package org.arn.hdsscapture.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.arn.hdsscapture.entity.Sociodemographic;
import org.arn.hdsscapture.entity.Task;
import org.arn.hdsscapture.repository.SesRepository;
import org.arn.hdsscapture.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheStats;

@RestController
public class SesZipController {
	
	private static final int BATCH_SIZE = 20000;
	private List<Integer> sesCount = new ArrayList<>();
	
	@Autowired
	SesRepository repo;
	
	@Autowired
	TaskRepository taskRepository;
	
	@GetMapping("/api/task/ses")
	@ResponseBody
	@Scheduled(cron = "0 0 5 * * *") // execute at 05:00 AM every day
	public ResponseEntity<String> downloadData(){
        try {
            String directoryPath = "hdss_zips";
            File directory = new File(directoryPath);

            if (!directory.exists() && !directory.mkdirs()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create directory");
            }

            // Clear the existing CSV file
            clearCsvFile(directoryPath);

            int page = 0;
            int pageSize = BATCH_SIZE;
            boolean hasMoreData = true;
            

            while (hasMoreData) {
                try {
                    List<Sociodemographic> dataBatch = repo.findSes(pageSize, page * pageSize);

                    if (dataBatch.isEmpty()) {
                        hasMoreData = false;
                        break;
                    }
                    // Create a temporary CSV file for each batch
                    String tempCsvFilePath = directoryPath + File.separator + "temp_ses_" + page + ".csv";
                    List<String> csvRows = new ArrayList<>();

                    for (Sociodemographic item : dataBatch) {
                        String csvRow = convertToCsvRow(item);
                        csvRows.add(csvRow);
                    }

                    // Write CSV for the current batch
                    writeCsv(tempCsvFilePath, csvRows);
                    sesCount.add(dataBatch.size());

                    page++;

                } catch (Exception e) {
                    e.printStackTrace();
                    hasMoreData = false;
                }
            }

            // Zip all temporary CSV files after processing all batches
            zipTemporaryCsvFiles(directoryPath, page);

            // Save or update task information
            saveOrUpdateTask(directoryPath, page * BATCH_SIZE);

            return ResponseEntity.ok("Process completed successfully");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to generate and zip files");
        }
    }
	
	private void clearCsvFile(String directoryPath) {
        try {
            String csvFilePath = directoryPath + File.separator + "ses.csv";
            File csvFile = new File(csvFilePath);

            if (csvFile.exists()) {
                // Delete the existing CSV file
                csvFile.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeCsv(String csvFilePath, List<String> csvRows) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFilePath, true))) {
            for (String csvRow : csvRows) {
                writer.println(csvRow.trim());  // Trim each line before writing
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


private void zipTemporaryCsvFiles(String directoryPath, int totalBatches) {
    try {
        String zipFilePath = directoryPath + File.separator + "ses.zip";
        String combinedCsvFilePath = directoryPath + File.separator + "combined_ses.csv";

        try (ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFilePath)))) {
            for (int i = 0; i < totalBatches; i++) {
                String tempCsvFilePath = directoryPath + File.separator + "temp_ses_" + i + ".csv";
                File tempCsvFile = new File(tempCsvFilePath);

                if (tempCsvFile.exists()) {
                    // Append the content of each temporary CSV file to the combined CSV file
                    appendCsvToFile(combinedCsvFilePath, tempCsvFilePath);

                    // Delete the temporary CSV file
                    tempCsvFile.delete();
                }else {
                    // If the temporary CSV file does not exist, create an empty CSV file
                    createEmptyCsvFile(tempCsvFilePath);

                    // Append the empty CSV file to the combined CSV file
                    appendCsvToFile(combinedCsvFilePath, tempCsvFilePath);
                }
            }

            // Zip the combined CSV file
            ZipEntry entry = new ZipEntry("ses.csv");
            zos.putNextEntry(entry);

            byte[] content = Files.readAllBytes(Paths.get(combinedCsvFilePath));
            zos.write(content);

            zos.closeEntry();
        }

        // Delete the combined CSV file after zipping
        File combinedCsvFile = new File(combinedCsvFilePath);
        if (combinedCsvFile.exists()) {
            combinedCsvFile.delete();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

private void appendCsvToFile(String destinationFilePath, String sourceFilePath) {
    try (PrintWriter writer = new PrintWriter(new FileWriter(destinationFilePath, true));
         BufferedReader reader = new BufferedReader(new FileReader(sourceFilePath))) {
        String line;
        while ((line = reader.readLine()) != null) {
            writer.println(line);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}



private String convertToCsvRow(Sociodemographic item) throws JsonProcessingException {
    CsvSchema schema = CsvSchema.builder()
    		.addColumn("socialgroup_uuid")
			  .addColumn("aircon_fcorres").addColumn("aircon_num_fcorres").addColumn("animal_othr_fcorres").addColumn("animal_othr_num_fcorres")
			  .addColumn("animal_othr_spfy_fcorres").addColumn("bike_fcorres").addColumn("bike_num_fcorres").addColumn("blender_fcorres")
			  .addColumn("blender_num_fcorres").addColumn("boat_fcorres").addColumn("boat_num_fcorres").addColumn("cabinets_fcorres")
			  .addColumn("cabinets_num_fcorres").addColumn("car_fcorres").addColumn("car_num_fcorres").addColumn("cart_fcorres")
			  .addColumn("cart_num_fcorres").addColumn("cattle_fcorres").addColumn("cattle_num_fcorres").addColumn("cethnic")
			  .addColumn("chew_bnut_oecoccur").addColumn("chew_oecoccur").addColumn("computer_fcorres").addColumn("computer_num_fcorres")
			  .addColumn("cooking_inside_fcorres").addColumn("cooking_loc_fcorres").addColumn("cooking_room_fcorres").addColumn("cooking_vent_fcorres")
			  .addColumn("donkey_fcorres").addColumn("donkey_num_fcorres").addColumn("drink_oecoccur").addColumn("dvd_cd_fcorres")
			  .addColumn("dvd_cd_num_fcorres").addColumn("edtime").addColumn("electricity_fcorres").addColumn("ext_wall_fcorres").addColumn("ext_wall_spfy_fcorres")
			  .addColumn("floor_fcorres").addColumn("floor_spfy_fcorres").addColumn("foam_matt_fcorres").addColumn("foam_matt_num_fcorres")
			  .addColumn("form_comments_txt").addColumn("form_comments_yn").addColumn("formcompldate").addColumn("fridge_fcorres").addColumn("fridge_num_fcorres")
				.addColumn("fw_uuid").addColumn("goat_fcorres").addColumn("goat_num_fcorres")
				.addColumn("h2o_dist_fcorres").addColumn("h2o_fcorres").addColumn("h2o_hours_fcorres")
				.addColumn("h2o_mins_fcorres").addColumn("h2o_prep_fcorres").addColumn("h2o_prep_spfy_fcorres_1")
				.addColumn("h2o_prep_spfy_fcorres_2").addColumn("h2o_prep_spfy_fcorres_3")
				.addColumn("h2o_prep_spfy_fcorres_4").addColumn("h2o_prep_spfy_fcorres_5").addColumn("h2o_spfy_fcorres")
				.addColumn("head_hh_fcorres").addColumn("head_hh_spfy_fcorres").addColumn("horse_fcorres")
				.addColumn("horse_num_fcorres").addColumn("house_occ_ge5_fcorres").addColumn("house_occ_lt5_fcorres")
				.addColumn("house_occ_tot_fcorres").addColumn("house_room_child_fcorres")
				.addColumn("house_rooms_fcorres").addColumn("individual_uuid").addColumn("insertDate")
				.addColumn("internet_fcorres").addColumn("job_busown_spfy_scorres").addColumn("job_othr_spfy_scorres")
				.addColumn("job_salary_spfy_scorres").addColumn("job_scorres").addColumn("job_skilled_spfy_scorres")
				.addColumn("job_smbus_spfy_scorres").addColumn("job_unskilled_spfy_scorres").addColumn("land_fcorres")
				.addColumn("land_use_fcorres_1").addColumn("land_use_fcorres_2").addColumn("land_use_fcorres_3")
				.addColumn("land_use_fcorres_4").addColumn("land_use_fcorres_5").addColumn("land_use_fcorres_88")
				.addColumn("land_use_spfy_fcorres_88").addColumn("landline_fcorres").addColumn("lantern_fcorres")
				.addColumn("lantern_num_fcorres").addColumn("livestock_fcorres").addColumn("location_uuid")
				.addColumn("marital_age").addColumn("marital_scorres")
				.addColumn("mobile_access_fcorres").addColumn("mobile_fcorres").addColumn("mobile_num_fcorres")
				.addColumn("mosquito_net_fcorres").addColumn("mosquito_net_num_fcorres").addColumn("motorcycle_fcorres")
				.addColumn("motorcycle_num_fcorres").addColumn("nth_trb_spfy_cethnic")
				.addColumn("othr_trb_spfy_cethnic").addColumn("own_rent_scorres").addColumn("own_rent_spfy_scorres")
				.addColumn("pig_fcorres").addColumn("pig_num_fcorres").addColumn("plough_fcorres")
				.addColumn("plough_num_fcorres").addColumn("poultry_fcorres").addColumn("poultry_num_fcorres")
				.addColumn("ptr_busown_spfy_scorres").addColumn("ptr_othr_spfy_scorres")
				.addColumn("ptr_salary_spfy_scorres").addColumn("ptr_scorres").addColumn("ptr_skilled_spfy_scorres")
				.addColumn("ptr_smbus_spfy_scorres").addColumn("ptr_unskilled_spfy_scorres").addColumn("radio_fcorres")
				.addColumn("radio_num_fcorres").addColumn("religion_scorres").addColumn("religion_spfy_scorres")
				.addColumn("roof_fcorres").addColumn("roof_spfy_fcorres").addColumn("sat_dish_fcorres")
				.addColumn("sat_dish_num_fcorres").addColumn("sd_obsstdat").addColumn("sew_fcorres")
				.addColumn("sew_num_fcorres").addColumn("sheep_fcorres").addColumn("sheep_num_fcorres")
				.addColumn("smoke_hhold_in_oecdosfrq").addColumn("smoke_hhold_oecoccur").addColumn("smoke_in_oecdosfrq")
				.addColumn("smoke_oecoccur").addColumn("sofa_fcorres").addColumn("sofa_num_fcorres")
				.addColumn("solar_fcorres").addColumn("spring_matt_fcorres").addColumn("spring_matt_num_fcorres")
				.addColumn("stove_fcorres").addColumn("stove_fuel_fcorres_1").addColumn("stove_fuel_fcorres_10")
				.addColumn("stove_fuel_fcorres_11").addColumn("stove_fuel_fcorres_12")
				.addColumn("stove_fuel_fcorres_13").addColumn("stove_fuel_fcorres_14").addColumn("stove_fuel_fcorres_2")
				.addColumn("stove_fuel_fcorres_3").addColumn("stove_fuel_fcorres_4").addColumn("stove_fuel_fcorres_5")
				.addColumn("stove_fuel_fcorres_6").addColumn("stove_fuel_fcorres_7").addColumn("stove_fuel_fcorres_8")
				.addColumn("stove_fuel_fcorres_88").addColumn("stove_fuel_fcorres_9")
				.addColumn("stove_fuel_spfy_fcorres_88").addColumn("stove_spfy_fcorres").addColumn("straw_matt_fcorres").addColumn("sttime")
				.addColumn("straw_matt_num_fcorres").addColumn("tables_fcorres").addColumn("tables_num_fcorres")
				.addColumn("toilet_fcorres").addColumn("toilet_loc_fcorres").addColumn("toilet_loc_spfy_fcorres")
				.addColumn("toilet_share_fcorres").addColumn("toilet_share_num_fcorres")
				.addColumn("toilet_spfy_fcorres").addColumn("tractor_fcorres").addColumn("tractor_num_fcorres")
				.addColumn("tricycles_fcorres").addColumn("tricycles_num_fcorres").addColumn("tv_fcorres")
				.addColumn("tv_num_fcorres").addColumn("uuid").addColumn("wash_fcorres").addColumn("wash_num_fcorres")
				.addColumn("watch_fcorres").addColumn("watch_num_fcorres").addColumn("comment").addColumn("status").addColumn("supervisor").addColumn("approveDate")
				.addColumn("pets").addColumn("dogs").addColumn("guinea_pigs").addColumn("cats").addColumn("fish").addColumn("birds")
				.addColumn("rabbits").addColumn("reptiles").addColumn("pet_other").addColumn("pet_other_spfy").addColumn("pet_vac")
				.addColumn("id0001").addColumn("id0002").addColumn("id0003").addColumn("id0004").addColumn("id0005")
				.addColumn("id0006").addColumn("id0006_1").addColumn("id0007").addColumn("id0007_1").addColumn("id0008").addColumn("id0008_1").addColumn("id0009").addColumn("id0009_1").addColumn("id0010")
				.addColumn("id0010_1").addColumn("id0011").addColumn("id0011_1").addColumn("id0012").addColumn("id0012_1").addColumn("id0014").addColumn("id0014_1").addColumn("id0015").addColumn("id0015_1").addColumn("id0016").addColumn("id0016_1").addColumn("id0017").addColumn("id0017_1").addColumn("id0018").addColumn("id0018_1").addColumn("id0019").addColumn("id0019_1").addColumn("id0013").addColumn("id0013_1").addColumn("id0021")
            .build();
    CsvMapper csvMapper = new CsvMapper();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    return csvMapper.writer(schema).with(formatter).writeValueAsString(item);
}


private void saveOrUpdateTask(String directoryPath, int totalRecords) {
    try {
        Optional<Task> optionalTask = taskRepository.findByFileName("ses");
        Task task;
        if (optionalTask.isPresent()) {
            // Update the existing zipfile entity with the new file data
            task = optionalTask.get();
        } else {
            // Create a new zipfile entity and save it to the database
            task = new Task();
            task.setFileName("ses");
        }

        task.setTimestamp(LocalDateTime.now());
        task.setType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        task.setData(getSizeString(calculateZipFileSize(directoryPath)));
        
        // Correct the total records count based on the actual number of records processed
        int actualRecords = calculateTotalRecords();
        task.setTotal(actualRecords);

        taskRepository.save(task);

        // Log or print a message indicating the success of the operation
        System.out.println("File " + (optionalTask.isPresent() ? "updated" : "uploaded") + " successfully");
    } catch (Exception e) {
        // Log or print the error message
        e.printStackTrace();
    }
}

private int calculateTotalRecords() {
    return sesCount.stream().mapToInt(Integer::intValue).sum();
}

private long calculateZipFileSize(String directoryPath) {
    String zipFilePath = directoryPath + File.separator + "ses.zip";
    File zipFile = new File(zipFilePath);

    if (zipFile.exists()) {
        return zipFile.length();
    } else {
        return 0; // Return 0 if the ZIP file doesn't exist
    }
}
	
private static final Logger logger = LoggerFactory.getLogger(SesZipController.class);

private final Cache<String, ByteArrayResource> zipFileCache;

public SesZipController(Cache<String, ByteArrayResource> zipFileCache) {
    this.zipFileCache = zipFileCache;

}

@Cacheable(value = "sesZipCache", key = "'ses'")
@GetMapping("/api/zip/ses")
public ResponseEntity<ByteArrayResource> downloadZipFile() {
	CacheStats cacheStats = zipFileCache.stats();
    System.out.println("Cache Hits: " + cacheStats.hitCount());
    System.out.println("Cache Misses: " + cacheStats.missCount());
    // Find task entity by filename
    Optional<Task> optionalTask = taskRepository.findByFileName("ses");
    if (!optionalTask.isPresent()) {
        return ResponseEntity.notFound().build();
    }

    String cacheKey = "ses";
    ByteArrayResource cachedResource = zipFileCache.getIfPresent(cacheKey);

    if (cachedResource != null) {
    	System.out.println("Cache Result: " + cachedResource);
        // Serve from the cache
        return buildResponseEntity(cachedResource);
    }else {
    	System.out.println("Cache Result False ");
    }

    // Retrieve the ZIP file from the directory
    String directoryPath = "hdss_zips";
    String filePath = directoryPath + File.separator + "ses.zip";
    File zipFile = new File(filePath);

    if (!zipFile.exists()) {
        return ResponseEntity.notFound().build();
    }

    // Initialize the resource outside the try block
    ByteArrayResource resource;

    // Read the ZIP file into a ByteArrayResource
    try (InputStream inputStream = new FileInputStream(zipFile);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

        byte[] buffer = new byte[1024];
        int bytesRead;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, bytesRead);
        }

        resource = new ByteArrayResource(byteArrayOutputStream.toByteArray());

    } catch (IOException e) {
        logger.error("Error reading ZIP file", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    // Use the resource when building the response entity
    return buildResponseEntity(resource);
}

// Move the buildResponseEntity method inside the sesZipController class
private ResponseEntity<ByteArrayResource> buildResponseEntity(ByteArrayResource resource) {
    // Build response headers
    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"ses.zip\"");
    headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
    headers.setContentLength(resource.contentLength());

    // Build response entity
    return ResponseEntity.ok()
            .headers(headers)
            .body(resource);
}
    
	private String getSizeString(long size) {
        String[] units = {"bytes", "KB", "MB"};
        int unitIndex = 0;
        double sizeValue = size;

        while (sizeValue > 1024 && unitIndex < units.length - 1) {
            sizeValue /= 1024;
            unitIndex++;
        }

        if (units.length > 0) {
            return String.format("%.2f %s", sizeValue, units[unitIndex]);
        } else {
            return "Invalid unit configuration";
        }
    }

	
	private void createEmptyCsvFile(String csvFilePath) {
	    try (PrintWriter writer = new PrintWriter(new FileWriter(csvFilePath))) {
	        // Empty CSV file does not require any data rows
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}

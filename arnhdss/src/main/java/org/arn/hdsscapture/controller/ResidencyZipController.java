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

import org.arn.hdsscapture.entity.Residency;
import org.arn.hdsscapture.entity.Task;
import org.arn.hdsscapture.repository.ResidencyRepository;
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
public class ResidencyZipController {
	
	private static final int BATCH_SIZE = 20000;
	private List<Integer> resCount = new ArrayList<>();
	
	@Autowired
	ResidencyRepository repo;
	
	@Autowired
	TaskRepository taskRepository;
	
	@GetMapping("/api/task/residency")
	@ResponseBody
	@Scheduled(cron = "0 0 5 * * *") // execute at 05:00 AM every day
	public ResponseEntity<String> downloadResidency(){
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
                    List<Residency> dataBatch = repo.findResidency(pageSize, page * pageSize);

                    if (dataBatch.isEmpty()) {
                        hasMoreData = false;
                        break;
                    }
                    // Create a temporary CSV file for each batch
                    String tempCsvFilePath = directoryPath + File.separator + "temp_residency_" + page + ".csv";
                    List<String> csvRows = new ArrayList<>();

                    for (Residency item : dataBatch) {
                        String csvRow = convertToCsvRow(item);
                        csvRows.add(csvRow);
                    }

                    // Write CSV for the current batch
                    writeCsv(tempCsvFilePath, csvRows);
                    resCount.add(dataBatch.size());

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
            String csvFilePath = directoryPath + File.separator + "residency.csv";
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
        String zipFilePath = directoryPath + File.separator + "residency.zip";
        String combinedCsvFilePath = directoryPath + File.separator + "combined_residency.csv";

        try (ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFilePath)))) {
            for (int i = 0; i < totalBatches; i++) {
                String tempCsvFilePath = directoryPath + File.separator + "temp_residency_" + i + ".csv";
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
            ZipEntry entry = new ZipEntry("residency.csv");
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



private String convertToCsvRow(Residency item) throws JsonProcessingException {
    CsvSchema schema = CsvSchema.builder()
    		.addColumn("uuid").addColumn("edtime").addColumn("endDate").addColumn("endType")
			  .addColumn("fw_uuid").addColumn("insertDate").addColumn("rltn_head")
			  .addColumn("startDate").addColumn("startType")
			  .addColumn("individual_uuid").addColumn("location_uuid").addColumn("socialgroup_uuid").addColumn("sttime")
            .build();
    CsvMapper csvMapper = new CsvMapper();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    return csvMapper.writer(schema).with(formatter).writeValueAsString(item);
}


private void saveOrUpdateTask(String directoryPath, int totalRecords) {
    try {
        Optional<Task> optionalTask = taskRepository.findByFileName("residency");
        Task task;
        if (optionalTask.isPresent()) {
            // Update the existing zipfile entity with the new file data
            task = optionalTask.get();
        } else {
            // Create a new zipfile entity and save it to the database
            task = new Task();
            task.setFileName("residency");
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
    return resCount.stream().mapToInt(Integer::intValue).sum();
}

private long calculateZipFileSize(String directoryPath) {
    String zipFilePath = directoryPath + File.separator + "residency.zip";
    File zipFile = new File(zipFilePath);

    if (zipFile.exists()) {
        return zipFile.length();
    } else {
        return 0; // Return 0 if the ZIP file doesn't exist
    }
}
	
//	@GetMapping("/api/zip/residency")
//    public ResponseEntity<ByteArrayResource> downloadZipFile() {
//        // Find task entity by filename
//        Optional<Task> optionalTask = taskRepository.findByFileName("residency");
//        if (!optionalTask.isPresent()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        //Task task = optionalTask.get();
//        
//        // Retrieve the ZIP file from the directory
//        String directoryPath = "hdss_zips";
//        String filePath = directoryPath + File.separator + "residency.zip";
//        File zipFile = new File(filePath);
//
//        // Create a ByteArrayResource from the ZIP file
//        ByteArrayResource resource;
//        try {
//            resource = new ByteArrayResource(Files.readAllBytes(zipFile.toPath()));
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//
//        // Build response headers
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"residency.zip\"");
//        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
//        headers.setContentLength(zipFile.length());
//
//        // Build response entity
//        ResponseEntity<ByteArrayResource> response = ResponseEntity.ok()
//                .headers(headers)
//                .body(resource);
//
//        return response;
//    }

private static final Logger logger = LoggerFactory.getLogger(ResidencyZipController.class);

private final Cache<String, ByteArrayResource> zipFileCache;

public ResidencyZipController(Cache<String, ByteArrayResource> zipFileCache) {
    this.zipFileCache = zipFileCache;

}

@Cacheable(value = "residencyZipCache", key = "'residency'")
@GetMapping("/api/zip/residency")
public ResponseEntity<ByteArrayResource> downloadZipFile() {
	CacheStats cacheStats = zipFileCache.stats();
    System.out.println("Cache Hits: " + cacheStats.hitCount());
    System.out.println("Cache Misses: " + cacheStats.missCount());
    // Find task entity by filename
    Optional<Task> optionalTask = taskRepository.findByFileName("residency");
    if (!optionalTask.isPresent()) {
        return ResponseEntity.notFound().build();
    }

    String cacheKey = "residency";
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
    String filePath = directoryPath + File.separator + "residency.zip";
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

// Move the buildResponseEntity method inside the residencyZipController class
private ResponseEntity<ByteArrayResource> buildResponseEntity(ByteArrayResource resource) {
    // Build response headers
    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"residency.zip\"");
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

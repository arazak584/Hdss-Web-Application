package org.arn.hdsscapture.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.arn.hdsscapture.entity.Sociodemographic;
import org.arn.hdsscapture.entity.Task;
import org.arn.hdsscapture.repository.SesRepository;
import org.arn.hdsscapture.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

@RestController
public class SesZipController {
	
	@Autowired
	SesRepository repo;
	
	@Autowired
	TaskRepository taskRepository;
	
	@GetMapping("/api/task/ses")
	@ResponseBody
	@Scheduled(cron = "0 0 5 * * *") // execute at 05:00 AM every day
	public ResponseEntity<String> downloadData(){
		try {
	  // Retrieve data from database
	  List<Sociodemographic> data = repo.findSes();

	  // Convert data to CSV
	  CsvSchema schema = CsvSchema.builder().addColumn("socialgroup_uuid")
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
				.addColumn("watch_fcorres").addColumn("watch_num_fcorres").build();
	  CsvMapper csvMapper = new CsvMapper();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  
	// Create a ByteArrayOutputStream to store the ZIP file content
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      try (ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(baos))) {
          // Create a new ZIP entry for the CSV file
          ZipEntry entry = new ZipEntry("ses.csv");
          zos.putNextEntry(entry);

          // Iterate through data and stream CSV content directly to the ZIP file
          for (Sociodemographic item : data) {
              String csvRow = csvMapper.writer(schema).with(formatter).writeValueAsString(item);
              zos.write(csvRow.getBytes());
          }

          // Close the ZIP entry
          zos.closeEntry();
      }
  	  
      // Get zip file data
      byte[] zipData = baos.toByteArray();
      // Get zip file size
      long zipSizeBytes = baos.size();
      String zipSize = getSizeString(zipSizeBytes);
  
  
   // Create the "zips" directory if it doesn't exist
      String directoryPath = "hdss_zips";
      File directory = new File(directoryPath);
      if (!directory.exists()) {
          if (!directory.mkdirs()) {
              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create directory");
          }
      }

      // Write zip file to the directory
      String filePath = directoryPath + File.separator + "ses.zip";
      try (FileOutputStream fos = new FileOutputStream(filePath)) {
          fos.write(zipData);
      } catch (IOException e) {
          e.printStackTrace();
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save file");
      }
  
   // Insert or update task entity
      Optional<Task> optionalTask = taskRepository.findByFileName("ses");
      if (optionalTask.isPresent()) {
          // Update the existing zipfile entity with the new file data
          Task task = optionalTask.get();
          task.setTimestamp(LocalDateTime.now());
          task.setType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
          task.setData(zipSize);
          task.setTotal(data.size());
          taskRepository.save(task);
          return ResponseEntity.status(HttpStatus.OK).body("File updated successfully");
      } else {
          // Create a new zipfile entity and save it to the database
          Task task = new Task();
          task.setTimestamp(LocalDateTime.now());
          task.setFileName("ses");
          task.setType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
          task.setData(zipSize);
          task.setTotal(data.size());
          taskRepository.save(task);
          return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully");
      }
  } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
  }

	}
	
	@GetMapping("/api/zip/ses")
    public ResponseEntity<ByteArrayResource> downloadZipFile() {
        // Find task entity by filename
        Optional<Task> optionalTask = taskRepository.findByFileName("ses");
        if (!optionalTask.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        //Task task = optionalTask.get();
        
        // Retrieve the ZIP file from the directory
        String directoryPath = "hdss_zips";
        String filePath = directoryPath + File.separator + "ses.zip";
        File zipFile = new File(filePath);

        // Create a ByteArrayResource from the ZIP file
        ByteArrayResource resource;
        try {
            resource = new ByteArrayResource(Files.readAllBytes(zipFile.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        // Build response headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"ses.zip\"");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        headers.setContentLength(zipFile.length());

        // Build response entity
        ResponseEntity<ByteArrayResource> response = ResponseEntity.ok()
                .headers(headers)
                .body(resource);

        return response;
    }
    
    private String getSizeString(long size) {
        String[] units = {"bytes", "KB", "MB"};
        int unitIndex = 0;
        double sizeValue = size;

        while (sizeValue > 1024 && unitIndex < units.length - 1) {
            sizeValue /= 1024;
            unitIndex++;
        }

        return String.format("%.2f %s", sizeValue, units[unitIndex]);
    }


}

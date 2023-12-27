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

import org.arn.hdsscapture.entity.Demographic;
import org.arn.hdsscapture.entity.Task;
import org.arn.hdsscapture.repository.DemographicRepository;
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
public class DemographicZipController {
	
	@Autowired
	DemographicRepository repo;
	
	@Autowired
	TaskRepository taskRepository;
	
	@GetMapping("/api/task/demographics")
	@ResponseBody
	@Scheduled(cron = "0 0 5 * * *") // execute at 05:00 AM every day
	public ResponseEntity<String> downloadDemography() {
		try {
	  // Retrieve data from database
	  List<Demographic> data = repo.findDemographic();

	  // Convert data to CSV
	  CsvSchema schema = CsvSchema.builder().addColumn("individual_uuid").addColumn("comp_yrs").addColumn("edtime").addColumn("education").addColumn("fw_uuid")
			  .addColumn("insertDate").addColumn("marital").addColumn("occupation").addColumn("occupation_oth")
			  .addColumn("phone1").addColumn("phone2")
			  .addColumn("religion").addColumn("religion_oth").addColumn("sttime").addColumn("tribe").addColumn("tribe_oth")
			  .build();
	  CsvMapper csvMapper = new CsvMapper();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  
	  
	// Create a ByteArrayOutputStream to store the ZIP file content
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      try (ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(baos))) {
          // Create a new ZIP entry for the CSV file
          ZipEntry entry = new ZipEntry("demographics.csv");
          zos.putNextEntry(entry);

          // Iterate through data and stream CSV content directly to the ZIP file
          for (Demographic item : data) {
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
      String filePath = directoryPath + File.separator + "demographics.zip";
      try (FileOutputStream fos = new FileOutputStream(filePath)) {
          fos.write(zipData);
      } catch (IOException e) {
          e.printStackTrace();
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save file");
      }
  
   // Insert or update task entity
      Optional<Task> optionalTask = taskRepository.findByFileName("demographics");
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
          task.setFileName("demographics");
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
	
	
	
	@GetMapping("/api/zip/demographics")
    public ResponseEntity<ByteArrayResource> downloadZipFile() {
        // Find task entity by filename
        Optional<Task> optionalTask = taskRepository.findByFileName("demographics");
        if (!optionalTask.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        //Task task = optionalTask.get();
        
        // Retrieve the ZIP file from the directory
        String directoryPath = "hdss_zips";
        String filePath = directoryPath + File.separator + "demographics.zip";
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
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"demographics.zip\"");
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

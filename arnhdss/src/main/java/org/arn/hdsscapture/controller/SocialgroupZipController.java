package org.arn.hdsscapture.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.arn.hdsscapture.entity.Socialgroup;
import org.arn.hdsscapture.entity.Task;
import org.arn.hdsscapture.repository.SocialgroupRepository;
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
public class SocialgroupZipController {
	
	@Autowired
	SocialgroupRepository repo;
	
	@Autowired
	TaskRepository taskRepository;
	
	@GetMapping("/api/task/socialgroup")
	@ResponseBody
	@Scheduled(cron = "0 0 5 * * *") // execute at 05:00 AM every day
	public ResponseEntity<String> downloadData(){
		try {
	  // Retrieve data from database
	  List<Socialgroup> data = repo.findAll();

	  // Convert data to CSV
	  CsvSchema schema = CsvSchema.builder().addColumn("uuid").addColumn("extId").addColumn("fw_uuid").addColumn("groupName")
			  .addColumn("groupType").addColumn("insertDate")
			  .addColumn("individual_uuid").build();
	  CsvMapper csvMapper = new CsvMapper();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  StringWriter writer = new StringWriter();
	  csvMapper.writer(schema).with(formatter).writeValues(writer).writeAll(data);
	  String csv = writer.toString();
	  
	  // Get the total number of records in the CSV file
		  int total = data.size();

	  // Zip the CSV file
	  ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ZipOutputStream zos = new ZipOutputStream(baos);
      ZipEntry entry = new ZipEntry("socialgroup.csv");
      zos.putNextEntry(entry);
      zos.write(csv.getBytes());
      zos.closeEntry();
      zos.close();
	  
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
      String filePath = directoryPath + File.separator + "socialgroup.zip";
      try (FileOutputStream fos = new FileOutputStream(filePath)) {
          fos.write(zipData);
      } catch (IOException e) {
          e.printStackTrace();
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save file");
      }

      // Insert or update task entity
      Optional<Task> optionalTask = taskRepository.findByFileName("socialgroup");
      if (optionalTask.isPresent()) {
          // update the existing zipfile entity with the new file data
          Task task = optionalTask.get();
          task.setTimestamp(LocalDateTime.now());
          task.setType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
          task.setData(zipSize);
          task.setTotal(total);
          taskRepository.save(task);
          return ResponseEntity.status(HttpStatus.OK).body("File updated successfully");
      } else {
          // create a new zipfile entity and save it to the database
          Task task = new Task();
          task.setTimestamp(LocalDateTime.now());
          task.setFileName("socialgroup");
          task.setType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
          task.setData(zipSize);
          task.setTotal(total);
          taskRepository.save(task);
          return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully");
      }
  } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
  }

	}
	
	@GetMapping("/api/zip/socialgroup")
    public ResponseEntity<ByteArrayResource> downloadZipFile() {
        // Find task entity by filename
        Optional<Task> optionalTask = taskRepository.findByFileName("socialgroup");
        if (!optionalTask.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        //Task task = optionalTask.get();
        
        // Retrieve the ZIP file from the directory
        String directoryPath = "hdss_zips";
        String filePath = directoryPath + File.separator + "socialgroup.zip";
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
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"socialgroup.zip\"");
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

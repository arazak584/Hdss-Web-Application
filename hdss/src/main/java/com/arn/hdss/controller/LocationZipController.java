package com.arn.hdss.controller;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.arn.hdss.entity.Location;
import com.arn.hdss.entity.Task;
import com.arn.hdss.repository.LocationRepository;
import com.arn.hdss.repository.TaskRepository;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

@RestController
@RequestMapping("/api/zip/location")
public class LocationZipController {
	
	@Autowired
	LocationRepository repo;
	
	@Autowired
	TaskRepository taskRepository;
	
	@GetMapping("")
	@ResponseBody
	public ResponseEntity<String> downloadLocation() {
		try {
	  // Retrieve data from database
	  List<Location> data = repo.findAll();

	  // Convert data to CSV
	  CsvSchema schema = CsvSchema.builder().addColumn("extId").addColumn("clusterId").addColumn("locationType").addColumn("accuracy")
			  .addColumn("compno").addColumn("fw").addColumn("insertDate").addColumn("latitude")
			  .addColumn("locationName").addColumn("longitude").addColumn("status").build();
	  CsvMapper csvMapper = new CsvMapper();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  StringWriter writer = new StringWriter();
	  csvMapper.writer(schema).with(formatter).writeValues(writer).writeAll(data);
	  String csv = writer.toString();

	  // Zip the CSV file
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ZipOutputStream zos = new ZipOutputStream(baos);
      ZipEntry entry = new ZipEntry("location.csv");
      zos.putNextEntry(entry);
      zos.write(csv.getBytes());
      zos.closeEntry();
      zos.close();
	  
   // Get zip file data
      byte[] zipData = baos.toByteArray();

      // Insert or update task entity
      Optional<Task> optionalTask = taskRepository.findByFileName("location.zip");
      if (optionalTask.isPresent()) {
          // update the existing zipfile entity with the new file data
          Task task = optionalTask.get();
          task.setTimestamp(LocalDateTime.now());
          task.setType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
          task.setData(zipData);
          taskRepository.save(task);
          return ResponseEntity.status(HttpStatus.OK).body("File updated successfully");
      } else {
          // create a new zipfile entity and save it to the database
          Task task = new Task();
          task.setTimestamp(LocalDateTime.now());
          task.setFileName("location.zip");
          task.setType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
          task.setData(zipData);
          taskRepository.save(task);
          return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully");
      }
  } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
  }

	}

}

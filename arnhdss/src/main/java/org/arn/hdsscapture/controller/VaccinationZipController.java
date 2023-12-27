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

import org.arn.hdsscapture.entity.Task;
import org.arn.hdsscapture.entity.Vaccination;
import org.arn.hdsscapture.repository.TaskRepository;
import org.arn.hdsscapture.repository.VaccinationRepository;
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
public class VaccinationZipController {
	
	@Autowired
	VaccinationRepository repo;
	
	@Autowired
	TaskRepository taskRepository;
	
	@GetMapping("/api/task/vaccination")
	@ResponseBody
	@Scheduled(cron = "0 0 5 * * *") // execute at 05:00 AM every day
	public ResponseEntity<String> downloadVaccination() {
		try {
	  // Retrieve data from database
	  List<Vaccination> data = repo.findVaccination();

	  // Convert data to CSV
		CsvSchema schema = CsvSchema.builder().addColumn("individual_uuid").addColumn("admission")
				.addColumn("admitDate").addColumn("arti").addColumn("artitreat").addColumn("bcg").addColumn("bednet")
				.addColumn("chlbednet").addColumn("diarrhoea").addColumn("diarrhoeatreat").addColumn("dob")
				.addColumn("dpt_hepb_hib1").addColumn("dpt_hepb_hib2").addColumn("dpt_hepb_hib3").addColumn("editDate").addColumn("edtime").addColumn("fever")
				.addColumn("fevertreat").addColumn("fw_uuid").addColumn("hcard").addColumn("hl").addColumn("hod")
				.addColumn("hom").addColumn("insertDate").addColumn("ipv").addColumn("itn").addColumn("location_uuid")
				.addColumn("measles_rubella1").addColumn("measles_rubella2").addColumn("menA").addColumn("muac")
				.addColumn("nhis").addColumn("onet").addColumn("opv0").addColumn("opv1").addColumn("opv2")
				.addColumn("opv3").addColumn("pneumo1").addColumn("pneumo2").addColumn("pneumo3").addColumn("rea")
				.addColumn("rea_oth").addColumn("reason").addColumn("reason_oth").addColumn("rota1").addColumn("rota2")
				.addColumn("rota3").addColumn("rtss18").addColumn("rtss6").addColumn("rtss7").addColumn("rtss9")
				.addColumn("sbf").addColumn("scar").addColumn("slpbednet").addColumn("socialgroup_uuid")
				.addColumn("stm").addColumn("sttime").addColumn("sty").addColumn("uuid").addColumn("vitaminA12").addColumn("vitaminA18")
				.addColumn("vitaminA6").addColumn("weight").addColumn("yellow_fever").build();
	  CsvMapper csvMapper = new CsvMapper();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  
	  
	// Create a ByteArrayOutputStream to store the ZIP file content
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      try (ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(baos))) {
          // Create a new ZIP entry for the CSV file
          ZipEntry entry = new ZipEntry("vaccination.csv");
          zos.putNextEntry(entry);

          // Iterate through data and stream CSV content directly to the ZIP file
          for (Vaccination item : data) {
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
      String filePath = directoryPath + File.separator + "vaccination.zip";
      try (FileOutputStream fos = new FileOutputStream(filePath)) {
          fos.write(zipData);
      } catch (IOException e) {
          e.printStackTrace();
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save file");
      }
  
   // Insert or update task entity
      Optional<Task> optionalTask = taskRepository.findByFileName("vaccination");
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
          task.setFileName("vaccination");
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
	
	
	
	@GetMapping("/api/zip/vaccination")
    public ResponseEntity<ByteArrayResource> downloadZipFile() {
        // Find task entity by filename
        Optional<Task> optionalTask = taskRepository.findByFileName("vaccination");
        if (!optionalTask.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        //Task task = optionalTask.get();
        
        // Retrieve the ZIP file from the directory
        String directoryPath = "hdss_zips";
        String filePath = directoryPath + File.separator + "vaccination.zip";
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
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"vaccination.zip\"");
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

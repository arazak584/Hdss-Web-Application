package org.arn.hdsscapture.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.arn.hdsscapture.entity.Task;
import org.arn.hdsscapture.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/upload")
public class UploadController {
	
	@Autowired
	TaskRepository repo;

	
	@PostMapping("/individual")
	public ResponseEntity<String> handleZipfileUpload(@RequestParam("individual.zip") MultipartFile file) {
	    try {
	        Optional<Task> optionalZipfile = repo.findByFileName(file.getOriginalFilename());
	        if (optionalZipfile.isPresent()) {
	            // update the existing zipfile entity with the new file data
	            Task task = optionalZipfile.get();
	            task.setTimestamp(LocalDateTime.now());
	            task.setType(file.getContentType());
	            task.setData(file.getBytes());
	            repo.save(task);
	            return ResponseEntity.status(HttpStatus.OK).body("File updated successfully");
	        } else {
	            // create a new zipfile entity and save it to the database
	            Task task = new Task();
	            task.setTimestamp(LocalDateTime.now());
	            task.setFileName(file.getOriginalFilename());
	            task.setType(file.getContentType());
	            task.setData(file.getBytes());
	            repo.save(task);
	            return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully");
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
	    }
	}
	
	
	@PostMapping("/location")
	public ResponseEntity<String> ZiplocationUpload(@RequestParam("location.zip") MultipartFile file) {
	    try {
	        Optional<Task> optionalZipfile = repo.findByFileName(file.getOriginalFilename());
	        if (optionalZipfile.isPresent()) {
	            // update the existing zipfile entity with the new file data
	            Task task = optionalZipfile.get();
	            task.setTimestamp(LocalDateTime.now());
	            task.setType(file.getContentType());
	            task.setData(file.getBytes());
	            repo.save(task);
	            return ResponseEntity.status(HttpStatus.OK).body("File updated successfully");
	        } else {
	            // create a new zipfile entity and save it to the database
	            Task task = new Task();
	            task.setTimestamp(LocalDateTime.now());
	            task.setFileName(file.getOriginalFilename());
	            task.setType(file.getContentType());
	            task.setData(file.getBytes());
	            repo.save(task);
	            return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully");
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
	    }
	}


	@PostMapping("/residency")
	public ResponseEntity<String> ZipresidencyUpload(@RequestParam("residency.zip") MultipartFile file) {
	    try {
	        Optional<Task> optionalZipfile = repo.findByFileName(file.getOriginalFilename());
	        if (optionalZipfile.isPresent()) {
	            // update the existing zipfile entity with the new file data
	            Task task = optionalZipfile.get();
	            task.setTimestamp(LocalDateTime.now());
	            task.setType(file.getContentType());
	            task.setData(file.getBytes());
	            repo.save(task);
	            return ResponseEntity.status(HttpStatus.OK).body("File updated successfully");
	        } else {
	            // create a new zipfile entity and save it to the database
	            Task task = new Task();
	            task.setTimestamp(LocalDateTime.now());
	            task.setFileName(file.getOriginalFilename());
	            task.setType(file.getContentType());
	            task.setData(file.getBytes());
	            repo.save(task);
	            return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully");
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
	    }
	}
	
	
	@PostMapping("/socialgroup")
	public ResponseEntity<String> ZipsocialgroupUpload(@RequestParam("socialgroup.zip") MultipartFile file) {
	    try {
	        Optional<Task> optionalZipfile = repo.findByFileName(file.getOriginalFilename());
	        if (optionalZipfile.isPresent()) {
	            // update the existing zipfile entity with the new file data
	            Task task = optionalZipfile.get();
	            task.setTimestamp(LocalDateTime.now());
	            task.setType(file.getContentType());
	            task.setData(file.getBytes());
	            repo.save(task);
	            return ResponseEntity.status(HttpStatus.OK).body("File updated successfully");
	        } else {
	            // create a new zipfile entity and save it to the database
	            Task task = new Task();
	            task.setTimestamp(LocalDateTime.now());
	            task.setFileName(file.getOriginalFilename());
	            task.setType(file.getContentType());
	            task.setData(file.getBytes());
	            repo.save(task);
	            return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully");
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
	    }
	}
	
	
	@PostMapping("/pregnancy")
	public ResponseEntity<String> ZippregnancyUpload(@RequestParam("pregnancy.zip") MultipartFile file) {
	    try {
	        Optional<Task> optionalZipfile = repo.findByFileName(file.getOriginalFilename());
	        if (optionalZipfile.isPresent()) {
	            // update the existing zipfile entity with the new file data
	            Task task = optionalZipfile.get();
	            task.setTimestamp(LocalDateTime.now());
	            task.setType(file.getContentType());
	            task.setData(file.getBytes());
	            repo.save(task);
	            return ResponseEntity.status(HttpStatus.OK).body("File updated successfully");
	        } else {
	            // create a new zipfile entity and save it to the database
	            Task task = new Task();
	            task.setTimestamp(LocalDateTime.now());
	            task.setFileName(file.getOriginalFilename());
	            task.setType(file.getContentType());
	            task.setData(file.getBytes());
	            repo.save(task);
	            return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully");
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
	    }
	}
	
	
	@PostMapping("/relationship")
	public ResponseEntity<String> ZiprelationshipUpload(@RequestParam("relationship.zip") MultipartFile file) {
	    try {
	        Optional<Task> optionalZipfile = repo.findByFileName(file.getOriginalFilename());
	        if (optionalZipfile.isPresent()) {
	            // update the existing zipfile entity with the new file data
	            Task task = optionalZipfile.get();
	            task.setTimestamp(LocalDateTime.now());
	            task.setType(file.getContentType());
	            task.setData(file.getBytes());
	            repo.save(task);
	            return ResponseEntity.status(HttpStatus.OK).body("File updated successfully");
	        } else {
	            // create a new zipfile entity and save it to the database
	            Task task = new Task();
	            task.setTimestamp(LocalDateTime.now());
	            task.setFileName(file.getOriginalFilename());
	            task.setType(file.getContentType());
	            task.setData(file.getBytes());
	            repo.save(task);
	            return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully");
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
	    }
	}
	



}

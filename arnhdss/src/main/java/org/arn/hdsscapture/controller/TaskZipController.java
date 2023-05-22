package org.arn.hdsscapture.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.arn.hdsscapture.entity.Task;
import org.arn.hdsscapture.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/task")
public class TaskZipController {

    @Autowired
    TaskRepository taskRepository;

    @GetMapping("")
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        // Find all task entities in the repository
        List<Task> tasks = taskRepository.findAll();

        // Filter tasks by file name
        List<Task> locationTasks = tasks.stream()
                .filter(task -> task.getFileName().equals("location.zip"))
                .collect(Collectors.toList());
        List<Task> individualTasks = tasks.stream()
                .filter(task -> task.getFileName().equals("individual.zip"))
                .collect(Collectors.toList());
        List<Task> residencyTasks = tasks.stream()
                .filter(task -> task.getFileName().equals("residency.zip"))
                .collect(Collectors.toList());
        List<Task> socialgroupTasks = tasks.stream()
                .filter(task -> task.getFileName().equals("socialgroup.zip"))
                .collect(Collectors.toList());
        List<Task> relationshipTasks = tasks.stream()
                .filter(task -> task.getFileName().equals("relationship.zip"))
                .collect(Collectors.toList());
        List<Task> pregnancyTasks = tasks.stream()
                .filter(task -> task.getFileName().equals("pregnancy.zip"))
                .collect(Collectors.toList());
        List<Task> demographyTasks = tasks.stream()
                .filter(task -> task.getFileName().equals("demography.zip"))
                .collect(Collectors.toList());
        List<Task> sesTasks = tasks.stream()
                .filter(task -> task.getFileName().equals("ses.zip"))
                .collect(Collectors.toList());

        // Create DTOs for each file
        TaskDto locationDto = createDto(locationTasks);
        TaskDto individualDto = createDto(individualTasks);
        TaskDto residencyDto = createDto(residencyTasks);
        TaskDto socialgroupDto = createDto(socialgroupTasks);
        TaskDto relationshipDto = createDto(relationshipTasks);
        TaskDto pregnancyDto = createDto(pregnancyTasks);
        TaskDto demographyDto = createDto(demographyTasks);
        TaskDto sesDto = createDto(sesTasks);

        // Add DTOs to list
        List<TaskDto> dtos = Arrays.asList(locationDto, individualDto, residencyDto, socialgroupDto,relationshipDto,pregnancyDto,demographyDto, sesDto);

        // Return the DTOs in the response body
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<Resource> downloadZip(@PathVariable String fileName) {
        // Find task entity by filename
        Optional<Task> optionalTask = taskRepository.findByFileName(fileName);
        if (!optionalTask.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Task task = optionalTask.get();
        
     // Create the file path
        String directoryPath = "zips";
        String filePath = directoryPath + File.separator + task.getFileName();

        // Create the file object
        File file = new File(filePath);

        // Check if the file exists
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }
        
     // Create a ByteArrayResource from the file
        ByteArrayResource resource;
        try {
            resource = new ByteArrayResource(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        
     // Build response headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + task.getFileName() + "\"");
        headers.add(HttpHeaders.CONTENT_TYPE, task.getType());
        headers.setContentLength(file.length());

        // Build response entity
        ResponseEntity<Resource> response = ResponseEntity.ok()
                .headers(headers)
                .body(resource);

        return response;
    }

    private TaskDto createDto(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return null;
        }

        Task task = tasks.get(0);

        // Create DTO with file name and download URL
        TaskDto dto = new TaskDto();
        dto.setFileName(task.getFileName());
        dto.setDownloadUrl("/api/zip/task/" + task.getFileName());

        return dto;
    }

    public static class TaskDto {
        private String fileName;
        private String downloadUrl;

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getDownloadUrl() {
            return downloadUrl;
        }

        public void setDownloadUrl(String downloadUrl) {
            this.downloadUrl = downloadUrl;
        }
    }
}

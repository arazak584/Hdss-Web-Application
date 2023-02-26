package com.arn.hdss.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arn.hdss.entity.Task;
import com.arn.hdss.repository.TaskRepository;

@RestController
@RequestMapping("/api/zip/task")
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

        // Create DTOs for each file
        TaskDto locationDto = createDto(locationTasks);
        TaskDto individualDto = createDto(individualTasks);
        TaskDto residencyDto = createDto(residencyTasks);
        TaskDto socialgroupDto = createDto(socialgroupTasks);
        TaskDto relationshipDto = createDto(relationshipTasks);
        TaskDto pregnancyDto = createDto(pregnancyTasks);

        // Add DTOs to list
        List<TaskDto> dtos = Arrays.asList(locationDto, individualDto, residencyDto, socialgroupDto,relationshipDto,pregnancyDto);

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

        // Create ByteArrayResource from task data
        ByteArrayResource resource = new ByteArrayResource(task.getData());

        // Build response headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + task.getFileName() + "\"");
        headers.add(HttpHeaders.CONTENT_TYPE, task.getType());
        headers.setContentLength(task.getData().length);

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

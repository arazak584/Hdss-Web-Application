package org.arn.hdsscapture.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocumentController {

	
	@GetMapping("/hdss/forms")
    public ResponseEntity<Object> listForms() {
        try {
            // Define the directory path relative to the user directory
            String directoryPath = System.getProperty("user.dir") + File.separator + "hdss_forms";
            File directory = new File(directoryPath);

            // Attempt to create the directory if it doesn't exist
            if (!directory.exists()) {
                if (directory.mkdirs()) {
                    System.out.println("Directory created: " + directory.getAbsolutePath());
                } else {
                    System.err.println("Failed to create directory: " + directory.getAbsolutePath());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Failed to create directory");
                }
            } else {
                System.out.println("Directory already exists: " + directory.getAbsolutePath());
            }

            // Filter files for PDFs and Python files
            File[] files = directory.listFiles((dir, name) -> name.endsWith(".pdf") || name.endsWith(".py"));
            List<FileInfo> fileList = new ArrayList<>();

            // If files are found, add them to the list
            if (files != null) {
                for (File file : files) {
                    fileList.add(new FileInfo(file.getName(), file.length()));
                }
            } else {
                System.out.println("No files found in the directory: " + directoryPath);
            }

            return ResponseEntity.ok(fileList);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while accessing files");
        }
    }

    // Helper class to represent file information
    public static class FileInfo {
        private String name;
        private long size;

        public FileInfo(String name, long size) {
            this.name = name;
            this.size = size;
        }

        public String getName() {
            return name;
        }

        public long getSize() {
            return size;
        }
    }
    
}

package org.arn.hdsscapture.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.arn.hdsscapture.entity.Relationship;
import org.arn.hdsscapture.exception.DataErrorException;
import org.arn.hdsscapture.exception.DataNotFoundException;
import org.arn.hdsscapture.repository.ErrorLogRepository;
import org.arn.hdsscapture.repository.RelationshipRepository;
import org.arn.hdsscapture.utils.DataWrapper;
import org.arn.hdsscapture.utils.ErrorLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/relationship")
public class RelationshipController {
	
	@Autowired
	RelationshipRepository repo;
	@Autowired
	ErrorLogRepository errorLogRepository;
	@Autowired
    private ErrorLogService errorLogService;
	
	@GetMapping("")
	public DataWrapper<Relationship> findAll() {

		List<Relationship> data = repo.findAll();

		DataWrapper<Relationship> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	
	@PostMapping("")
    public DataWrapper<Relationship> saveAll(@RequestBody DataWrapper<Relationship> data) {
        List<Relationship> savedRecords = new ArrayList<>();
        List<Relationship> errorRecords = new ArrayList<>();

        try {
            // Iterate over each Relationship record to validate/save individually
            for (Relationship record : data.getData()) {
                try {
                    // Try saving individual record
                    Relationship saved = repo.save(record);
                    savedRecords.add(saved);
                } catch (Exception e) {
                    // Log the error for the problematic record using the external service
                    String errorMessage = "Error saving record: " + e.getMessage();
                    String stackTrace = getImportantPartOfStackTrace(e); //getStackTraceAsString(e);
                    String residencyUuid = "Woman: " +(record.getIndividualA_uuid() != null ? record.getIndividualA_uuid() : "Unknown") + " | " + 
                    		 "Partner: "+(record.getIndividualB_uuid() != null ? record.getIndividualB_uuid() : "Unknown");
                    String fw = record.getFw_uuid();
                    String tb = "Relationship";
                    
                    errorLogService.logError(errorMessage, stackTrace, residencyUuid,fw,tb); // Log error details
                    
                    errorRecords.add(record); // Add the record to the error list
                }
            }

            // If there are any error records, throw an exception to roll back
            if (!errorRecords.isEmpty()) {
                throw new DataErrorException("One or more records failed to save. Transaction rolled back.");
            }

            // If everything is saved successfully, return the saved records
            DataWrapper<Relationship> response = new DataWrapper<>();
            response.setData(savedRecords);
            return response;

        } catch (DataErrorException e) {
            // Re-throw the exception after logging
            throw e;
        } catch (Exception e) {
            // In case of an unexpected exception, log it and re-throw
            String errorMessage = "Unexpected error: " + e.getMessage();
            String stackTrace = getStackTraceAsString(e);
            errorLogService.logError(errorMessage, stackTrace, null,null,null);
            
            throw new DataErrorException(errorMessage, e);
        }
    }

    // Helper method to get full stack trace as a String
    private String getStackTraceAsString(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
    
    private String getImportantPartOfStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        
        String fullStackTrace = sw.toString();
        StringBuilder importantPart = new StringBuilder();
        
        // Extract lines related to 'Caused by' and the exception type
        String[] lines = fullStackTrace.split("\n");
        boolean causeFound = false;
        
        for (String line : lines) {
            // Append the first line (the main exception) and the 'Caused by' parts
            if (line.startsWith("Caused by") || (!causeFound && line.contains(e.getClass().getSimpleName()))) {
                importantPart.append(line).append("\n");
                causeFound = true;
            }
            
            // Stop after appending the first 'Caused by' section
            if (causeFound && line.startsWith("    at ")) {
                break;
            }
        }
        
        // Return only the important part of the stack trace
        return importantPart.toString();
    }
	
	
//	@PostMapping("")
//	public DataWrapper<Relationship> saveAll(@RequestBody DataWrapper<Relationship> data) {
//		try {
//
//		List<Relationship> saved =  repo.saveAll(data.getData());
//
//		DataWrapper<Relationship> s = new DataWrapper<>();
//		s.setData(saved);
//
//		return s;
//		}catch (Exception e) {
//            // Log the API error message and full stack trace into ErrorLog entity
//            String errorMessage = "API Error: " + e.getMessage();
//            String stackTrace = getStackTraceAsString(e);
//
//            logError(errorMessage, stackTrace);
//
//            throw new DataErrorException(errorMessage, e);
//        }
//	}
//	
//	// Helper method to log the error into ErrorLog entity
//    private void logError(String errorMessage, String stackTrace) {
//        ErrorLog errorLog = new ErrorLog();
//        errorLog.setErrorMessage(errorMessage);
//        errorLog.setTimestamp(LocalDateTime.now());
//        errorLog.setStackTrace(stackTrace);
//        errorLogRepository.save(errorLog);
//    }
//
//    // Helper method to get full stack trace as a String
//    private String getStackTraceAsString(Exception e) {
//        StringWriter sw = new StringWriter();
//        PrintWriter pw = new PrintWriter(sw);
//        e.printStackTrace(pw);
//        return sw.toString();
//    }
	
	@PostMapping("/save")
	public Relationship save(@RequestBody Relationship relationship) {
		return repo.save(relationship);
	}

	@GetMapping("/{id}")
	public Relationship find(@PathVariable("id") String uuid) {

		return repo.findById(uuid).orElseThrow(() -> new DataNotFoundException(Relationship.class.getSimpleName(), uuid));
	}

	@PostMapping("/{id}")
	public Relationship replace(@RequestBody Relationship newRelationship, @PathVariable("id") String uuid) {

		return repo.save(newRelationship);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String uuid) {
		repo.deleteById(uuid);
	}


}

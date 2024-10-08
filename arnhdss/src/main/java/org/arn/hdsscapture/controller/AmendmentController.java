package org.arn.hdsscapture.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.arn.hdsscapture.entity.Amendment;
import org.arn.hdsscapture.exception.DataErrorException;
import org.arn.hdsscapture.exception.DataNotFoundException;
import org.arn.hdsscapture.repository.AmendmentRepository;
import org.arn.hdsscapture.repository.ErrorLogRepository;
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
@RequestMapping("/api/amendment")
public class AmendmentController {
	
	@Autowired
	AmendmentRepository repo;
	@Autowired
	ErrorLogRepository errorLogRepository;
	@Autowired
    private ErrorLogService errorLogService;
	
	@GetMapping("")
	public DataWrapper<Amendment> findAll() {

		List<Amendment> data = repo.findAll();

		DataWrapper<Amendment> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("")
    public DataWrapper<Amendment> saveAll(@RequestBody DataWrapper<Amendment> data) {
        List<Amendment> savedRecords = new ArrayList<>();
        List<Amendment> errorRecords = new ArrayList<>();

        try {
            // Iterate over each Amendment record to validate/save individually
            for (Amendment record : data.getData()) {
                try {
                    // Try saving individual record
                    Amendment saved = repo.save(record);
                    savedRecords.add(saved);
                } catch (Exception e) {
                    // Log the error for the problematic record using the external service
                    String errorMessage = "Error saving record: " + e.getMessage();
                    String stackTrace = getImportantPartOfStackTrace(e); //getStackTraceAsString(e);
                    String residencyUuid = 
                    	    "Individual_uuid: " + (record.getIndividual_uuid() != null ? record.getIndividual_uuid() : "Unknown") + " | " +
                    	    "Name: " + (record.getOrig_firstName() != null ? record.getOrig_firstName()+ " " + record.getOrig_lastName() : "Unknown");
                    String fw = record.getFw_uuid();
                    String tb = "Amendment";

                    errorLogService.logError(errorMessage, stackTrace, residencyUuid,fw,tb); // Log error details
                    
                    errorRecords.add(record); // Add the record to the error list
                }
            }

            // If there are any error records, throw an exception to roll back
            if (!errorRecords.isEmpty()) {
                throw new DataErrorException("One or more records failed to save. Transaction rolled back.");
            }

            // If everything is saved successfully, return the saved records
            DataWrapper<Amendment> response = new DataWrapper<>();
            response.setData(savedRecords);
            return response;

        } catch (DataErrorException e) {
            // Re-throw the exception after logging
            throw e;
        } catch (Exception e) {
            // In case of an unexpected exception, log it and re-throw
            String errorMessage = "Unexpected error: " + e.getMessage();
            String stackTrace = getImportantPartOfStackTrace(e);
            errorLogService.logError(errorMessage, stackTrace, null,null,null);
            
            throw new DataErrorException(errorMessage, e);
        }
    }

    // Helper method to get full stack trace as a String   
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
//	public DataWrapper<Amendment> saveAll(@RequestBody DataWrapper<Amendment> data) {
//
//		try {
//		List<Amendment> saved =  repo.saveAll(data.getData());
//
//		DataWrapper<Amendment> s = new DataWrapper<>();
//		s.setData(saved);
//
//		return s;
//		} catch (Exception e) {
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
	public Amendment save(@RequestBody Amendment amendment) {
		return repo.save(amendment);
	}

	@GetMapping("/{id}")
	public Amendment find(@PathVariable("id") String uuid) {

		return repo.findById(uuid).orElseThrow(() -> new DataNotFoundException(Amendment.class.getSimpleName(), uuid));
	}

	@PostMapping("/{id}")
	public Amendment replace(@RequestBody Amendment newAmendment, @PathVariable("id") String uuid) {

		return repo.save(newAmendment);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String uuid) {
		repo.deleteById(uuid);
	}


}

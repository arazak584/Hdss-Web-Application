package org.arn.hdsscapture.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.arn.hdsscapture.entity.Inmigration;
import org.arn.hdsscapture.exception.DataErrorException;
import org.arn.hdsscapture.exception.DataNotFoundException;
import org.arn.hdsscapture.repository.ErrorLogRepository;
import org.arn.hdsscapture.repository.InmigrationRepository;
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
@RequestMapping("/api/inmigration")
public class InmigrationController {
	
	@Autowired
	InmigrationRepository repo;
	@Autowired
	ErrorLogRepository errorLogRepository;
	@Autowired
    private ErrorLogService errorLogService;
	
	@GetMapping("")
	public DataWrapper<Inmigration> findAll() {

		List<Inmigration> data = repo.findAll();

		DataWrapper<Inmigration> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	


	@PostMapping("")
    public DataWrapper<Inmigration> saveAll(@RequestBody DataWrapper<Inmigration> data) {
        List<Inmigration> savedRecords = new ArrayList<>();
        List<Inmigration> errorRecords = new ArrayList<>();

        try {
            // Iterate over each Inmigration record to validate/save individually
            for (Inmigration record : data.getData()) {
                try {
                    // Try saving individual record
                    Inmigration saved = repo.save(record);
                    savedRecords.add(saved);
                } catch (Exception e) {
                    // Log the error for the problematic record using the external service
                    String errorMessage = "Error saving record: " + e.getMessage();
                    String stackTrace = getImportantPartOfStackTrace(e); //getStackTraceAsString(e);
                    String residencyUuid = (record.getResidency_uuid() != null ? record.getResidency_uuid() : "Unknown") + " - Residency_uuid " + 
                            (record.getVisit_uuid() != null ? record.getVisit_uuid() : "Unknown") + " - visit_uuid";
                    String fw = record.getFw_uuid();
                    String tb = "Inmigration";

                    errorLogService.logError(errorMessage, stackTrace, residencyUuid,fw,tb); // Log error details
                    
                    errorRecords.add(record); // Add the record to the error list
                }
            }

            // If there are any error records, throw an exception to roll back
            if (!errorRecords.isEmpty()) {
                throw new DataErrorException("One or more records failed to save. Transaction rolled back.");
            }

            // If everything is saved successfully, return the saved records
            DataWrapper<Inmigration> response = new DataWrapper<>();
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
//	public DataWrapper<Inmigration> saveAll(@RequestBody DataWrapper<Inmigration> data) {
//		try {
//
//		List<Inmigration> saved =  repo.saveAll(data.getData());
//
//		DataWrapper<Inmigration> s = new DataWrapper<>();
//		s.setData(saved);
//
//		return s;
//		} catch (Exception e) {
//            // Log the API error message and full stack trace into ErrorLog entity
//            String errorMessage = "API Error: " + e.getMessage();
//            String stackTrace = getStackTraceAsString(e);
//            String residencyUuid = extractResidencyUuid(data);
//
//            logError(errorMessage, stackTrace, residencyUuid);
//
//            throw new DataErrorException(errorMessage, e);
//        }
//	}
//	
//	// Helper method to log the error into ErrorLog entity
//    private void logError(String errorMessage, String stackTrace, String recordUuid) {
//        ErrorLog errorLog = new ErrorLog();
//        errorLog.setErrorMessage(errorMessage);
//        errorLog.setTimestamp(LocalDateTime.now());
//        errorLog.setStackTrace(stackTrace);
//        errorLog.setRecordUuid(recordUuid);
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
//    
//    // Helper method to extract residency_uuid from Inmigration data
//    private String extractResidencyUuid(DataWrapper<Inmigration> data) {
//        if (data.getData() != null && !data.getData().isEmpty()) {
//            // Assuming each Inmigration object has a getResidencyUuid method
//            return data.getData().get(0).getResidency_uuid();
//        }
//        return null; // Return null if no residency_uuid is available
//    }
	
	@PostMapping("/save")
	public Inmigration save(@RequestBody Inmigration inmigration) {
		return repo.save(inmigration);
	}

	@GetMapping("/{id}")
	public Inmigration find(@PathVariable("id") String extId) {

		return repo.findById(extId).orElseThrow(() -> new DataNotFoundException(Inmigration.class.getSimpleName(), extId));
	}

	@PostMapping("/{id}")
	public Inmigration replace(@RequestBody Inmigration newInmigration, @PathVariable("id") String extId) {

		return repo.save(newInmigration);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String extId) {
		repo.deleteById(extId);
	}


}

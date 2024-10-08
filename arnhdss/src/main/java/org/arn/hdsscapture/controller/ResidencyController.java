package org.arn.hdsscapture.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.arn.hdsscapture.entity.Residency;
import org.arn.hdsscapture.exception.DataErrorException;
import org.arn.hdsscapture.exception.DataNotFoundException;
import org.arn.hdsscapture.repository.ErrorLogRepository;
import org.arn.hdsscapture.repository.ResidencyRepository;
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
@RequestMapping("/api/residency")
public class ResidencyController {
	
	@Autowired
	ResidencyRepository repo;
	@Autowired
	ErrorLogRepository errorLogRepository;
	@Autowired
    private ErrorLogService errorLogService;
	
	@GetMapping("")
	public DataWrapper<Residency> findAll() {

		List<Residency> data = repo.findAll();

		DataWrapper<Residency> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	
	@PostMapping("")
	public DataWrapper<Residency> saveAll(@RequestBody DataWrapper<Residency> data) {
	    List<Residency> savedRecords = new ArrayList<>();
	    List<Residency> errorRecords = new ArrayList<>();
	    
	    try {
	        // New residency data from the request
	        List<Residency> newResidencyData = data.getData();

	        // Iterate through the new residency data
	        for (Residency newResidency : newResidencyData) {
	            try {
	                // Check if the UUID already exists in the database
	                Residency existingResidency = repo.findById(newResidency.getUuid()).orElse(null);

	                if (existingResidency != null && existingResidency.getEndType() == 1) {
	                    // Update existing residency with the new data
	                    existingResidency.setSocialgroup_uuid(newResidency.getSocialgroup_uuid());
	                    existingResidency.setStartDate(newResidency.getStartDate());
	                    existingResidency.setEndDate(newResidency.getEndDate());
	                    existingResidency.setEndType(newResidency.getEndType());
	                    existingResidency.setRltn_head(newResidency.getRltn_head());

	                    // Save the updated record
	                    repo.save(existingResidency);
	                    savedRecords.add(existingResidency);

	                } else if (existingResidency != null && existingResidency.getEndType() == 2) {
	                    // Query for conflicting records with the same individual_uuid but different UUID
	                    List<Residency> conflictingResidencies = repo.findConflictingRecords(newResidency.getIndividual_uuid(), newResidency.getUuid());

	                    boolean canUpdate = true;

	                    // Check for conflicting records with endType 1
	                    for (Residency conflictingResidency : conflictingResidencies) {
	                        if (conflictingResidency.getEndType() == 1) {
	                            canUpdate = false;
	                            break;
	                        }
	                    }

	                    // If no conflicting records are found, update the record
	                    if (canUpdate) {
	                        repo.save(newResidency);
	                        savedRecords.add(newResidency);
	                    }
	                } else {
	                    // If UUID doesn't exist or endType is not 2, save the new record
	                    repo.save(newResidency);
	                    savedRecords.add(newResidency);
	                }

	            } catch (Exception e) {
	                // Log the error for the problematic record
	                String errorMessage = "Error saving record: " + e.getMessage();
	                String stackTrace = getImportantPartOfStackTrace(e);
	                String residencyUuid = "Individual_uuid: " + (newResidency.getIndividual_uuid() != null ? newResidency.getIndividual_uuid() : "Unknown") + " | "
	                                     + "Location_uuid: " + (newResidency.getLocation_uuid() != null ? newResidency.getLocation_uuid() : "Unknown") + " | "
	                                     + "Socialgroup_uuid: " + (newResidency.getSocialgroup_uuid() != null ? newResidency.getSocialgroup_uuid() : "Unknown");
	                String fw = newResidency.getFw_uuid();
	                String tb = "Residency";

	                errorLogService.logError(errorMessage, stackTrace, residencyUuid, fw, tb);
	                errorRecords.add(newResidency);
	            }
	        }

	        // If there are any error records, throw an exception to roll back the transaction
	        if (!errorRecords.isEmpty()) {
	            throw new DataErrorException("One or more records failed to save. Transaction rolled back.");
	        }

	        // Return the successfully saved records
	        DataWrapper<Residency> response = new DataWrapper<>();
	        response.setData(savedRecords);
	        return response;

	    } catch (DataErrorException e) {
	        // Rethrow after logging
	        throw e;
	    } catch (Exception e) {
	        // Handle unexpected exceptions
	        String errorMessage = "Unexpected error: " + e.getMessage();
	        String stackTrace = getImportantPartOfStackTrace(e);
	        errorLogService.logError(errorMessage, stackTrace, null, null, null);
	        
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
//	public DataWrapper<Residency> saveAll(@RequestBody DataWrapper<Residency> data) {
//	    try {
//	        List<Residency> newResidencyData = data.getData();
//
//	        // Iterate through the new residency data
//	        for (Residency newResidency : newResidencyData) {
//	            // Check if the UUID already exists in the database
//	            Residency existingResidency = repo.findById(newResidency.getUuid()).orElse(null);
//	            
//	            if (existingResidency != null && existingResidency.getEndType() == 1) {
//	            	System.out.println("Condition met");
//	                // Update the existing residency with the new data
//	                existingResidency.setSocialgroup_uuid(newResidency.getSocialgroup_uuid());
//	                existingResidency.setStartDate(newResidency.getStartDate());
//	                existingResidency.setEndDate(newResidency.getEndDate());
//	                existingResidency.setEndType(newResidency.getEndType());
//	                existingResidency.setRltn_head(newResidency.getRltn_head());
//	                // Update any other fields as needed
//	                System.out.println(existingResidency);
//
//	                // Save the updated record
//	                repo.save(existingResidency);
//	            } else if (existingResidency != null && existingResidency.getEndType() == 2) {
//	                // Query the database to find any records with the same individual_uuid but different UUID
//	                // and endType equal to 1
//	                List<Residency> conflictingResidencies = repo.findConflictingRecords(newResidency.getIndividual_uuid(), newResidency.getUuid());
//
//	                boolean canUpdate = true;
//
//	                // Check if there are conflicting records with endType 1
//	                for (Residency conflictingResidency : conflictingResidencies) {
//	                    if (conflictingResidency.getEndType() == 1) {
//	                        canUpdate = false;
//	                        break;
//	                    }
//	                }
//
//	                // If no conflicting records with endType 1 are found, update the record
//	                if (canUpdate) {
//	                	
//	                	// Set the existingResidency to be equal to the newResidency
//	                    existingResidency = newResidency;
//
//	                    // Save the updated record
//	                    repo.save(existingResidency);
//
//	                }
//	            } else {
//	                // If UUID doesn't exist or endType is not 2, simply save the record
//	                repo.save(newResidency);
//	            }
//	        }
//
//	        // Return the saved data
//	        DataWrapper<Residency> s = new DataWrapper<>();
//	        s.setData(newResidencyData);
//
//	        return s;
//	    } catch (Exception e) {
//	        // Log the API error message and full stack trace into ErrorLog entity
//	        String errorMessage = "API Error: " + e.getMessage();
//	        String stackTrace = getStackTraceAsString(e);
//
//	        logError(errorMessage, stackTrace);
//
//	        throw new DataErrorException(errorMessage, e);
//	    }
//	}
//
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
	public Residency save(@RequestBody Residency residency) {
		return repo.save(residency);
	}

	@GetMapping("/{id}")
	public Residency find(@PathVariable("id") String uuid) {

		return repo.findById(uuid).orElseThrow(() -> new DataNotFoundException(Residency.class.getSimpleName(), uuid));
	}

	@PostMapping("/{id}")
	public Residency replace(@RequestBody Residency newResidency, @PathVariable("id") String uuid) {

		return repo.save(newResidency);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String uuid) {
		repo.deleteById(uuid);
	}


}

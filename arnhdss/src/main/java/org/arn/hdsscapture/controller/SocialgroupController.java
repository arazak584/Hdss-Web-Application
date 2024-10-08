package org.arn.hdsscapture.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.arn.hdsscapture.entity.Socialgroup;
import org.arn.hdsscapture.exception.DataErrorException;
import org.arn.hdsscapture.exception.DataNotFoundException;
import org.arn.hdsscapture.repository.ErrorLogRepository;
import org.arn.hdsscapture.repository.SocialgroupRepository;
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
@RequestMapping("/api/socialgroup")
public class SocialgroupController {
	
	@Autowired
	SocialgroupRepository repo;
	@Autowired
	ErrorLogRepository errorLogRepository;
	@Autowired
    private ErrorLogService errorLogService;
	
	@GetMapping("")
	public DataWrapper<Socialgroup> findAll() {

		List<Socialgroup> data = repo.findAll();

		DataWrapper<Socialgroup> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	
	@PostMapping("")
	public DataWrapper<Socialgroup> saveAll(@RequestBody DataWrapper<Socialgroup> data) {
	    List<Socialgroup> savedRecords = new ArrayList<>();
	    List<Socialgroup> errorRecords = new ArrayList<>();

	    try {
	        List<Socialgroup> saved = data.getData();
	        // Iterate over each Socialgroup record
	        for (Socialgroup socialgroup : saved) {
	            try {
	                Optional<Socialgroup> existingInd = repo.findById(socialgroup.getUuid());
	                Socialgroup existingS = existingInd.orElse(null);

	                if (existingS != null) {
	                    // UUID exists
	                    if (existingS.getExtId().equals(socialgroup.getExtId())) {
	                        // Incoming ExtId matches existing, update the existing Socialgroup	                    	
	        		        	existingS.setGroupName(socialgroup.getGroupName());
	        		        	existingS.setIndividual_uuid(socialgroup.getIndividual_uuid());
	        		        	existingS.setGroupType(socialgroup.getGroupType());
	                        repo.save(existingS);
	                    } else {
	                        // Incoming ExtId doesn't match existing, check if it exists for another UUID
	                        Optional<Socialgroup> existingByExtId = repo.findByExtId(socialgroup.getExtId());
	                        Socialgroup existingByExtIdS = existingByExtId.orElse(null);

	                        if (existingByExtIdS != null && !existingByExtIdS.getUuid().equals(socialgroup.getUuid())) {
	                            // Incoming ExtId exists for another UUID, increment last three digits
	                            incrementLastTwoDigits(socialgroup);
	                        }

	                        // Save as updated record
	                        repo.save(socialgroup);
	                    }
	                } else {
	                    // UUID doesn't exist
	                    Optional<Socialgroup> existingByExtId = repo.findByExtId(socialgroup.getExtId());
	                    Socialgroup existingByExtIdS = existingByExtId.orElse(null);

	                    if (existingByExtIdS != null) {
	                        // ExtId exists for another UUID, increment last three digits
	                    	incrementLastTwoDigits(socialgroup);
	                    }

	                    // Save as new record
	                    repo.save(socialgroup);
	                }

	                savedRecords.add(socialgroup); // Add saved record to saved list

	            } catch (Exception e) {
	                // Handle exception for Socialgroup record and log the error
	                String errorMessage = "Error saving record: " + e.getMessage();
	                String stackTrace = getImportantPartOfStackTrace(e);
	                String residencyUuid = "HHID: " +(socialgroup.getExtId() != null ? socialgroup.getExtId() : "Unknown") + " | " +
	                		 "HHNAME: "+(socialgroup.getGroupName() != null ? socialgroup.getGroupName() : "Unknown");
	                String fw = socialgroup.getFw_uuid();
	                String tb = "Socialgroup";

	                errorLogService.logError(errorMessage, stackTrace, residencyUuid, fw, tb); // Log error details
	                errorRecords.add(socialgroup); // Add the record to the error list
	            }
	        }

	        // If there are any error records, throw an exception to roll back
	        if (!errorRecords.isEmpty()) {
	            throw new DataErrorException("One or more records failed to save. Transaction rolled back.");
	        }

	        // If everything is saved successfully, return the saved records
	        DataWrapper<Socialgroup> response = new DataWrapper<>();
	        response.setData(savedRecords);
	        return response;

	    } catch (DataErrorException e) {
	        throw e;
	    } catch (Exception e) {
	        // In case of an unexpected exception, log it and re-throw
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
//	public DataWrapper<Socialgroup> saveAll(@RequestBody DataWrapper<Socialgroup> data) {
//		try {
//
//		List<Socialgroup> saved =  data.getData();
//		
//		for (Socialgroup socialgroup : saved) {
//		    Optional<Socialgroup> existingSocialgroup = repo.findById(socialgroup.getUuid());
//		    Socialgroup existingS = existingSocialgroup.orElse(null);
//
//		    if (existingS != null) {
//		        // UUID exists
//		        if (existingS.getExtId().equals(socialgroup.getExtId())) {
//		        	existingS.setGroupName(socialgroup.getGroupName());
//		        	existingS.setIndividual_uuid(socialgroup.getIndividual_uuid());
//		            // Incoming ExtId matches existing, update the existing Socialgroup
//		            repo.save(existingS);
//		        } else {
//		            // Incoming ExtId doesn't match existing, check if it exists for another UUID
//		            Optional<Socialgroup> existingByExtId = repo.findByExtId(socialgroup.getExtId());
//		            Socialgroup existingByExtIdS = existingByExtId.orElse(null);
//
//		            if (existingByExtIdS != null && !existingByExtIdS.getUuid().equals(socialgroup.getUuid())) {
//		                // Incoming ExtId exists for another UUID, increment last two digits
//		                incrementLastTwoDigits(socialgroup);
//		            }
//
//		            // Save as updated record
//		            repo.save(socialgroup);
//		        }
//		    } else {
//		        // UUID doesn't exist
//		        Optional<Socialgroup> existingByExtId = repo.findByExtId(socialgroup.getExtId());
//		        Socialgroup existingByExtIdS = existingByExtId.orElse(null);
//
//		        if (existingByExtIdS != null) {
//		            // ExtId exists for another UUID, increment last two digits
//		            incrementLastTwoDigits(socialgroup);
//		        }
//
//		        // Save as new record
//		        repo.save(socialgroup);
//		    }
//		}
//
//		
//
//		DataWrapper<Socialgroup> s = new DataWrapper<>();
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
	public Socialgroup save(@RequestBody Socialgroup socialgroup) {
		return repo.save(socialgroup);
	}

	@GetMapping("/{id}")
	public Socialgroup find(@PathVariable("id") String extId) {

		return repo.findById(extId).orElseThrow(() -> new DataNotFoundException(Socialgroup.class.getSimpleName(), extId));
	}

	@PostMapping("/{id}")
	public Socialgroup replace(@RequestBody Socialgroup newSocialgroup, @PathVariable("id") String extId) {

		return repo.save(newSocialgroup);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String extId) {
		repo.deleteById(extId);
	}
	
	private void incrementLastTwoDigits(Socialgroup socialgroup) {
	    String extId = socialgroup.getExtId();
	    
	    // Start a loop to check for unique extId
	    while (true) {
	        // Extract the last three digits from extId
	        String lastTwoDigits = extId.substring(extId.length() - 2);	        
	        // Increment the last three digits
	        int incrementedValue = Integer.parseInt(lastTwoDigits) + 1;	        
	        // Ensure the incremented value does not exceed 99
	        incrementedValue = incrementedValue % 100;	        
	        // Format the incremented value to have three digits
	        String formattedIncrementedValue = String.format("%02d", incrementedValue);	        
	        // Replace the last three digits in extId
	        String newExtId = extId.substring(0, extId.length() - 2) + formattedIncrementedValue;	        
	        // Check if the new extId already exists in the database
	        Optional<Socialgroup> existingHOFID = repo.findByExtId(newExtId);        
	        if (!existingHOFID.isPresent()) {
	            // If new extId is unique, set it and break out of the loop
	            socialgroup.setExtId(newExtId);
	            break;
	        }	        
	        // Otherwise, loop again and increment the last three digits further
	        extId = newExtId;
	    }
	}

}

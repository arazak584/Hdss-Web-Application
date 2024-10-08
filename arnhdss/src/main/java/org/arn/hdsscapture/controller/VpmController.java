package org.arn.hdsscapture.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.arn.hdsscapture.entity.Vpm;
import org.arn.hdsscapture.exception.DataErrorException;
import org.arn.hdsscapture.exception.DataNotFoundException;
import org.arn.hdsscapture.repository.ErrorLogRepository;
import org.arn.hdsscapture.repository.VpmRepository;
import org.arn.hdsscapture.utils.DataWrapper;
import org.arn.hdsscapture.utils.ErrorLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/vpm")
public class VpmController {
	
	@Autowired
	VpmRepository repo;
	@Autowired
	ErrorLogRepository errorLogRepository;
	@Autowired
    private ErrorLogService errorLogService;
	
	@GetMapping("")
	public DataWrapper<Vpm> findAll() {

		List<Vpm> data = repo.findAll();

		DataWrapper<Vpm> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	
	@PostMapping("")
	public ResponseEntity<DataWrapper<Vpm>> saveAll(@RequestBody DataWrapper<Vpm> data) {
	    List<Vpm> savedRecords = new ArrayList<>();
	    List<Vpm> errorRecords = new ArrayList<>();

	    try {
	        List<Vpm> recordsToSave = data.getData();

	        for (Vpm vpm : recordsToSave) {
	            try {
	                Optional<Vpm> existingVpmOptional = repo.findById(vpm.getIndividual_uuid());
	                Vpm existingVpm = existingVpmOptional.orElse(null);

	                if (existingVpm != null) {
	                    // Logic to handle existing records
	                    if (existingVpm.getComplete() == null && vpm.getComplete() == 1) {
	                        System.out.println("Updating existing record 1...");
	                        savedRecords.add(repo.save(vpm));
	                    } else if (existingVpm.getComplete() == 1 && vpm.getComplete() == 1) {
	                        System.out.println("Updating existing record 2...");
	                        savedRecords.add(repo.save(vpm));
	                    } else if (existingVpm.getComplete() == 1 && vpm.getComplete() == 2) {
	                        System.out.println("Deleting existing record...");
	                        repo.delete(existingVpm);
	                        continue; // Skip to next iteration, since the record is deleted
	                    } else if (vpm.getComplete() == 0) {
	                        // No action needed for vpm with complete == 0
	                    }
	                } else {
	                    // New record logic
	                    if (vpm.getComplete() == 1) {
	                        System.out.println("Save New...");
	                        savedRecords.add(repo.save(vpm));
	                    } else if (vpm.getComplete() == 0 || vpm.getComplete() == 2) {
	                        // Skip or ignore vpm records with complete == 0 or 2 (as per your logic)
	                    }
	                }

	            } catch (Exception e) {
	                // Log the error for the problematic record using the external service
	                String errorMessage = "Error saving record: " + e.getMessage();
	                String stackTrace = getImportantPartOfStackTrace(e); 
	                String residencyUuid = "Individual_uuid: " + (vpm.getIndividual_uuid() != null ? vpm.getIndividual_uuid() : "Unknown") + " | " + 
	                        "Visit_uuid: " +(vpm.getVisit_uuid() != null ? vpm.getVisit_uuid() : "Unknown");
	                String fw = vpm.getFw_uuid();
	                String tb = "Vpm";

	                errorLogService.logError(errorMessage, stackTrace, residencyUuid, fw, tb); // Log error details
	                errorRecords.add(vpm); // Add the record to the error list
	            }
	        }

	        // If there are any error records, return a 400 Bad Request
	        if (!errorRecords.isEmpty()) {
	            throw new DataErrorException("One or more records failed to save. Transaction rolled back.");
	        }

	        // If everything is saved successfully, return the saved records with a 200 OK status
	        DataWrapper<Vpm> responseData = new DataWrapper<>();
	        responseData.setData(savedRecords);
	        return ResponseEntity.ok(responseData);

	    } catch (DataErrorException e) {
	        // If there are error records, return a 400 Bad Request
	        String errorMessage = "One or more records failed to save.";
	        String stackTrace = getStackTraceAsString(e);
	        errorLogService.logError(errorMessage, stackTrace, null, null, null);

	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // You can customize the error response body as needed
	    } catch (Exception e) {
	        // In case of an unexpected exception, log it and return a 500 Internal Server Error
	        String errorMessage = "Unexpected error: " + e.getMessage();
	        String stackTrace = getStackTraceAsString(e);
	        errorLogService.logError(errorMessage, stackTrace, null, null, null);

	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // You can customize the error response body as needed
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
//	public DataWrapper<Vpm> saveAll(@RequestBody DataWrapper<Vpm> data) {
//		try {
//
//		List<Vpm> saved =  data.getData();
//		
//		
//		for (Vpm Vpm : saved) {
//            Optional<Vpm> existingVpmOptional = repo.findById(Vpm.getUuid());
//            Vpm existingVpm = existingVpmOptional.orElse(null);
//
//            if (existingVpm != null) {
//            	if (existingVpm.getComplete() == null && Vpm.getComplete()==1) {
//            		System.out.println("Updating existing record 1...");
//            		repo.save(Vpm);
//            	}else if (existingVpm.getComplete() == 1 && Vpm.getComplete()==1) {
//            		System.out.println("Updating existing record 2...");
//                    repo.save(Vpm);
//                }else if (existingVpm.getComplete() == 1 && Vpm.getComplete()==2) {
//                	System.out.println("Deleting existing record...");
//                	repo.delete(existingVpm);
//                	continue;
//                }else if (Vpm.getComplete() == 0) {     	
//               } 
//            } else { 
//            	if (existingVpm == null ) {
//            		if (Vpm.getComplete() == 1) {
//            	System.out.println("Save New...");
//                repo.save(Vpm);
//            	}else if (Vpm.getComplete() == 2) {
//            	
//            }else if (Vpm.getComplete() == 0) {
//            	
//            } 
//            }
//		}
//        }
//		
//
//		DataWrapper<Vpm> s = new DataWrapper<>();
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
	public Vpm save(@RequestBody Vpm vpm) {
		return repo.save(vpm);
	}

	@GetMapping("/{id}")
	public Vpm find(@PathVariable("id") String extId) {

		return repo.findById(extId).orElseThrow(() -> new DataNotFoundException(Vpm.class.getSimpleName(), extId));
	}

	@PostMapping("/{id}")
	public Vpm replace(@RequestBody Vpm newVpm, @PathVariable("id") String extId) {

		return repo.save(newVpm);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String extId) {
		repo.deleteById(extId);
	}


}

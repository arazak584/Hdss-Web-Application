package org.arn.hdsscapture.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.arn.hdsscapture.entity.Outmigration;
import org.arn.hdsscapture.entity.Outmigration;
import org.arn.hdsscapture.entity.Residency;
import org.arn.hdsscapture.exception.DataErrorException;
import org.arn.hdsscapture.exception.DataNotFoundException;
import org.arn.hdsscapture.repository.ErrorLogRepository;
import org.arn.hdsscapture.repository.OutmigrationRepository;
import org.arn.hdsscapture.repository.ResidencyRepository;
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
@RequestMapping("/api/outmigration")
public class OutmigrationController {
	
	@Autowired
	OutmigrationRepository repo;
	@Autowired
	ErrorLogRepository errorLogRepository;
	@Autowired
    private ResidencyRepository residencyRepository;
	@Autowired
    private ErrorLogService errorLogService;
	
	@GetMapping("")
	public DataWrapper<Outmigration> findAll() {

		List<Outmigration> data = repo.findAll();

		DataWrapper<Outmigration> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("")
	public ResponseEntity<DataWrapper<Outmigration>> saveAll(@RequestBody DataWrapper<Outmigration> data) {
	    List<Outmigration> savedRecords = new ArrayList<>();
	    List<Outmigration> errorRecords = new ArrayList<>();

	    try {
	        List<Outmigration> recordsToSave = data.getData();

	        for (Outmigration outmigration : recordsToSave) {
	            try {
	                Optional<Outmigration> existingOutmigrationOptional = repo.findById(outmigration.getResidency_uuid());
	                Outmigration existingOutmigration = existingOutmigrationOptional.orElse(null);
	                

	                if (existingOutmigration != null) {
	                	Residency associatedResidency = residencyRepository.findByUuid(existingOutmigration.getResidency_uuid());
	                    // Logic to handle existing records
	                    if (existingOutmigration.getComplete() == null && outmigration.getComplete() == 1) {
	                        System.out.println("Updating existing record 1...");
	                        if (existingOutmigration.getResidency_uuid() != null && associatedResidency != null && associatedResidency.getEndDate() != null) {
	                            outmigration.setRecordedDate(associatedResidency.getEndDate());
	                            System.out.println("Updating End Date for existingOmg...");
	                        }
	                        savedRecords.add(repo.save(outmigration));
	                    } else if (existingOutmigration.getComplete() == 1 && outmigration.getComplete() == 1) {
	                        System.out.println("Updating existing record 2...");
	                        if (existingOutmigration.getResidency_uuid() != null && associatedResidency != null && associatedResidency.getEndDate() != null) {
	                            outmigration.setRecordedDate(associatedResidency.getEndDate());
	                            System.out.println("Updating End Date for existingOmg...");
	                        }
	                        savedRecords.add(repo.save(outmigration));
	                    } else if (existingOutmigration.getComplete() == 1 && outmigration.getComplete() == 2) {
	                        System.out.println("Deleting existing record...");
	                        repo.delete(existingOutmigration);
	                        continue; // Skip to next iteration, since the record is deleted
	                    } else if (outmigration.getComplete() == 0) {
	                        // No action needed for outmigration with complete == 0
	                    }
	                } else {
	                    // New record logic
	                    if (outmigration.getComplete() == 1) {
	                        System.out.println("Save New Omg...");
	                        savedRecords.add(repo.save(outmigration));
	                    } else if (outmigration.getComplete() == 0 || outmigration.getComplete() == 2) {
	                        // Skip or ignore outmigration records with complete == 0 or 2 (as per your logic)
	                    }
	                }

	            } catch (Exception e) {
	                // Log the error for the problematic record using the external service
	                String errorMessage = "Error saving record: " + e.getMessage();
	                String stackTrace = getImportantPartOfStackTrace(e); 
	                String residencyUuid = "Individual_uuid: " + (outmigration.getIndividual_uuid() != null ? outmigration.getIndividual_uuid() : "Unknown") + " | " + 
	                        "Visit_uuid: " +(outmigration.getVisit_uuid() != null ? outmigration.getVisit_uuid() : "Unknown");
	                String fw = outmigration.getFw_uuid();
	                String tb = "Outmigration";

	                errorLogService.logError(errorMessage, stackTrace, residencyUuid, fw, tb); // Log error details
	                errorRecords.add(outmigration); // Add the record to the error list
	            }
	        }

	        // If there are any error records, return a 400 Bad Request
	        if (!errorRecords.isEmpty()) {
	            throw new DataErrorException("One or more records failed to save. Transaction rolled back.");
	        }

	        // If everything is saved successfully, return the saved records with a 200 OK status
	        DataWrapper<Outmigration> responseData = new DataWrapper<>();
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
//	public ResponseEntity<DataWrapper<Outmigration>> saveAll(@RequestBody DataWrapper<Outmigration> data) {
//	    List<Outmigration> savedRecords = new ArrayList<>();
//	    List<Outmigration> errorRecords = new ArrayList<>();
//
//	    try {
//	        List<Outmigration> recordsToSave = data.getData();
//
//	        for (Outmigration outmigration : recordsToSave) {
//	            try {
//	                Optional<Outmigration> existingOutmigrationOptional = repo.findById(outmigration.getResidency_uuid());
//	                Outmigration existingOmg = existingOutmigrationOptional.orElse(null);
//
//	                if (existingOmg != null) {
//	                    Residency associatedResidency = residencyRepository.findByUuid(existingOmg.getResidency_uuid());
//	                    if (existingOmg.getComplete() == null && outmigration.getComplete() == 1) {
//	                        System.out.println("Updating existing record...1");
//	                        if (existingOmg.getResidency_uuid() != null && associatedResidency != null && associatedResidency.getEndDate() != null) {
//	                            outmigration.setRecordedDate(associatedResidency.getEndDate());
//	                            System.out.println("Updating End Date for existingOmg...");
//	                        }
//	                        repo.save(outmigration);
//	                    } else if (existingOmg.getComplete() == 1 && outmigration.getComplete() == 1) {
//	                        System.out.println("Updating existing record...2");
//	                        if (existingOmg.getResidency_uuid() != null && associatedResidency != null && associatedResidency.getEndDate() != null) {
//	                            outmigration.setRecordedDate(associatedResidency.getEndDate());
//	                            System.out.println("Updating End Date for existingOmg...");
//	                        }
//	                        repo.save(outmigration);
//	                    } else if (existingOmg.getComplete() == 1 && outmigration.getComplete() == 2) {
//	                        System.out.println("Deleting existing record...");
//	                        repo.delete(existingOmg);
//	                        continue;
//	                    } else if (outmigration.getComplete() == 0) {
//	                        System.out.println("Condition for omg.getComplete() == 0 is true...");
//	                    } 
//	                } else {
//	                    // New record logic
//	                    if (outmigration.getComplete() == 1) {
//	                        System.out.println("Save New...");
//	                        savedRecords.add(repo.save(outmigration));
//	                    } else if (outmigration.getComplete() == 0 || outmigration.getComplete() == 2) {
//	                        // Skip or ignore outmigration records with complete == 0 or 2 (as per your logic)
//	                    }
//	                }
//	            } catch (Exception e) {
//	                String errorMessage = "Error saving record: " + e.getMessage();
//	                String stackTrace = getImportantPartOfStackTrace(e);
//	                String residencyUuid = "Individual_uuid: " + (outmigration.getIndividual_uuid() != null ? outmigration.getIndividual_uuid() : "Unknown") + " | " +
//	                        "Visit_uuid: " + (outmigration.getVisit_uuid() != null ? outmigration.getVisit_uuid() : "Unknown");
//	                String fw = outmigration.getFw_uuid();
//	                String tb = "Outmigration";
//
//	                errorLogService.logError(errorMessage, stackTrace, residencyUuid, fw, tb);
//	                errorRecords.add(outmigration);
//	            }
//	        }
//
//	        // If there are any error records, throw an exception to roll back
//	        if (!errorRecords.isEmpty()) {
//	            throw new DataErrorException("One or more records failed to save. Transaction rolled back.");
//	        }
//
//	        // If everything is saved successfully, return the saved records
//	        DataWrapper<Outmigration> responseData = new DataWrapper<>();
//	        responseData.setData(savedRecords);
//	        return ResponseEntity.ok(responseData);
//
//	    } catch (DataErrorException e) {
//	        String errorMessage = "One or more records failed to save.";
//	        String stackTrace = getStackTraceAsString(e);
//	        errorLogService.logError(errorMessage, stackTrace, null, null, null);
//
//	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//	    } catch (Exception e) {
//	        String errorMessage = "Unexpected error: " + e.getMessage();
//	        String stackTrace = getStackTraceAsString(e);
//	        errorLogService.logError(errorMessage, stackTrace, null, null, null);
//
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//	    }
//	}
//
//
//
//    // Helper method to get full stack trace as a String
//    private String getStackTraceAsString(Exception e) {
//        StringWriter sw = new StringWriter();
//        PrintWriter pw = new PrintWriter(sw);
//        e.printStackTrace(pw);
//        return sw.toString();
//    }
//    
//    private String getImportantPartOfStackTrace(Exception e) {
//        StringWriter sw = new StringWriter();
//        PrintWriter pw = new PrintWriter(sw);
//        e.printStackTrace(pw);
//        
//        String fullStackTrace = sw.toString();
//        StringBuilder importantPart = new StringBuilder();
//        
//        // Extract lines related to 'Caused by' and the exception type
//        String[] lines = fullStackTrace.split("\n");
//        boolean causeFound = false;
//        
//        for (String line : lines) {
//            // Append the first line (the main exception) and the 'Caused by' parts
//            if (line.startsWith("Caused by") || (!causeFound && line.contains(e.getClass().getSimpleName()))) {
//                importantPart.append(line).append("\n");
//                causeFound = true;
//            }
//            
//            // Stop after appending the first 'Caused by' section
//            if (causeFound && line.startsWith("    at ")) {
//                break;
//            }
//        }
//        
//        // Return only the important part of the stack trace
//        return importantPart.toString();
//    }
	
//	@PostMapping("")
//    public DataWrapper<Outmigration> saveAll(@RequestBody DataWrapper<Outmigration> data) {
//        try {
//            List<Outmigration> newOutmigrationData = data.getData();
//
//            
//            for (Outmigration omg : newOutmigrationData) {  
//            	
//            	Optional<Outmigration> existingOmgOptional = repo.findById(omg.getResidency_uuid());
//                Outmigration existingOmg = existingOmgOptional.orElse(null);
//                
//                if (existingOmg != null) {
//                	if (existingOmg.getComplete() == null && omg.getComplete()==1) {
//                		System.out.println("Updating existing record...");
//                        repo.save(omg);
//                	}else if (existingOmg.getComplete() == 1 && omg.getComplete()==1) {
//                		System.out.println("Updating existing record...");
//                        repo.save(omg);
//                	}else if (existingOmg.getComplete() == 1 && omg.getComplete()==2) {
//                    	System.out.println("Deleting existing record...");
//                    	repo.delete(existingOmg);
//                    	continue;
//                    }else if (omg.getComplete() == 0) {
//                    	System.out.println("Condition for omg.getComplete() == 0 is true...");
//                    	
//                    }else if (existingOmg.getResidency_uuid() != null) {
//                    	Residency associatedResidency = residencyRepository.findByUuid(existingOmg.getResidency_uuid());
//                        if (associatedResidency != null && associatedResidency.getEndDate() != null) {
//                            existingOmg.setRecordedDate(associatedResidency.getEndDate());
//                            System.out.println("Updating End Date for existingOmg...");
//                            repo.save(existingOmg);
//                        }
//                    }
//                	
//                }else {
//                	if ( existingOmg == null ) {               		
//                		if (omg.getComplete() == 1) {
//                			System.out.println("Save new record...");
//                            repo.save(omg);	
//                		}else if (omg.getComplete() == 2) {
//                			
//                		}else if (omg.getComplete() == 0) {
//                			
//                		}
//                	}
//                }               
//     
//              
//            }
//
//            // Return the saved data
//            DataWrapper<Outmigration> s = new DataWrapper<>();
//            s.setData(newOutmigrationData);
//
//            return s;
//        } catch (Exception e) {
//            // Log the API error message and full stack trace into ErrorLog entity
//            String errorMessage = "API Error: " + e.getMessage();
//            String stackTrace = getStackTraceAsString(e);
//
//            logError(errorMessage, stackTrace);
//
//            throw new DataErrorException(errorMessage, e);
//        }
//    }
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
	public Outmigration save(@RequestBody Outmigration outmigration) {
		return repo.save(outmigration);
	}

	@GetMapping("/{id}")
	public Outmigration find(@PathVariable("id") String extId) {

		return repo.findById(extId).orElseThrow(() -> new DataNotFoundException(Outmigration.class.getSimpleName(), extId));
	}

	@PostMapping("/{id}")
	public Outmigration replace(@RequestBody Outmigration newOutmigration, @PathVariable("id") String extId) {

		return repo.save(newOutmigration);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String extId) {
		repo.deleteById(extId);
	}

}

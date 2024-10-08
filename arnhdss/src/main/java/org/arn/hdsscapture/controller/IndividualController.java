package org.arn.hdsscapture.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.arn.hdsscapture.entity.Individual;
import org.arn.hdsscapture.exception.DataErrorException;
import org.arn.hdsscapture.exception.DataNotFoundException;
import org.arn.hdsscapture.repository.ErrorLogRepository;
import org.arn.hdsscapture.repository.IndividualRepository;
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
@RequestMapping("/api/individual")
public class IndividualController {
	
	@Autowired
	IndividualRepository repo;
	@Autowired
	ErrorLogRepository errorLogRepository;
	@Autowired
    private ErrorLogService errorLogService;
	
	@GetMapping("")
	public DataWrapper<Individual> findAll() {

		List<Individual> data = repo.findAll();

		DataWrapper<Individual> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	
	@PostMapping("")
	public DataWrapper<Individual> saveAll(@RequestBody DataWrapper<Individual> data) {
	    List<Individual> savedRecords = new ArrayList<>();
	    List<Individual> errorRecords = new ArrayList<>();

	    try {
	        // Sort the list of Individual objects by dob in ascending order
	        List<Individual> sortedIndividuals = data.getData()
	                .stream()
	                .sorted(Comparator.comparing(Individual::getDob))
	                .collect(Collectors.toList());

	        // Iterate over each Individual record
	        for (Individual individual : sortedIndividuals) {
	            try {
	                Optional<Individual> existingInd = repo.findById(individual.getUuid());
	                Individual existingS = existingInd.orElse(null);

	                if (existingS != null) {
	                    // UUID exists
	                    if (existingS.getExtId().equals(individual.getExtId())) {
	                        // Incoming ExtId matches existing, update the existing Individual
	                        existingS.setFirstName(individual.getFirstName());
	                        existingS.setLastName(individual.getLastName());
	                        existingS.setGender(individual.getGender());
	                        existingS.setOtherName(individual.getOtherName());
	                        existingS.setDob(individual.getDob());
	                        existingS.setGhanacard(individual.getGhanacard());
	                        existingS.setMother_uuid(individual.getMother_uuid());
	                        existingS.setFather_uuid(individual.getFather_uuid());
	                        repo.save(existingS);
	                    } else {
	                        // Incoming ExtId doesn't match existing, check if it exists for another UUID
	                        Optional<Individual> existingByExtId = repo.findByExtId(individual.getExtId());
	                        Individual existingByExtIdS = existingByExtId.orElse(null);

	                        if (existingByExtIdS != null && !existingByExtIdS.getUuid().equals(individual.getUuid())) {
	                            // Incoming ExtId exists for another UUID, increment last three digits
	                            incrementLastThreeDigits(individual);
	                        }

	                        // Save as updated record
	                        repo.save(individual);
	                    }
	                } else {
	                    // UUID doesn't exist
	                    Optional<Individual> existingByExtId = repo.findByExtId(individual.getExtId());
	                    Individual existingByExtIdS = existingByExtId.orElse(null);

	                    if (existingByExtIdS != null) {
	                        // ExtId exists for another UUID, increment last three digits
	                        incrementLastThreeDigits(individual);
	                    }

	                    // Save as new record
	                    repo.save(individual);
	                }

	                savedRecords.add(individual); // Add saved record to saved list

	            } catch (Exception e) {
	                // Handle exception for individual record and log the error
	                String errorMessage = "Error saving record: " + e.getMessage();
	                String stackTrace = getImportantPartOfStackTrace(e);
	                String residencyUuid =  "PermID: " +(individual.getExtId() != null ? individual.getExtId() : "Unknown")+ " | " +
	                		 "Name: "+(individual.getFirstName() != null ? individual.getFirstName()+ " " + individual.getLastName() : "Unknown");
	                String fw = individual.getFw_uuid();
	                String tb = "Individual";

	                errorLogService.logError(errorMessage, stackTrace, residencyUuid, fw, tb); // Log error details
	                errorRecords.add(individual); // Add the record to the error list
	            }
	        }

	        // If there are any error records, throw an exception to roll back
	        if (!errorRecords.isEmpty()) {
	            throw new DataErrorException("One or more records failed to save. Transaction rolled back.");
	        }

	        // If everything is saved successfully, return the saved records
	        DataWrapper<Individual> response = new DataWrapper<>();
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
//	public DataWrapper<Individual> saveAll(@RequestBody DataWrapper<Individual> data) {
//		try {
//
//			// Sort the list of Individual objects by dob in ascending order
//			List<Individual> sortedIndividuals = data.getData()
//	                .stream()
//	                .sorted(Comparator.comparing(Individual::getDob))
//	                .collect(Collectors.toList());
//
//	        for (Individual individual : sortedIndividuals) {
//	            Optional<Individual> existingInd = repo.findById(individual.getUuid());
//	            Individual existingS = existingInd.orElse(null);
//
//	            if (existingS != null) {
//	                // UUID exists
//	                if (existingS.getExtId().equals(individual.getExtId())) {
//	                    // Incoming ExtId matches existing, update the existing Individual
//	                	existingS.setFirstName(individual.getFirstName());
//	                	existingS.setLastName(individual.getLastName());
//	                	existingS.setGender(individual.getGender());
//	                	existingS.setOtherName(individual.getOtherName());
//	                	existingS.setDob(individual.getDob());
//	                	existingS.setGhanacard(individual.getGhanacard());
//	                    repo.save(existingS);
//	                } else {
//	                    // Incoming ExtId doesn't match existing, check if it exists for another UUID
//	                    Optional<Individual> existingByExtId = repo.findByExtId(individual.getExtId());
//	                    Individual existingByExtIdS = existingByExtId.orElse(null);
//
//	                    if (existingByExtIdS != null && !existingByExtIdS.getUuid().equals(individual.getUuid())) {
//	                        // Incoming ExtId exists for another UUID, increment last three digits
//	                        incrementLastThreeDigits(individual);
//	                    }
//
//	                    // Save as updated record
//	                    repo.save(individual);
//	                }
//	            } else {
//	                // UUID doesn't exist
//	                Optional<Individual> existingByExtId = repo.findByExtId(individual.getExtId());
//	                Individual existingByExtIdS = existingByExtId.orElse(null);
//
//	                if (existingByExtIdS != null) {
//	                    // ExtId exists for another UUID, increment last three digits
//	                    incrementLastThreeDigits(individual);
//	                }
//
//	                // Save as new record
//	                repo.save(individual);
//	            }
//	        }
//
//	        DataWrapper<Individual> s = new DataWrapper<>();
//	        s.setData(sortedIndividuals);
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

	
//	private void incrementLastThreeDigits(Individual individual) {
//	    // Extract the last three digits from extId
//	    String extId = individual.getExtId();
//	    String lastThreeDigits = extId.substring(extId.length() - 3);
//	    // Increment the last three digits
//	    int incrementedValue = Integer.parseInt(lastThreeDigits) + 1;
//	    // Ensure the incremented value does not exceed 999
//	    incrementedValue = incrementedValue % 1000;
//	    // Format the incremented value to have three digits
//	    String formattedIncrementedValue = String.format("%03d", incrementedValue);
//	    // Replace the last three digits in extId
//	    individual.setExtId(extId.substring(0, extId.length() - 3) + formattedIncrementedValue);
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
	public Individual save(@RequestBody Individual individual) {
		return repo.save(individual);
	}

	@GetMapping("/{id}")
	public Individual find(@PathVariable("id") String extId) {

		return repo.findById(extId).orElseThrow(() -> new DataNotFoundException(Individual.class.getSimpleName(), extId));
	}

	@PostMapping("/{id}")
	public Individual replace(@RequestBody Individual newIndividual, @PathVariable("id") String extId) {

		return repo.save(newIndividual);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String extId) {
		repo.deleteById(extId);
	}


	private void incrementLastThreeDigits(Individual individual) {
	    String extId = individual.getExtId();
	    
	    // Start a loop to check for unique extId
	    while (true) {
	        // Extract the last three digits from extId
	        String lastThreeDigits = extId.substring(extId.length() - 3);	        
	        // Increment the last three digits
	        int incrementedValue = Integer.parseInt(lastThreeDigits) + 1;	        
	        // Ensure the incremented value does not exceed 999
	        incrementedValue = incrementedValue % 1000;	        
	        // Format the incremented value to have three digits
	        String formattedIncrementedValue = String.format("%03d", incrementedValue);	        
	        // Replace the last three digits in extId
	        String newExtId = extId.substring(0, extId.length() - 3) + formattedIncrementedValue;	        
	        // Check if the new extId already exists in the database
	        Optional<Individual> existingIndividual = repo.findByExtId(newExtId);        
	        if (!existingIndividual.isPresent()) {
	            // If new extId is unique, set it and break out of the loop
	            individual.setExtId(newExtId);
	            break;
	        }	        
	        // Otherwise, loop again and increment the last three digits further
	        extId = newExtId;
	    }
	}
}

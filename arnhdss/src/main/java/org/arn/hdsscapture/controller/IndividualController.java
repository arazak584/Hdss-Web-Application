package org.arn.hdsscapture.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.arn.hdsscapture.entity.ErrorLog;
import org.arn.hdsscapture.entity.Individual;
import org.arn.hdsscapture.exception.DataErrorException;
import org.arn.hdsscapture.exception.DataNotFoundException;
import org.arn.hdsscapture.repository.ErrorLogRepository;
import org.arn.hdsscapture.repository.IndividualRepository;
import org.arn.hdsscapture.utils.DataWrapper;
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
	
	@GetMapping("")
	public DataWrapper<Individual> findAll() {

		List<Individual> data = repo.findAll();

		DataWrapper<Individual> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("")
	public DataWrapper<Individual> saveAll(@RequestBody DataWrapper<Individual> data) {
		try {

			// Sort the list of Individual objects by dob in ascending order
			List<Individual> sortedIndividuals = data.getData()
	                .stream()
	                .sorted(Comparator.comparing(Individual::getDob))
	                .collect(Collectors.toList());

	        for (Individual individual : sortedIndividuals) {
	            Optional<Individual> existingInd = repo.findById(individual.getUuid());
	            Individual existingS = existingInd.orElse(null);

	            if (existingS != null) {
	                // UUID exists
	                if (existingS.getExtId().equals(individual.getExtId())) {
	                    // Incoming ExtId matches existing, update the existing Individual
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
	        }

	        DataWrapper<Individual> s = new DataWrapper<>();
	        s.setData(sortedIndividuals);

		return s;
		} catch (Exception e) {
            // Log the API error message and full stack trace into ErrorLog entity
            String errorMessage = "API Error: " + e.getMessage();
            String stackTrace = getStackTraceAsString(e);

            logError(errorMessage, stackTrace);

            throw new DataErrorException(errorMessage, e);
        }
	}
	
	private void incrementLastThreeDigits(Individual individual) {
	    // Extract the last three digits from extId
	    String extId = individual.getExtId();
	    String lastThreeDigits = extId.substring(extId.length() - 3);
	    // Increment the last three digits
	    int incrementedValue = Integer.parseInt(lastThreeDigits) + 1;
	    // Ensure the incremented value does not exceed 999
	    incrementedValue = incrementedValue % 1000;
	    // Format the incremented value to have three digits
	    String formattedIncrementedValue = String.format("%03d", incrementedValue);
	    // Replace the last three digits in extId
	    individual.setExtId(extId.substring(0, extId.length() - 3) + formattedIncrementedValue);
	}
    
	
	// Helper method to log the error into ErrorLog entity
    private void logError(String errorMessage, String stackTrace) {
        ErrorLog errorLog = new ErrorLog();
        errorLog.setErrorMessage(errorMessage);
        errorLog.setTimestamp(LocalDateTime.now());
        errorLog.setStackTrace(stackTrace);
        errorLogRepository.save(errorLog);
    }

    // Helper method to get full stack trace as a String
    private String getStackTraceAsString(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
	
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


}

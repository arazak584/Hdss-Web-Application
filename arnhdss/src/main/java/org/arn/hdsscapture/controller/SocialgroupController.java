package org.arn.hdsscapture.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.arn.hdsscapture.entity.ErrorLog;
import org.arn.hdsscapture.entity.Socialgroup;
import org.arn.hdsscapture.exception.DataErrorException;
import org.arn.hdsscapture.exception.DataNotFoundException;
import org.arn.hdsscapture.repository.ErrorLogRepository;
import org.arn.hdsscapture.repository.SocialgroupRepository;
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
@RequestMapping("/api/socialgroup")
public class SocialgroupController {
	
	@Autowired
	SocialgroupRepository repo;
	@Autowired
	ErrorLogRepository errorLogRepository;
	
	@GetMapping("")
	public DataWrapper<Socialgroup> findAll() {

		List<Socialgroup> data = repo.findAll();

		DataWrapper<Socialgroup> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("")
	public DataWrapper<Socialgroup> saveAll(@RequestBody DataWrapper<Socialgroup> data) {
		try {

		List<Socialgroup> saved =  data.getData();
		
		for (Socialgroup socialgroup : saved) {
		    Optional<Socialgroup> existingSocialgroup = repo.findById(socialgroup.getUuid());
		    Socialgroup existingS = existingSocialgroup.orElse(null);

		    if (existingS != null) {
		        // UUID exists
		        if (existingS.getExtId().equals(socialgroup.getExtId())) {
		        	existingS.setGroupName(socialgroup.getGroupName());
		        	existingS.setIndividual_uuid(socialgroup.getIndividual_uuid());
		            // Incoming ExtId matches existing, update the existing Socialgroup
		            repo.save(existingS);
		        } else {
		            // Incoming ExtId doesn't match existing, check if it exists for another UUID
		            Optional<Socialgroup> existingByExtId = repo.findByExtId(socialgroup.getExtId());
		            Socialgroup existingByExtIdS = existingByExtId.orElse(null);

		            if (existingByExtIdS != null && !existingByExtIdS.getUuid().equals(socialgroup.getUuid())) {
		                // Incoming ExtId exists for another UUID, increment last two digits
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
		            // ExtId exists for another UUID, increment last two digits
		            incrementLastTwoDigits(socialgroup);
		        }

		        // Save as new record
		        repo.save(socialgroup);
		    }
		}

		

		DataWrapper<Socialgroup> s = new DataWrapper<>();
		s.setData(saved);

		return s;
		}catch (Exception e) {
            // Log the API error message and full stack trace into ErrorLog entity
            String errorMessage = "API Error: " + e.getMessage();
            String stackTrace = getStackTraceAsString(e);

            logError(errorMessage, stackTrace);

            throw new DataErrorException(errorMessage, e);
        }
	}
	
    
	private void incrementLastTwoDigits(Socialgroup socialgroup) {
	    // Extract the last two digits from extId
	    String extId = socialgroup.getExtId();
	    String lastTwoDigits = extId.substring(extId.length() - 2);	    
	    // Increment the last two digits
	    int incrementedValue = Integer.parseInt(lastTwoDigits) + 1;	    
	    // Ensure the incremented value does not exceed 99
	    incrementedValue = incrementedValue % 100;	    
	    // Format the incremented value to have two digits
	    String formattedIncrementedValue = String.format("%02d", incrementedValue);	    
	    // Replace the last two digits in extId
	    socialgroup.setExtId(extId.substring(0, extId.length() - 2) + formattedIncrementedValue);
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

}

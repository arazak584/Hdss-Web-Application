package org.arn.hdsscapture.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.arn.hdsscapture.entity.Death;
import org.arn.hdsscapture.entity.ErrorLog;
import org.arn.hdsscapture.exception.DataErrorException;
import org.arn.hdsscapture.exception.DataNotFoundException;
import org.arn.hdsscapture.repository.DeathRepository;
import org.arn.hdsscapture.repository.ErrorLogRepository;
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
@RequestMapping("/api/death")
public class DeathController {
	
	@Autowired
	DeathRepository repo;
	@Autowired
	ErrorLogRepository errorLogRepository;
	
	@GetMapping("")
	public DataWrapper<Death> findAll() {

		List<Death> data = repo.findAll();

		DataWrapper<Death> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("")
	public DataWrapper<Death> saveAll(@RequestBody DataWrapper<Death> data) {
		try {

		List<Death> saved =  data.getData();
		
		for (Death death : saved) {
            Optional<Death> existingDeathOptional = repo.findById(death.getIndividual_uuid());
            Death existingDeath = existingDeathOptional.orElse(null);
            
            if (existingDeath != null) {
            	if (existingDeath.getComplete() == null && death.getComplete()==1) {
            		System.out.println("Updating existing record 1...");
            		repo.save(death);
            	}else if (existingDeath.getComplete() == 1 && death.getComplete()==1) {
            		System.out.println("Updating existing record 2...");
                    repo.save(death);
                }else if (existingDeath.getComplete() == 1 && death.getComplete()==2) {
                	System.out.println("Deleting existing record...");
                	repo.delete(existingDeath);
                	continue;
                }else if (death.getComplete() == 0) {     	
               } 
            } else { 
            	if (existingDeath == null ) {
            		if (death.getComplete() == 1) {
            	System.out.println("Save New...");
                repo.save(death);
            	}else if (death.getComplete() == 2) {
            	
            }else if (death.getComplete() == 0) {
            	
            } 
            }
		}

        }

		DataWrapper<Death> s = new DataWrapper<>();
		s.setData(saved);

		return s;
		} catch (Exception e) {
            // Log the API error message and full stack trace into ErrorLog entity
            String errorMessage = "API Error: " + e.getMessage();
            String stackTrace = getStackTraceAsString(e);

            logError(errorMessage, stackTrace);

            throw new DataErrorException(errorMessage, e);
        }
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
	public Death save(@RequestBody Death death) {
		return repo.save(death);
	}

	@GetMapping("/{id}")
	public Death find(@PathVariable("id") String extId) {

		return repo.findById(extId).orElseThrow(() -> new DataNotFoundException(Death.class.getSimpleName(), extId));
	}

	@PostMapping("/{id}")
	public Death replace(@RequestBody Death newDeath, @PathVariable("id") String extId) {

		return repo.save(newDeath);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String extId) {
		repo.deleteById(extId);
	}


}

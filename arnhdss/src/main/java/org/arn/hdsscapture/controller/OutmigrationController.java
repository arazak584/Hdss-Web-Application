package org.arn.hdsscapture.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.arn.hdsscapture.entity.ErrorLog;
import org.arn.hdsscapture.entity.Outmigration;
import org.arn.hdsscapture.entity.Residency;
import org.arn.hdsscapture.exception.DataErrorException;
import org.arn.hdsscapture.exception.DataNotFoundException;
import org.arn.hdsscapture.repository.ErrorLogRepository;
import org.arn.hdsscapture.repository.OutmigrationRepository;
import org.arn.hdsscapture.repository.ResidencyRepository;
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
@RequestMapping("/api/outmigration")
public class OutmigrationController {
	
	@Autowired
	OutmigrationRepository repo;
	@Autowired
	ErrorLogRepository errorLogRepository;
	@Autowired
    private ResidencyRepository residencyRepository;
	
	@GetMapping("")
	public DataWrapper<Outmigration> findAll() {

		List<Outmigration> data = repo.findAll();

		DataWrapper<Outmigration> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("")
    public DataWrapper<Outmigration> saveAll(@RequestBody DataWrapper<Outmigration> data) {
        try {
            List<Outmigration> newOutmigrationData = data.getData();

            
            for (Outmigration omg : newOutmigrationData) {  
            	
            	Optional<Outmigration> existingOmgOptional = repo.findById(omg.getResidency_uuid());
                Outmigration existingOmg = existingOmgOptional.orElse(null);
                
                if (existingOmg != null) {
                	if (existingOmg.getComplete() == null && omg.getComplete()==1) {
                		System.out.println("Updating existing record...");
                        repo.save(omg);
                	}else if (existingOmg.getComplete() == 1 && omg.getComplete()==1) {
                		System.out.println("Updating existing record...");
                        repo.save(omg);
                	}else if (existingOmg.getComplete() == 1 && omg.getComplete()==2) {
                    	System.out.println("Deleting existing record...");
                    	repo.delete(existingOmg);
                    	continue;
                    }else if (omg.getComplete() == 0) {
                    	System.out.println("Condition for omg.getComplete() == 0 is true...");
                    	
                    }else if (existingOmg.getResidency_uuid() != null) {
                    	Residency associatedResidency = residencyRepository.findByUuid(existingOmg.getResidency_uuid());
                        if (associatedResidency != null && associatedResidency.getEndDate() != null) {
                            existingOmg.setRecordedDate(associatedResidency.getEndDate());
                            System.out.println("Updating End Date for existingOmg...");
                            repo.save(existingOmg);
                        }
                    }
                	
                }else {
                	if ( existingOmg == null ) {               		
                		if (omg.getComplete() == 1) {
                			System.out.println("Save new record...");
                            repo.save(omg);	
                		}else if (omg.getComplete() == 2) {
                			
                		}else if (omg.getComplete() == 0) {
                			
                		}
                	}
                }               
     
              
            }

            // Return the saved data
            DataWrapper<Outmigration> s = new DataWrapper<>();
            s.setData(newOutmigrationData);

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

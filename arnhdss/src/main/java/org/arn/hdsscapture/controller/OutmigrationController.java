package org.arn.hdsscapture.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.List;

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

            for (Outmigration newOutmigration : newOutmigrationData) {
                // Check if the UUID already exists in the database
                Outmigration existingOutmigration = repo.findById(newOutmigration.getUuid()).orElse(null);

                if (existingOutmigration != null) {
                    // Check if the Outmigration exists and has a residency_uuid
                    if (existingOutmigration.getResidency_uuid() != null) {
                        // Query the database to find the associated Residency record
                        Residency associatedResidency = residencyRepository.findByUuid(existingOutmigration.getResidency_uuid());

                        if (associatedResidency != null && associatedResidency.getEndDate() != null) {
                            // Update the recordedDate in the existing Outmigration
                            existingOutmigration.setRecordedDate(associatedResidency.getEndDate());

                            // Save the updated Outmigration
                            repo.save(existingOutmigration);
                        }
                    }
                } else {
                    // This is a new Outmigration record, you can create it and set recordedDate as needed
                    repo.save(newOutmigration);
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

package org.arn.hdsscapture.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.List;

import org.arn.hdsscapture.entity.ErrorLog;
import org.arn.hdsscapture.entity.Pregnancyobservation;
import org.arn.hdsscapture.exception.DataErrorException;
import org.arn.hdsscapture.exception.DataNotFoundException;
import org.arn.hdsscapture.repository.ErrorLogRepository;
import org.arn.hdsscapture.repository.PregnancyobservationRepository;
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
@RequestMapping("/api/pregnancy")
public class PregnancyobservationController {
	
	@Autowired
	PregnancyobservationRepository repo;
	@Autowired
	ErrorLogRepository errorLogRepository;
	
	@GetMapping("")
	public DataWrapper<Pregnancyobservation> findAll() {

		List<Pregnancyobservation> data = repo.findAll();

		DataWrapper<Pregnancyobservation> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("")
	public DataWrapper<Pregnancyobservation> saveAll(@RequestBody DataWrapper<Pregnancyobservation> data) {
		try {
		List<Pregnancyobservation> observations = data.getData();

	    // Iterate through the observations and set outcome to null if outcome is equal to 1
	    for (Pregnancyobservation observation : observations) {
	        if (observation.getOutcome() != null && observation.getOutcome() == 1) {
	            observation.setOutcome(null);
	        }
	    }

		List<Pregnancyobservation> saved =  repo.saveAll(data.getData());

		DataWrapper<Pregnancyobservation> s = new DataWrapper<>();
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
	public Pregnancyobservation save(@RequestBody Pregnancyobservation pregnancyobservation) {
		return repo.save(pregnancyobservation);
	}

	@GetMapping("/{id}")
	public Pregnancyobservation find(@PathVariable("id") String uuid) {

		return repo.findById(uuid).orElseThrow(() -> new DataNotFoundException(Pregnancyobservation.class.getSimpleName(), uuid));
	}

	@PostMapping("/{id}")
	public Pregnancyobservation replace(@RequestBody Pregnancyobservation newPregnancyobservation, @PathVariable("id") String uuid) {

		return repo.save(newPregnancyobservation);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String uuid) {
		repo.deleteById(uuid);
	}

}

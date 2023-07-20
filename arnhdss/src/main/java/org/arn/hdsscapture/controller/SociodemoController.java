package org.arn.hdsscapture.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.List;

import org.arn.hdsscapture.entity.ErrorLog;
import org.arn.hdsscapture.entity.Sociodemographic;
import org.arn.hdsscapture.exception.DataErrorException;
import org.arn.hdsscapture.exception.DataNotFoundException;
import org.arn.hdsscapture.repository.ErrorLogRepository;
import org.arn.hdsscapture.repository.SesRepository;
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
@RequestMapping("/api/socio")
public class SociodemoController {
	
	@Autowired
	SesRepository repo;
	@Autowired
	ErrorLogRepository errorLogRepository;
	
	@GetMapping("")
	public DataWrapper<Sociodemographic> findAll() {

		List<Sociodemographic> data = repo.findAll();

		DataWrapper<Sociodemographic> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("")
	public DataWrapper<Sociodemographic> saveAll(@RequestBody DataWrapper<Sociodemographic> data) {
		try {

		List<Sociodemographic> saved =  repo.saveAll(data.getData());

		DataWrapper<Sociodemographic> s = new DataWrapper<>();
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
	public Sociodemographic save(@RequestBody Sociodemographic sodemographic) {
		return repo.save(sodemographic);
	}

	@GetMapping("/{id}")
	public Sociodemographic find(@PathVariable("id") String extId) {

		return repo.findById(extId).orElseThrow(() -> new DataNotFoundException(Sociodemographic.class.getSimpleName(), extId));
	}

	@PostMapping("/{id}")
	public Sociodemographic replace(@RequestBody Sociodemographic newsDemographic, @PathVariable("id") String extId) {

		return repo.save(newsDemographic);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String extId) {
		repo.deleteById(extId);
	}


}

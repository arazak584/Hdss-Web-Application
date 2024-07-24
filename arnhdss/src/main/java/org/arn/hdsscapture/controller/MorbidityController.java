package org.arn.hdsscapture.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.List;

import org.arn.hdsscapture.entity.ErrorLog;
import org.arn.hdsscapture.entity.Morbidity;
import org.arn.hdsscapture.exception.DataErrorException;
import org.arn.hdsscapture.exception.DataNotFoundException;
import org.arn.hdsscapture.repository.ErrorLogRepository;
import org.arn.hdsscapture.repository.MorbidityRepository;
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
@RequestMapping("/api/morbidity")
public class MorbidityController {
	
	@Autowired
	MorbidityRepository repo;
	@Autowired
	ErrorLogRepository errorLogRepository;
	
	@GetMapping("")
	public DataWrapper<Morbidity> findAll() {

		List<Morbidity> data = repo.findAll();

		DataWrapper<Morbidity> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("")
	public DataWrapper<Morbidity> saveAll(@RequestBody DataWrapper<Morbidity> data) {
		try {

		List<Morbidity> saved =  repo.saveAll(data.getData());

		DataWrapper<Morbidity> s = new DataWrapper<>();
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
	public Morbidity save(@RequestBody Morbidity morbidity) {
		return repo.save(morbidity);
	}

	@GetMapping("/{id}")
	public Morbidity find(@PathVariable("id") String uuid) {

		return repo.findById(uuid).orElseThrow(() -> new DataNotFoundException(Morbidity.class.getSimpleName(), uuid));
	}

	@PostMapping("/{id}")
	public Morbidity replace(@RequestBody Morbidity newMorbidity, @PathVariable("id") String uuid) {

		return repo.save(newMorbidity);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String uuid) {
		repo.deleteById(uuid);
	}


}

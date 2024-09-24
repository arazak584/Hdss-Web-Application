package org.arn.hdsscapture.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

import org.arn.hdsscapture.entity.ErrorLog;
import org.arn.hdsscapture.entity.RegisterBook;
import org.arn.hdsscapture.exception.DataErrorException;
import org.arn.hdsscapture.exception.DataNotFoundException;
import org.arn.hdsscapture.repository.ErrorLogRepository;
import org.arn.hdsscapture.repository.RegisterRepository;
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
@RequestMapping("/api/registry")
public class RegisterController {
	
	@Autowired
	RegisterRepository repo;
	@Autowired
	ErrorLogRepository errorLogRepository;
	
	@GetMapping("")
	public DataWrapper<RegisterBook> findAll() {

		List<RegisterBook> data = repo.findAll();

		DataWrapper<RegisterBook> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	

	@PostMapping("")
	public DataWrapper<RegisterBook> saveAll(@RequestBody DataWrapper<RegisterBook> data) {
	    try {
	        // Loop through each record and print the insertDate
	        for (RegisterBook record : data.getData()) {
	            // Assuming insertDate is a Date object in RegisterBook
	            if (record.getInsertDate() != null) {
	                System.out.println("Incoming insertDate: " + record.getInsertDate().toString()); 
	                // or use SimpleDateFormat for custom formatting
	                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
	                System.out.println("Formatted insertDate: " + f.format(record.getInsertDate()));
	            } else {
	                System.out.println("insertDate is null for record: " + record.getIndividual_uuid());
	            }
	        }

	        // Save all valid records
	        List<RegisterBook> saved = repo.saveAll(data.getData());

	        DataWrapper<RegisterBook> s = new DataWrapper<>();
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
	public RegisterBook save(@RequestBody RegisterBook registerBook) {
		return repo.save(registerBook);
	}

	@GetMapping("/{id}")
	public RegisterBook find(@PathVariable("id") String uuid) {

		return repo.findById(uuid).orElseThrow(() -> new DataNotFoundException(RegisterBook.class.getSimpleName(), uuid));
	}

	@PostMapping("/{id}")
	public RegisterBook replace(@RequestBody RegisterBook newRegisterBook, @PathVariable("id") String uuid) {

		return repo.save(newRegisterBook);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String uuid) {
		repo.deleteById(uuid);
	}


}

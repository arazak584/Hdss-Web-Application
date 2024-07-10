package org.arn.hdsscapture.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.List;

import org.arn.hdsscapture.entity.ErrorLog;
import org.arn.hdsscapture.entity.Location;
import org.arn.hdsscapture.entity.Locationhierarchy;
import org.arn.hdsscapture.exception.DataErrorException;
import org.arn.hdsscapture.exception.DataNotFoundException;
import org.arn.hdsscapture.projection.LocationProjection;
import org.arn.hdsscapture.repository.ErrorLogRepository;
import org.arn.hdsscapture.repository.LocationRepository;
import org.arn.hdsscapture.repository.LocationhierarchyRepository;
import org.arn.hdsscapture.repository.LocationhierarchylevelRepository;
import org.arn.hdsscapture.utils.DataWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/location")
public class LocationController {
	
	@Autowired
	LocationRepository repo;
	
	@Autowired
	ErrorLogRepository errorLogRepository;
	
	@Autowired
	LocationhierarchyRepository villrepo;
	
	@GetMapping("")
	public DataWrapper<LocationProjection> findAll() {

		List<LocationProjection> data = repo.findLocations();

		DataWrapper<LocationProjection> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}

	
	@PostMapping("")
	public DataWrapper<Location> saveAll(@RequestBody DataWrapper<Location> data) {
		try {
			List<Location> saved = repo.saveAll(data.getData());

			DataWrapper<Location> s = new DataWrapper<>();
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
	public Location save(@RequestBody Location location) {
		return repo.save(location);
	}

	@GetMapping("/{id}")
	public Location find(@PathVariable("id") String extId) {

		return repo.findById(extId).orElseThrow(() -> new DataNotFoundException(Location.class.getSimpleName(), extId));
	}

	@PostMapping("/{id}")
	public Location replace(@RequestBody Location newLocation, @PathVariable("id") String extId) {

		return repo.save(newLocation);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String extId) {
		repo.deleteById(extId);
	}
	
	@GetMapping("/villages")
    public List<Locationhierarchy> getAllVillages() {
        return villrepo.village();
    }
	
	@GetMapping("/by-village")
    public List<Location> getAllLocations(@RequestParam String village) {
        return repo.findByVillage(village);
    }

}

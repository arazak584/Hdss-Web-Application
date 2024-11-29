package org.arn.hdsscapture.controller;

import java.util.Collections;
import java.util.List;

import org.arn.hdsscapture.entity.Locationhierarchy;
import org.arn.hdsscapture.entity.Population;
import org.arn.hdsscapture.projection.EventTrend;
import org.arn.hdsscapture.repository.LocationhierarchyRepository;
import org.arn.hdsscapture.repository.PopulationRepository;
import org.arn.hdsscapture.utils.DataWrapper;
import org.arn.hdsscapture.views.Events;
import org.arn.hdsscapture.views.EventsRepository;
import org.arn.hdsscapture.views.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	PopulationRepository repo;
	@Autowired
	EventsRepository erepo;
	@Autowired
	FieldRepository frepo;
	@Autowired
	LocationhierarchyRepository villrepo;
	
	@GetMapping("/pop")
	public DataWrapper<Population> findAll() {

		List<Population> data = repo.pyramid();

		DataWrapper<Population> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@GetMapping("/dth-year")
	public DataWrapper<Events> findDth() {

		List<Events> data = erepo.deaths();

		DataWrapper<Events> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@GetMapping("/outcome-year")
	public DataWrapper<Events> findDOutcome() {

		List<Events> data = erepo.outcome();

		DataWrapper<Events> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@GetMapping("/event-trend")
	public DataWrapper<EventTrend> findEvent() {

		List<EventTrend> data = frepo.getEvent();

		DataWrapper<EventTrend> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@GetMapping("/villages")
    public List<Locationhierarchy> getAllVillages() {
        return villrepo.village();
    }
	
	@GetMapping("/subvillages")
    public List<Locationhierarchy> getSubVillages() {
        return villrepo.subvillage();
    }
	
	@GetMapping("/event-trend-village")
	public ResponseEntity<DataWrapper<EventTrend>> findEventVillage(
	        @RequestParam(name = "village", required = false) String village,
	        Model model) {

	    if (village != null && !village.isEmpty()) {
	        // Fetch data for the specified village
	        List<EventTrend> data = frepo.getEventVillage(village);

	        DataWrapper<EventTrend> wrapper = new DataWrapper<>();
	        wrapper.setData(data);

	        return ResponseEntity.ok(wrapper); // Return 200 OK with the data
	    } else {
	        // Handle the case where no village is provided
	        DataWrapper<EventTrend> wrapper = new DataWrapper<>();
	        wrapper.setData(Collections.emptyList()); // Return an empty list or suitable default data
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(wrapper); // Return 400 Bad Request with an appropriate response
	    }
	}

	

}

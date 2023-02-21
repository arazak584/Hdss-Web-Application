package com.arn.hdss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arn.hdss.entity.Location;
import com.arn.hdss.exception.DataNotFoundException;
import com.arn.hdss.repository.LocationRepository;

@RestController
@RequestMapping("/api/location")
public class LocationController {
	
	@Autowired
	LocationRepository repo;
	
	@GetMapping("")
	public DataWrapper<Location> findAll() {

		List<Location> data = repo.findAll();

		DataWrapper<Location> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("")
	public DataWrapper<Location> saveAll(@RequestBody DataWrapper<Location> data) {

		List<Location> saved =  repo.saveAll(data.getData());

		DataWrapper<Location> s = new DataWrapper<>();
		s.setData(saved);

		return s;
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


}

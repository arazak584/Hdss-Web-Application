package org.arn.hdsscapture.controller;

import java.util.List;

import org.arn.hdsscapture.entity.Demographic;
import org.arn.hdsscapture.exception.DataNotFoundException;
import org.arn.hdsscapture.repository.DemographicRepository;
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
@RequestMapping("/api/demographic")
public class DemographicController {
	
	@Autowired
	DemographicRepository repo;
	
	@GetMapping("")
	public DataWrapper<Demographic> findAll() {

		List<Demographic> data = repo.findAll();

		DataWrapper<Demographic> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("")
	public DataWrapper<Demographic> saveAll(@RequestBody DataWrapper<Demographic> data) {

		List<Demographic> saved =  repo.saveAll(data.getData());

		DataWrapper<Demographic> s = new DataWrapper<>();
		s.setData(saved);

		return s;
	}
	
	@PostMapping("/save")
	public Demographic save(@RequestBody Demographic demographic) {
		return repo.save(demographic);
	}

	@GetMapping("/{id}")
	public Demographic find(@PathVariable("id") String extId) {

		return repo.findById(extId).orElseThrow(() -> new DataNotFoundException(Demographic.class.getSimpleName(), extId));
	}

	@PostMapping("/{id}")
	public Demographic replace(@RequestBody Demographic newDemographic, @PathVariable("id") String extId) {

		return repo.save(newDemographic);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String extId) {
		repo.deleteById(extId);
	}


}

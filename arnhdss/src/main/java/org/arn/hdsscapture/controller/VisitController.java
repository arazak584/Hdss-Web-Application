package org.arn.hdsscapture.controller;

import java.util.List;

import org.arn.hdsscapture.entity.Visit;
import org.arn.hdsscapture.exception.DataNotFoundException;
import org.arn.hdsscapture.repository.VisitRepository;
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
@RequestMapping("/api/visit")
public class VisitController {
	
	@Autowired
	VisitRepository repo;
	
	@GetMapping("")
	public DataWrapper<Visit> findAll() {

		List<Visit> data = repo.findAll();

		DataWrapper<Visit> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("")
	public DataWrapper<Visit> saveAll(@RequestBody DataWrapper<Visit> data) {

		List<Visit> saved =  repo.saveAll(data.getData());

		DataWrapper<Visit> s = new DataWrapper<>();
		s.setData(saved);

		return s;
	}
	
	
	@PostMapping("/save")
	public Visit save(@RequestBody Visit visit) {
		return repo.save(visit);
	}

	@GetMapping("/{id}")
	public Visit find(@PathVariable("id") String visitExtId) {

		return repo.findById(visitExtId).orElseThrow(() -> new DataNotFoundException(Visit.class.getSimpleName(), visitExtId));
	}

	@PostMapping("/{id}")
	public Visit replace(@RequestBody Visit newVisit, @PathVariable("id") String visitExtId) {

		return repo.save(newVisit);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String visitExtId) {
		repo.deleteById(visitExtId);
	}


}

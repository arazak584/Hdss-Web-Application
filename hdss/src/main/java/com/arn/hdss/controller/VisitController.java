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

import com.arn.hdss.entity.Visit;
import com.arn.hdss.exception.DataNotFoundException;
import com.arn.hdss.repository.VisitRepository;

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
	public Visit find(@PathVariable("id") String extId) {

		return repo.findById(extId).orElseThrow(() -> new DataNotFoundException(Visit.class.getSimpleName(), extId));
	}

	@PostMapping("/{id}")
	public Visit replace(@RequestBody Visit newVisit, @PathVariable("id") String extId) {

		return repo.save(newVisit);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String extId) {
		repo.deleteById(extId);
	}


}

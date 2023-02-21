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

import com.arn.hdss.entity.Inmigration;
import com.arn.hdss.exception.DataNotFoundException;
import com.arn.hdss.repository.InmigrationRepository;

@RestController
@RequestMapping("/api/inmigration")
public class InmigrationController {
	
	@Autowired
	InmigrationRepository repo;
	
	@GetMapping("")
	public DataWrapper<Inmigration> findAll() {

		List<Inmigration> data = repo.findAll();

		DataWrapper<Inmigration> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("")
	public DataWrapper<Inmigration> saveAll(@RequestBody DataWrapper<Inmigration> data) {

		List<Inmigration> saved =  repo.saveAll(data.getData());

		DataWrapper<Inmigration> s = new DataWrapper<>();
		s.setData(saved);

		return s;
	}
	
	@PostMapping("/save")
	public Inmigration save(@RequestBody Inmigration inmigration) {
		return repo.save(inmigration);
	}

	@GetMapping("/{id}")
	public Inmigration find(@PathVariable("id") String extId) {

		return repo.findById(extId).orElseThrow(() -> new DataNotFoundException(Inmigration.class.getSimpleName(), extId));
	}

	@PostMapping("/{id}")
	public Inmigration replace(@RequestBody Inmigration newInmigration, @PathVariable("id") String extId) {

		return repo.save(newInmigration);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String extId) {
		repo.deleteById(extId);
	}


}

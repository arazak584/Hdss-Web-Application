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

import com.arn.hdss.entity.Individual;
import com.arn.hdss.exception.DataNotFoundException;
import com.arn.hdss.repository.IndividualRepository;

@RestController
@RequestMapping("/api/individual")
public class IndividualController {
	
	@Autowired
	IndividualRepository repo;
	
	@GetMapping("")
	public DataWrapper<Individual> findAll() {

		List<Individual> data = repo.findAll();

		DataWrapper<Individual> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("")
	public DataWrapper<Individual> saveAll(@RequestBody DataWrapper<Individual> data) {

		List<Individual> saved =  repo.saveAll(data.getData());

		DataWrapper<Individual> s = new DataWrapper<>();
		s.setData(saved);

		return s;
	}
	
	@PostMapping("/save")
	public Individual save(@RequestBody Individual individual) {
		return repo.save(individual);
	}

	@GetMapping("/{id}")
	public Individual find(@PathVariable("id") String extId) {

		return repo.findById(extId).orElseThrow(() -> new DataNotFoundException(Individual.class.getSimpleName(), extId));
	}

	@PostMapping("/{id}")
	public Individual replace(@RequestBody Individual newIndividual, @PathVariable("id") String extId) {

		return repo.save(newIndividual);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String extId) {
		repo.deleteById(extId);
	}


}

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

import com.arn.hdss.entity.Outcome;
import com.arn.hdss.entity.Pregnancyoutcome;
import com.arn.hdss.exception.DataNotFoundException;
import com.arn.hdss.repository.OutcomeRepository;
import com.arn.hdss.repository.PregnancyoutcomeRepository;

@RestController
@RequestMapping("/api/outcome")
public class OutcomeController {
	
	@Autowired
	OutcomeRepository repo;
	
	@GetMapping("")
	public DataWrapper<Outcome> findAll() {

		List<Outcome> data = repo.findAll();

		DataWrapper<Outcome> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("")
	public DataWrapper<Outcome> saveAll(@RequestBody DataWrapper<Outcome> data) {

		List<Outcome> saved =  repo.saveAll(data.getData());

		DataWrapper<Outcome> s = new DataWrapper<>();
		s.setData(saved);

		return s;
	}
	
	@PostMapping("/save")
	public Outcome save(@RequestBody Outcome outcome) {
		return repo.save(outcome);
	}

	@GetMapping("/{id}")
	public Outcome find(@PathVariable("id") String uuid) {

		return repo.findById(uuid).orElseThrow(() -> new DataNotFoundException(Outcome.class.getSimpleName(), uuid));
	}

	@PostMapping("/{id}")
	public Outcome replace(@RequestBody Outcome newOutcome, @PathVariable("id") String uuid) {

		return repo.save(newOutcome);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String uuid) {
		repo.deleteById(uuid);
	}

}

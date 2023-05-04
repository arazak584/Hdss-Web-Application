package org.arn.hdsscapture.controller;

import java.util.List;

import org.arn.hdsscapture.entity.Outcome;
import org.arn.hdsscapture.exception.DataNotFoundException;
import org.arn.hdsscapture.repository.OutcomeRepository;
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

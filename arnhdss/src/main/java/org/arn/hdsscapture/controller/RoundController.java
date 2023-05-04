package org.arn.hdsscapture.controller;


import java.util.List;

import org.arn.hdsscapture.entity.Round;
import org.arn.hdsscapture.repository.RoundRepository;
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
@RequestMapping("")
public class RoundController {
	
	@Autowired
	RoundRepository repo;
	
	@GetMapping("/api/round")
	public DataWrapper<Round> findAll() {

		List<Round> data = repo.findAll();

		DataWrapper<Round> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("/api/round")
	public DataWrapper<Round> saveAll(@RequestBody DataWrapper<Round> data) {

		List<Round> saved =  repo.saveAll(data.getData());

		DataWrapper<Round> s = new DataWrapper<>();
		s.setData(saved);

		return s;
	}
	
	@PostMapping("/api/round/save")
	public Round save(@RequestBody Round Round) {
		return repo.save(Round);
	}


	@PostMapping("/api/round/{id}")
	public Round replace(@RequestBody Round newRound, @PathVariable("id") String id) {

		return repo.save(newRound);
	}
	
	@DeleteMapping("/api/round/{id}")
	void delete(@PathVariable("id") String id) {
		repo.deleteById(id);
	}
	



}



package org.arn.hdsscapture.controller;

import java.util.List;

import org.arn.hdsscapture.entity.Death;
import org.arn.hdsscapture.exception.DataNotFoundException;
import org.arn.hdsscapture.repository.DeathRepository;
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
@RequestMapping("/api/death")
public class DeathController {
	
	@Autowired
	DeathRepository repo;
	
	@GetMapping("")
	public DataWrapper<Death> findAll() {

		List<Death> data = repo.findAll();

		DataWrapper<Death> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("")
	public DataWrapper<Death> saveAll(@RequestBody DataWrapper<Death> data) {

		List<Death> saved =  repo.saveAll(data.getData());

		DataWrapper<Death> s = new DataWrapper<>();
		s.setData(saved);

		return s;
	}
	
	@PostMapping("/save")
	public Death save(@RequestBody Death death) {
		return repo.save(death);
	}

	@GetMapping("/{id}")
	public Death find(@PathVariable("id") String extId) {

		return repo.findById(extId).orElseThrow(() -> new DataNotFoundException(Death.class.getSimpleName(), extId));
	}

	@PostMapping("/{id}")
	public Death replace(@RequestBody Death newDeath, @PathVariable("id") String extId) {

		return repo.save(newDeath);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String extId) {
		repo.deleteById(extId);
	}


}

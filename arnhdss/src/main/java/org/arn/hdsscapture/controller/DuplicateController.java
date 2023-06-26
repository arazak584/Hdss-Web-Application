package org.arn.hdsscapture.controller;

import java.util.List;

import org.arn.hdsscapture.entity.Duplicate;
import org.arn.hdsscapture.exception.DataNotFoundException;
import org.arn.hdsscapture.repository.DuplicateRepository;
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
@RequestMapping("/api/duplicate")
public class DuplicateController {
	
	@Autowired
	DuplicateRepository repo;
	
	@GetMapping("")
	public DataWrapper<Duplicate> findAll() {

		List<Duplicate> data = repo.findAll();

		DataWrapper<Duplicate> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("")
	public DataWrapper<Duplicate> saveAll(@RequestBody DataWrapper<Duplicate> data) {

		List<Duplicate> saved =  repo.saveAll(data.getData());

		DataWrapper<Duplicate> s = new DataWrapper<>();
		s.setData(saved);

		return s;
	}
	
	@PostMapping("/save")
	public Duplicate save(@RequestBody Duplicate duplicate) {
		return repo.save(duplicate);
	}

	@GetMapping("/{id}")
	public Duplicate find(@PathVariable("id") String uuid) {

		return repo.findById(uuid).orElseThrow(() -> new DataNotFoundException(Duplicate.class.getSimpleName(), uuid));
	}

	@PostMapping("/{id}")
	public Duplicate replace(@RequestBody Duplicate newDuplicate, @PathVariable("id") String uuid) {

		return repo.save(newDuplicate);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String uuid) {
		repo.deleteById(uuid);
	}


}

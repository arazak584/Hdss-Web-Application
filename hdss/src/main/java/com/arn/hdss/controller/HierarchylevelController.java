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

import com.arn.hdss.entity.Hierarchy;
import com.arn.hdss.exception.DataNotFoundException;
import com.arn.hdss.repository.HierarchyRepository;

@RestController
@RequestMapping("/api/level")
public class HierarchylevelController {
	
	@Autowired
	HierarchyRepository repo;
	
	@GetMapping("")
	public DataWrapper<Hierarchy> findAll() {

		List<Hierarchy> data = repo.findAll();

		DataWrapper<Hierarchy> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("")
	public DataWrapper<Hierarchy> saveAll(@RequestBody DataWrapper<Hierarchy> data) {

		List<Hierarchy> saved =  repo.saveAll(data.getData());

		DataWrapper<Hierarchy> s = new DataWrapper<>();
		s.setData(saved);

		return s;
	}
	
	@PostMapping("/save")
	public Hierarchy save(@RequestBody Hierarchy hierarchy) {
		return repo.save(hierarchy);
	}

	@GetMapping("/{id}")
	public Hierarchy find(@PathVariable("id") String uuid) {

		return repo.findById(uuid).orElseThrow(() -> new DataNotFoundException(Hierarchy.class.getSimpleName(), uuid));
	}

	@PostMapping("/{id}")
	public Hierarchy replace(@RequestBody Hierarchy newLocationhierarchylevel, @PathVariable("id") String uuid) {

		return repo.save(newLocationhierarchylevel);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String uuid) {
		repo.deleteById(uuid);
	}


}

package org.arn.hdsscapture.controller;

import java.util.List;

import org.arn.hdsscapture.entity.Outmigration;
import org.arn.hdsscapture.exception.DataNotFoundException;
import org.arn.hdsscapture.repository.OutmigrationRepository;
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
@RequestMapping("/api/outmigration")
public class OutmigrationController {
	
	@Autowired
	OutmigrationRepository repo;
	
	@GetMapping("")
	public DataWrapper<Outmigration> findAll() {

		List<Outmigration> data = repo.findAll();

		DataWrapper<Outmigration> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("")
	public DataWrapper<Outmigration> saveAll(@RequestBody DataWrapper<Outmigration> data) {

		List<Outmigration> saved =  repo.saveAll(data.getData());

		DataWrapper<Outmigration> s = new DataWrapper<>();
		s.setData(saved);

		return s;
	}
	
	@PostMapping("/save")
	public Outmigration save(@RequestBody Outmigration outmigration) {
		return repo.save(outmigration);
	}

	@GetMapping("/{id}")
	public Outmigration find(@PathVariable("id") String extId) {

		return repo.findById(extId).orElseThrow(() -> new DataNotFoundException(Outmigration.class.getSimpleName(), extId));
	}

	@PostMapping("/{id}")
	public Outmigration replace(@RequestBody Outmigration newOutmigration, @PathVariable("id") String extId) {

		return repo.save(newOutmigration);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String extId) {
		repo.deleteById(extId);
	}

}

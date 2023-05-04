package org.arn.hdsscapture.controller;

import java.util.List;

import org.arn.hdsscapture.entity.Relationship;
import org.arn.hdsscapture.exception.DataNotFoundException;
import org.arn.hdsscapture.repository.RelationshipRepository;
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
@RequestMapping("/api/relationship")
public class RelationshipController {
	
	@Autowired
	RelationshipRepository repo;
	
	@GetMapping("")
	public DataWrapper<Relationship> findAll() {

		List<Relationship> data = repo.findAll();

		DataWrapper<Relationship> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("")
	public DataWrapper<Relationship> saveAll(@RequestBody DataWrapper<Relationship> data) {

		List<Relationship> saved =  repo.saveAll(data.getData());

		DataWrapper<Relationship> s = new DataWrapper<>();
		s.setData(saved);

		return s;
	}
	
	@PostMapping("/save")
	public Relationship save(@RequestBody Relationship relationship) {
		return repo.save(relationship);
	}

	@GetMapping("/{id}")
	public Relationship find(@PathVariable("id") String uuid) {

		return repo.findById(uuid).orElseThrow(() -> new DataNotFoundException(Relationship.class.getSimpleName(), uuid));
	}

	@PostMapping("/{id}")
	public Relationship replace(@RequestBody Relationship newRelationship, @PathVariable("id") String uuid) {

		return repo.save(newRelationship);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String uuid) {
		repo.deleteById(uuid);
	}


}

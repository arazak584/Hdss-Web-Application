package org.arn.hdsscapture.controller;

import java.util.List;

import org.arn.hdsscapture.entity.Amendment;
import org.arn.hdsscapture.exception.DataNotFoundException;
import org.arn.hdsscapture.repository.AmendmentRepository;
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
@RequestMapping("/api/amendment")
public class AmendmentController {
	
	@Autowired
	AmendmentRepository repo;
	
	@GetMapping("")
	public DataWrapper<Amendment> findAll() {

		List<Amendment> data = repo.findAll();

		DataWrapper<Amendment> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("")
	public DataWrapper<Amendment> saveAll(@RequestBody DataWrapper<Amendment> data) {

		List<Amendment> saved =  repo.saveAll(data.getData());

		DataWrapper<Amendment> s = new DataWrapper<>();
		s.setData(saved);

		return s;
	}
	
	@PostMapping("/save")
	public Amendment save(@RequestBody Amendment amendment) {
		return repo.save(amendment);
	}

	@GetMapping("/{id}")
	public Amendment find(@PathVariable("id") String uuid) {

		return repo.findById(uuid).orElseThrow(() -> new DataNotFoundException(Amendment.class.getSimpleName(), uuid));
	}

	@PostMapping("/{id}")
	public Amendment replace(@RequestBody Amendment newAmendment, @PathVariable("id") String uuid) {

		return repo.save(newAmendment);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String uuid) {
		repo.deleteById(uuid);
	}


}

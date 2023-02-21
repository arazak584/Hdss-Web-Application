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

import com.arn.hdss.entity.Vpm;
import com.arn.hdss.exception.DataNotFoundException;
import com.arn.hdss.repository.VpmRepository;

@RestController
@RequestMapping("/api/vpm")
public class VpmController {
	
	@Autowired
	VpmRepository repo;
	
	@GetMapping("")
	public DataWrapper<Vpm> findAll() {

		List<Vpm> data = repo.findAll();

		DataWrapper<Vpm> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("")
	public DataWrapper<Vpm> saveAll(@RequestBody DataWrapper<Vpm> data) {

		List<Vpm> saved =  repo.saveAll(data.getData());

		DataWrapper<Vpm> s = new DataWrapper<>();
		s.setData(saved);

		return s;
	}
	
	@PostMapping("/save")
	public Vpm save(@RequestBody Vpm vpm) {
		return repo.save(vpm);
	}

	@GetMapping("/{id}")
	public Vpm find(@PathVariable("id") String extId) {

		return repo.findById(extId).orElseThrow(() -> new DataNotFoundException(Vpm.class.getSimpleName(), extId));
	}

	@PostMapping("/{id}")
	public Vpm replace(@RequestBody Vpm newVpm, @PathVariable("id") String extId) {

		return repo.save(newVpm);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String extId) {
		repo.deleteById(extId);
	}


}

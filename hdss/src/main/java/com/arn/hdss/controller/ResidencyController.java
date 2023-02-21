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

import com.arn.hdss.entity.Residency;
import com.arn.hdss.exception.DataNotFoundException;
import com.arn.hdss.repository.ResidencyRepository;

@RestController
@RequestMapping("/api/residency")
public class ResidencyController {
	
	@Autowired
	ResidencyRepository repo;
	
	@GetMapping("")
	public DataWrapper<Residency> findAll() {

		List<Residency> data = repo.findAll();

		DataWrapper<Residency> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("")
	public DataWrapper<Residency> saveAll(@RequestBody DataWrapper<Residency> data) {

		List<Residency> saved =  repo.saveAll(data.getData());

		DataWrapper<Residency> s = new DataWrapper<>();
		s.setData(saved);

		return s;
	}
	
	@PostMapping("/save")
	public Residency save(@RequestBody Residency residency) {
		return repo.save(residency);
	}

	@GetMapping("/{id}")
	public Residency find(@PathVariable("id") String uuid) {

		return repo.findById(uuid).orElseThrow(() -> new DataNotFoundException(Residency.class.getSimpleName(), uuid));
	}

	@PostMapping("/{id}")
	public Residency replace(@RequestBody Residency newResidency, @PathVariable("id") String uuid) {

		return repo.save(newResidency);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String uuid) {
		repo.deleteById(uuid);
	}


}

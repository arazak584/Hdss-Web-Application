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

import com.arn.hdss.entity.Subdistrict;
import com.arn.hdss.exception.DataNotFoundException;
import com.arn.hdss.repository.SubdistrictRepository;

@RestController
@RequestMapping("/api/subdistrict")
public class SubdistrictController {
	
	@Autowired
	SubdistrictRepository repo;
	
	@GetMapping("")
	public DataWrapper<Subdistrict> findAll() {

		List<Subdistrict> data = repo.findAll();

		DataWrapper<Subdistrict> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("")
	public DataWrapper<Subdistrict> saveAll(@RequestBody DataWrapper<Subdistrict> data) {

		List<Subdistrict> saved =  repo.saveAll(data.getData());

		DataWrapper<Subdistrict> s = new DataWrapper<>();
		s.setData(saved);

		return s;
	}
	
	@PostMapping("/save")
	public Subdistrict save(@RequestBody Subdistrict subdistrict) {
		return repo.save(subdistrict);
	}

	@GetMapping("/{id}")
	public Subdistrict find(@PathVariable("id") String extId) {

		return repo.findById(extId).orElseThrow(() -> new DataNotFoundException(Subdistrict.class.getSimpleName(), extId));
	}

	@PostMapping("/{id}")
	public Subdistrict replace(@RequestBody Subdistrict newSubdistrict, @PathVariable("id") String extId) {

		return repo.save(newSubdistrict);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String extId) {
		repo.deleteById(extId);
	}


}

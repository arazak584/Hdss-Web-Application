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

import com.arn.hdss.entity.District;
import com.arn.hdss.exception.DataNotFoundException;
import com.arn.hdss.repository.DistrictRepository;

@RestController
@RequestMapping("/api/district")
public class DistrictController {
	
	@Autowired
	DistrictRepository repo;
	
	@GetMapping("")
	public DataWrapper<District> findAll() {

		List<District> data = repo.findAll();

		DataWrapper<District> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("")
	public DataWrapper<District> saveAll(@RequestBody DataWrapper<District> data) {

		List<District> saved =  repo.saveAll(data.getData());

		DataWrapper<District> s = new DataWrapper<>();
		s.setData(saved);

		return s;
	}
	
	@PostMapping("/save")
	public District save(@RequestBody District district) {
		return repo.save(district);
	}

	@GetMapping("/{id}")
	public District find(@PathVariable("id") String extId) {

		return repo.findById(extId).orElseThrow(() -> new DataNotFoundException(District.class.getSimpleName(), extId));
	}

	@PostMapping("/{id}")
	public District replace(@RequestBody District newDistrict, @PathVariable("id") String extId) {

		return repo.save(newDistrict);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String extId) {
		repo.deleteById(extId);
	}


}

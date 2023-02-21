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

import com.arn.hdss.entity.Region;
import com.arn.hdss.exception.DataNotFoundException;
import com.arn.hdss.repository.RegionRepository;

@RestController
@RequestMapping("/api/region")
public class RegionController {
	
	@Autowired
	RegionRepository repo;
	
	@GetMapping("")
	public DataWrapper<Region> findAll() {

		List<Region> data = repo.findAll();

		DataWrapper<Region> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("")
	public DataWrapper<Region> saveAll(@RequestBody DataWrapper<Region> data) {

		List<Region> saved =  repo.saveAll(data.getData());

		DataWrapper<Region> s = new DataWrapper<>();
		s.setData(saved);

		return s;
	}
	
	@PostMapping("/save")
	public Region save(@RequestBody Region region) {
		return repo.save(region);
	}

	@GetMapping("/{id}")
	public Region find(@PathVariable("id") String extId) {

		return repo.findById(extId).orElseThrow(() -> new DataNotFoundException(Region.class.getSimpleName(), extId));
	}

	@PostMapping("/{id}")
	public Region replace(@RequestBody Region newRegion, @PathVariable("id") String extId) {

		return repo.save(newRegion);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String extId) {
		repo.deleteById(extId);
	}


}

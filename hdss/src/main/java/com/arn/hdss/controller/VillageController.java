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

import com.arn.hdss.entity.Village;
import com.arn.hdss.exception.DataNotFoundException;
import com.arn.hdss.repository.VillageRepository;

@RestController
@RequestMapping("/api/village")
public class VillageController {
	
	@Autowired
	VillageRepository repo;
	
	@GetMapping("")
	public DataWrapper<Village> findAll() {

		List<Village> data = repo.findAll();

		DataWrapper<Village> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("")
	public DataWrapper<Village> saveAll(@RequestBody DataWrapper<Village> data) {

		List<Village> saved =  repo.saveAll(data.getData());

		DataWrapper<Village> s = new DataWrapper<>();
		s.setData(saved);

		return s;
	}
	
	@PostMapping("/save")
	public Village save(@RequestBody Village village) {
		return repo.save(village);
	}

	@GetMapping("/{id}")
	public Village find(@PathVariable("id") String extId) {

		return repo.findById(extId).orElseThrow(() -> new DataNotFoundException(Village.class.getSimpleName(), extId));
	}

	@PostMapping("/{id}")
	public Village replace(@RequestBody Village newVillage, @PathVariable("id") String extId) {

		return repo.save(newVillage);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String extId) {
		repo.deleteById(extId);
	}


}

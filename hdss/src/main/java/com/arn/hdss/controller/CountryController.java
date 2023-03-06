package com.arn.hdss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arn.hdss.entity.Country;
import com.arn.hdss.exception.DataNotFoundException;
import com.arn.hdss.repository.CountryRepository;

@RestController
public class CountryController {
	
	@Autowired
	CountryRepository repo;
	
	@GetMapping("/api/country")
	public DataWrapper<Country> findAll() {

		List<Country> data = repo.findAll();

		DataWrapper<Country> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("/api/country")
	public DataWrapper<Country> saveAll(@RequestBody DataWrapper<Country> data) {

		List<Country> saved =  repo.saveAll(data.getData());

		DataWrapper<Country> s = new DataWrapper<>();
		s.setData(saved);

		return s;
	}
	
	@PostMapping("/api/country/save")
	public Country save(@RequestBody Country country) {
		return repo.save(country);
	}

	@GetMapping("/api/country/{id}")
	public Country find(@PathVariable("id") String extId) {

		return repo.findById(extId).orElseThrow(() -> new DataNotFoundException(Country.class.getSimpleName(), extId));
	}

	@PostMapping("/api/country/{id}")
	public Country replace(@RequestBody Country newCountry, @PathVariable("id") String extId) {

		return repo.save(newCountry);
	}
	
	@DeleteMapping("/api/country/{id}")
	void delete(@PathVariable("id") String extId) {
		repo.deleteById(extId);
	}


}

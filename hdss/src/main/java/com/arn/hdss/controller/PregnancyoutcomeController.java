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

import com.arn.hdss.entity.Pregnancyoutcome;
import com.arn.hdss.exception.DataNotFoundException;
import com.arn.hdss.repository.PregnancyoutcomeRepository;

@RestController
@RequestMapping("/api/outcome")
public class PregnancyoutcomeController {
	
	@Autowired
	PregnancyoutcomeRepository repo;
	
	@GetMapping("")
	public DataWrapper<Pregnancyoutcome> findAll() {

		List<Pregnancyoutcome> data = repo.findAll();

		DataWrapper<Pregnancyoutcome> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("")
	public DataWrapper<Pregnancyoutcome> saveAll(@RequestBody DataWrapper<Pregnancyoutcome> data) {

		List<Pregnancyoutcome> saved =  repo.saveAll(data.getData());

		DataWrapper<Pregnancyoutcome> s = new DataWrapper<>();
		s.setData(saved);

		return s;
	}
	
	@PostMapping("/save")
	public Pregnancyoutcome save(@RequestBody Pregnancyoutcome pregnancyoutcome) {
		return repo.save(pregnancyoutcome);
	}

	@GetMapping("/{id}")
	public Pregnancyoutcome find(@PathVariable("id") String uuid) {

		return repo.findById(uuid).orElseThrow(() -> new DataNotFoundException(Pregnancyoutcome.class.getSimpleName(), uuid));
	}

	@PostMapping("/{id}")
	public Pregnancyoutcome replace(@RequestBody Pregnancyoutcome newPregnancyoutcome, @PathVariable("id") String uuid) {

		return repo.save(newPregnancyoutcome);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String uuid) {
		repo.deleteById(uuid);
	}

}

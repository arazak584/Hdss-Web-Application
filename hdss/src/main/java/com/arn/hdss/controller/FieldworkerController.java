package com.arn.hdss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arn.hdss.entity.Fieldworker;
import com.arn.hdss.repository.FieldworkerRepository;

@Controller
@RestController
public class FieldworkerController {
	
	@Autowired
	FieldworkerRepository repo;
	
	@GetMapping("/api/fieldworker")
	public DataWrapper<Fieldworker> findAll() {

		List<Fieldworker> data = repo.findAll();

		DataWrapper<Fieldworker> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("/api/fieldworker")
	public DataWrapper<Fieldworker> saveAll(@RequestBody DataWrapper<Fieldworker> data) {

		List<Fieldworker> saved =  repo.saveAll(data.getData());

		DataWrapper<Fieldworker> s = new DataWrapper<>();
		s.setData(saved);

		return s;
	}
	
	@PostMapping("/api/fieldworker/save")
	public Fieldworker save(@RequestBody Fieldworker fieldworker) {
		return repo.save(fieldworker);
	}



	@PostMapping("/api/fieldworker/{id}")
	public Fieldworker replace(@RequestBody Fieldworker newFieldworker, @PathVariable("id") Long id) {

		return repo.save(newFieldworker);
	}
	
	@DeleteMapping("/api/fieldworker/{id}")
	void delete(@PathVariable("id") Long id) {
		repo.deleteById(id);
	}
	

	


}

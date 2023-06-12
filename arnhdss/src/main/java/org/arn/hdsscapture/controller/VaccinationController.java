package org.arn.hdsscapture.controller;

import java.util.List;

import org.arn.hdsscapture.entity.Vaccination;
import org.arn.hdsscapture.exception.DataNotFoundException;
import org.arn.hdsscapture.repository.VaccinationRepository;
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
@RequestMapping("/api/vaccination")
public class VaccinationController {
	
	@Autowired
	VaccinationRepository repo;
	
	@GetMapping("")
	public DataWrapper<Vaccination> findAll() {

		List<Vaccination> data = repo.findAll();

		DataWrapper<Vaccination> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("")
	public DataWrapper<Vaccination> saveAll(@RequestBody DataWrapper<Vaccination> data) {

		List<Vaccination> saved =  repo.saveAll(data.getData());

		DataWrapper<Vaccination> s = new DataWrapper<>();
		s.setData(saved);

		return s;
	}
	
	@PostMapping("/save")
	public Vaccination save(@RequestBody Vaccination vaccination) {
		return repo.save(vaccination);
	}

	@GetMapping("/{id}")
	public Vaccination find(@PathVariable("id") String uuid) {

		return repo.findById(uuid).orElseThrow(() -> new DataNotFoundException(Vaccination.class.getSimpleName(), uuid));
	}

	@PostMapping("/{id}")
	public Vaccination replace(@RequestBody Vaccination newVaccination, @PathVariable("id") String uuid) {

		return repo.save(newVaccination);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String uuid) {
		repo.deleteById(uuid);
	}


}

package org.arn.hdsscapture.controller;

import java.util.List;

import org.arn.hdsscapture.entity.Sociodemographic;
import org.arn.hdsscapture.exception.DataNotFoundException;
import org.arn.hdsscapture.repository.SesRepository;
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
@RequestMapping("/api/socio")
public class SociodemoController {
	
	@Autowired
	SesRepository repo;
	
	@GetMapping("")
	public DataWrapper<Sociodemographic> findAll() {

		List<Sociodemographic> data = repo.findAll();

		DataWrapper<Sociodemographic> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("")
	public DataWrapper<Sociodemographic> saveAll(@RequestBody DataWrapper<Sociodemographic> data) {

		List<Sociodemographic> saved =  repo.saveAll(data.getData());

		DataWrapper<Sociodemographic> s = new DataWrapper<>();
		s.setData(saved);

		return s;
	}
	
	@PostMapping("/save")
	public Sociodemographic save(@RequestBody Sociodemographic sodemographic) {
		return repo.save(sodemographic);
	}

	@GetMapping("/{id}")
	public Sociodemographic find(@PathVariable("id") String extId) {

		return repo.findById(extId).orElseThrow(() -> new DataNotFoundException(Sociodemographic.class.getSimpleName(), extId));
	}

	@PostMapping("/{id}")
	public Sociodemographic replace(@RequestBody Sociodemographic newsDemographic, @PathVariable("id") String extId) {

		return repo.save(newsDemographic);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String extId) {
		repo.deleteById(extId);
	}


}

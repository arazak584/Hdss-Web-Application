package org.arn.hdsscapture.controller;

import java.util.List;

import org.arn.hdsscapture.entity.Pregnancyobservation;
import org.arn.hdsscapture.exception.DataNotFoundException;
import org.arn.hdsscapture.repository.PregnancyobservationRepository;
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
@RequestMapping("/api/pregnancy")
public class PregnancyobservationController {
	
	@Autowired
	PregnancyobservationRepository repo;
	
	@GetMapping("")
	public DataWrapper<Pregnancyobservation> findAll() {

		List<Pregnancyobservation> data = repo.findAll();

		DataWrapper<Pregnancyobservation> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("")
	public DataWrapper<Pregnancyobservation> saveAll(@RequestBody DataWrapper<Pregnancyobservation> data) {

		List<Pregnancyobservation> saved =  repo.saveAll(data.getData());

		DataWrapper<Pregnancyobservation> s = new DataWrapper<>();
		s.setData(saved);

		return s;
	}
	
	@PostMapping("/save")
	public Pregnancyobservation save(@RequestBody Pregnancyobservation pregnancyobservation) {
		return repo.save(pregnancyobservation);
	}

	@GetMapping("/{id}")
	public Pregnancyobservation find(@PathVariable("id") String uuid) {

		return repo.findById(uuid).orElseThrow(() -> new DataNotFoundException(Pregnancyobservation.class.getSimpleName(), uuid));
	}

	@PostMapping("/{id}")
	public Pregnancyobservation replace(@RequestBody Pregnancyobservation newPregnancyobservation, @PathVariable("id") String uuid) {

		return repo.save(newPregnancyobservation);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String uuid) {
		repo.deleteById(uuid);
	}

}

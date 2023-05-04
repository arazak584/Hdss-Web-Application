package org.arn.hdsscapture.controller;

import java.util.List;

import org.arn.hdsscapture.entity.Socialgroup;
import org.arn.hdsscapture.exception.DataNotFoundException;
import org.arn.hdsscapture.repository.SocialgroupRepository;
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
@RequestMapping("/api/socialgroup")
public class SocialgroupController {
	
	@Autowired
	SocialgroupRepository repo;
	
	@GetMapping("")
	public DataWrapper<Socialgroup> findAll() {

		List<Socialgroup> data = repo.findAll();

		DataWrapper<Socialgroup> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("")
	public DataWrapper<Socialgroup> saveAll(@RequestBody DataWrapper<Socialgroup> data) {

		List<Socialgroup> saved =  repo.saveAll(data.getData());

		DataWrapper<Socialgroup> s = new DataWrapper<>();
		s.setData(saved);

		return s;
	}
	
	@PostMapping("/save")
	public Socialgroup save(@RequestBody Socialgroup socialgroup) {
		return repo.save(socialgroup);
	}

	@GetMapping("/{id}")
	public Socialgroup find(@PathVariable("id") String extId) {

		return repo.findById(extId).orElseThrow(() -> new DataNotFoundException(Socialgroup.class.getSimpleName(), extId));
	}

	@PostMapping("/{id}")
	public Socialgroup replace(@RequestBody Socialgroup newSocialgroup, @PathVariable("id") String extId) {

		return repo.save(newSocialgroup);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String extId) {
		repo.deleteById(extId);
	}

}

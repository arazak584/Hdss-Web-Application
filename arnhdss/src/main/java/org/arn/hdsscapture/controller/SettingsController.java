package org.arn.hdsscapture.controller;


import java.util.List;

import org.arn.hdsscapture.entity.Settings;
import org.arn.hdsscapture.repository.SettingsRepository;
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
@RequestMapping("")
public class SettingsController {
	
	@Autowired
	SettingsRepository repo;
	
	@GetMapping("/api/parameter")
	public DataWrapper<Settings> findAll() {

		List<Settings> data = repo.findAll();

		DataWrapper<Settings> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("/api/parameter")
	public DataWrapper<Settings> saveAll(@RequestBody DataWrapper<Settings> data) {

		List<Settings> saved =  repo.saveAll(data.getData());

		DataWrapper<Settings> s = new DataWrapper<>();
		s.setData(saved);

		return s;
	}
	
	@PostMapping("/api/parameter/save")
	public Settings save(@RequestBody Settings Settings) {
		return repo.save(Settings);
	}


	@PostMapping("/api/parameter/{id}")
	public Settings replace(@RequestBody Settings newSettings, @PathVariable("id") String id) {

		return repo.save(newSettings);
	}
	
	@DeleteMapping("/api/parameter/{id}")
	void delete(@PathVariable("id") Integer id) {
		repo.deleteById(id);
	}
	



}



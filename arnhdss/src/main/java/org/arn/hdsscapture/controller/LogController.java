package org.arn.hdsscapture.controller;

import java.util.List;

import org.arn.hdsscapture.entity.LogBook;
import org.arn.hdsscapture.repository.LogRepository;
import org.arn.hdsscapture.utils.DataWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/log")
public class LogController {
	
	@Autowired
	LogRepository repo;
	
	@GetMapping("")
	@Cacheable(value = "logbook", key = "'logbook'", cacheManager = "cacheManager")
	public DataWrapper<LogBook> findAll() {

		List<LogBook> data = repo.findAll();

		DataWrapper<LogBook> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("")
	public DataWrapper<LogBook> saveAll(@RequestBody DataWrapper<LogBook> data) {

		List<LogBook> saved =  repo.saveAll(data.getData());
		
		DataWrapper<LogBook> s = new DataWrapper<>();
		s.setData(saved);

		return s;
	}
	
	@PostMapping("/save")
	public LogBook save(@RequestBody LogBook logBook) {
		return repo.save(logBook);
	}


	@PostMapping("/{id}")
	public LogBook replace(@RequestBody LogBook newLogBook, @PathVariable("id") String id) {

		return repo.save(newLogBook);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") Integer id) {
		repo.deleteById(id);
	}
	
}


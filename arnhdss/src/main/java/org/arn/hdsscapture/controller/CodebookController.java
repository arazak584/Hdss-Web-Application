package org.arn.hdsscapture.controller;

import java.util.List;

import org.arn.hdsscapture.CacheConfig;
import org.arn.hdsscapture.entity.Codebook;
import org.arn.hdsscapture.repository.CodebookRepository;
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
@RequestMapping("/api/codebook")
public class CodebookController {
	
	@Autowired
	CodebookRepository repo;
	
	@Autowired
	CacheConfig cacheManager;
	
	@GetMapping("")
	//@Cacheable(value = "codebook", key = "'codebook'", cacheManager = "cacheManager")
	public DataWrapper<Codebook> findAll() {

		List<Codebook> data = repo.findAll();

		DataWrapper<Codebook> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("")
	public DataWrapper<Codebook> saveAll(@RequestBody DataWrapper<Codebook> data) {

		List<Codebook> saved =  repo.saveAll(data.getData());

		DataWrapper<Codebook> s = new DataWrapper<>();
		s.setData(saved);

		return s;
	}
	
	@PostMapping("/save")
	public Codebook save(@RequestBody Codebook codebook) {
		return repo.save(codebook);
	}


	@PostMapping("/{id}")
	public Codebook replace(@RequestBody Codebook newCodebook, @PathVariable("id") String id) {

		return repo.save(newCodebook);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") Integer id) {
		repo.deleteById(id);
	}
	
}

package org.arn.hdsscapture.controller;

import java.util.List;

import org.arn.hdsscapture.CacheConfig;
import org.arn.hdsscapture.entity.Fieldworker;
import org.arn.hdsscapture.repository.FieldworkerRepository;
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
@RequestMapping("/api/fieldworker")
public class FieldworkerController {
	
	@Autowired
	FieldworkerRepository repo;
	
	@Autowired
	CacheConfig cacheManager;
	
	@GetMapping("")
	@Cacheable(value = "fieldworker", key = "'fieldworker'", cacheManager = "cacheManager")
	public DataWrapper<Fieldworker> findAll() {

		List<Fieldworker> data = repo.findAll();

		DataWrapper<Fieldworker> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@GetMapping("fw")
    public List<Fieldworker> getAll() {
        return repo.fw();
    }
	
	@PostMapping("")
	public DataWrapper<Fieldworker> saveAll(@RequestBody DataWrapper<Fieldworker> data) {

		List<Fieldworker> saved =  repo.saveAll(data.getData());

		DataWrapper<Fieldworker> s = new DataWrapper<>();
		s.setData(saved);

		return s;
	}
	
	
	@PostMapping("/save")
	public Fieldworker save(@RequestBody Fieldworker fieldworker) {
		return repo.save(fieldworker);
	}



	@PostMapping("/{id}")
	public Fieldworker replace(@RequestBody Fieldworker newFieldworker, @PathVariable("id") String id) {

		return repo.save(newFieldworker);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String id) {
		repo.deleteById(id);
	}
	

	


}

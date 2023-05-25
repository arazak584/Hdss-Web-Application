package org.arn.hdsscapture.controller;

import java.util.List;

import org.arn.hdsscapture.entity.Listing;
import org.arn.hdsscapture.exception.DataNotFoundException;
import org.arn.hdsscapture.repository.ListingRepository;
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
@RequestMapping("/api/listing")
public class ListingController {
	
	@Autowired
	ListingRepository repo;
	
	@GetMapping("")
	public DataWrapper<Listing> findAll() {

		List<Listing> data = repo.findAll();

		DataWrapper<Listing> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("")
	public DataWrapper<Listing> saveAll(@RequestBody DataWrapper<Listing> data) {

		List<Listing> saved =  repo.saveAll(data.getData());

		DataWrapper<Listing> s = new DataWrapper<>();
		s.setData(saved);

		return s;
	}
	
	@PostMapping("/save")
	public Listing save(@RequestBody Listing listing) {
		return repo.save(listing);
	}

	@GetMapping("/{id}")
	public Listing find(@PathVariable("id") String extId) {

		return repo.findById(extId).orElseThrow(() -> new DataNotFoundException(Listing.class.getSimpleName(), extId));
	}

	@PostMapping("/{id}")
	public Listing replace(@RequestBody Listing newlisting, @PathVariable("id") String extId) {

		return repo.save(newlisting);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String extId) {
		repo.deleteById(extId);
	}


}

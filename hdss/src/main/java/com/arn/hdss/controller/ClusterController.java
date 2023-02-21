package com.arn.hdss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arn.hdss.entity.Cluster;
import com.arn.hdss.exception.DataNotFoundException;
import com.arn.hdss.repository.ClusterRepository;

@RestController
@RequestMapping("/api/cluster")
public class ClusterController {
	
	@Autowired
	ClusterRepository repo;
	
	@GetMapping("")
	public DataWrapper<Cluster> findAll() {

		List<Cluster> data = repo.findAll();

		DataWrapper<Cluster> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("")
	public DataWrapper<Cluster> saveAll(@RequestBody DataWrapper<Cluster> data) {

		List<Cluster> saved =  repo.saveAll(data.getData());

		DataWrapper<Cluster> s = new DataWrapper<>();
		s.setData(saved);

		return s;
	}
	
	@PostMapping("/save")
	public Cluster save(@RequestBody Cluster cluster) {
		return repo.save(cluster);
	}

	@GetMapping("/{id}")
	public Cluster find(@PathVariable("id") String extId) {

		return repo.findById(extId).orElseThrow(() -> new DataNotFoundException(Cluster.class.getSimpleName(), extId));
	}

	@PostMapping("/{id}")
	public Cluster replace(@RequestBody Cluster newCluster, @PathVariable("id") String extId) {

		return repo.save(newCluster);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable("id") String extId) {
		repo.deleteById(extId);
	}


}

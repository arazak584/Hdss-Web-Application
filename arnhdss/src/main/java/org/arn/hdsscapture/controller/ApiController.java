package org.arn.hdsscapture.controller;

import java.util.List;

import org.arn.hdsscapture.entity.Population;
import org.arn.hdsscapture.repository.PopulationRepository;
import org.arn.hdsscapture.utils.DataWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	PopulationRepository repo;
	
	@GetMapping("/pop")
	public DataWrapper<Population> findAll() {

		List<Population> data = repo.pyramid();

		DataWrapper<Population> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}

}

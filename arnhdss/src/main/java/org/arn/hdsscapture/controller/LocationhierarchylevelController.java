package org.arn.hdsscapture.controller;

import java.util.List;

import org.arn.hdsscapture.entity.Locationhierarchylevel;
import org.arn.hdsscapture.repository.LocationhierarchylevelRepository;
import org.arn.hdsscapture.utils.DataWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/")
public class LocationhierarchylevelController {
	
	@Autowired
	LocationhierarchylevelRepository repo;
	
	@GetMapping("hierarchylevel")
	public DataWrapper<Locationhierarchylevel> findAll() {

		List<Locationhierarchylevel> data = repo.findAll();

		DataWrapper<Locationhierarchylevel> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}



}

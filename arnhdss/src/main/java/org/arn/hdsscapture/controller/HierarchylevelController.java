package org.arn.hdsscapture.controller;

import java.util.List;

import org.arn.hdsscapture.entity.Locationhierarchy;
import org.arn.hdsscapture.repository.LocationhierarchyRepository;
import org.arn.hdsscapture.utils.DataWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/")
public class HierarchylevelController {
	
	@Autowired
	LocationhierarchyRepository repo;
	
	@GetMapping("hierarchy")
	public DataWrapper<Locationhierarchy> findAll() {

		List<Locationhierarchy> data = repo.findAll();

		DataWrapper<Locationhierarchy> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	


}

package org.arn.hdsscapture.controller;

import java.util.List;

import org.arn.hdsscapture.entity.Locationhierarchylevel;
import org.arn.hdsscapture.entity.Locationhierarchy;
import org.arn.hdsscapture.repository.LocationhierarchylevelRepository;
import org.arn.hdsscapture.repository.LocationhierarchyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class HierarchyController {
	
	
	@Autowired
	LocationhierarchyRepository hierarRepo;
	
	@Autowired
	LocationhierarchylevelRepository hierarchyRepo;

	
	//Country
	@GetMapping("/countryList")
	public String findCountry(Model model) {
	    List<Locationhierarchy> countryList = hierarRepo.findAll();
	    model.addAttribute("country", countryList);
	    return "countryList";
	}

	@GetMapping("/countryList/add")
	public String addCountry(Model model) {
		Locationhierarchy country = new Locationhierarchy();
		List<Locationhierarchylevel> locationhierarchylevel = hierarchyRepo.findAll();
		model.addAttribute("country", country);
		model.addAttribute("hierarchy", locationhierarchylevel);
		return "add_country";
		
	}

}

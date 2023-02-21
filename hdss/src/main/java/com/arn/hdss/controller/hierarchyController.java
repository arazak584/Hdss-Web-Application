package com.arn.hdss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.arn.hdss.entity.Country;
import com.arn.hdss.entity.Hierarchy;
import com.arn.hdss.repository.CountryRepository;
import com.arn.hdss.repository.HierarchyRepository;

@Controller
public class hierarchyController {
	
	
	@Autowired
	CountryRepository countryRepo;
	
	@Autowired
	HierarchyRepository hierarchyRepo;

	
	@GetMapping("/countryList")
	public String findCountry(Model model) {
	    List<Country> countryList = countryRepo.findAll();
	    model.addAttribute("country", countryList);
	    return "countryList";
	}

	@GetMapping("/country/add")
	public String addCountry(Model model) {
		Country country = new Country();
		List<Hierarchy> hierarchy = hierarchyRepo.findAll();
		model.addAttribute("country", country);
		model.addAttribute("hierarchy", hierarchy);
		return "add_country";
		
	}
	
	@PostMapping("/country")
	public String saveCountry(@ModelAttribute("country") Country country, Model model) {
	    Hierarchy hierarchy = hierarchyRepo.findById(country.getLevel_uuid()).orElse(null);
	    if (hierarchy != null) {
	        country.setLevel_uuid(hierarchy.getUuid());
	        country.setParent_uuid(hierarchy.getKeyIdentifier());
	        countryRepo.save(country);
	        return "redirect:/countryList";
	    } else {
	        List<Hierarchy> hierarchyList = hierarchyRepo.findAll();
	        model.addAttribute("hierarchy", hierarchyList);
	        model.addAttribute("error", "Invalid Level UUID");
	        return "add_country";
	    }
	}



}

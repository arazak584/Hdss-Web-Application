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
	
	@PostMapping("/countryList")
	public String saveCountry(@ModelAttribute("country") Locationhierarchy country, Model model) {
	    Locationhierarchylevel locationhierarchylevel = hierarchyRepo.findById(country.getLevel_uuid()).orElse(null);
	    if (locationhierarchylevel != null) {
	        country.setLevel_uuid(locationhierarchylevel.getUuid());
	        //country.setParent_uuid(hierarchy.getKeyIdentifier());
	        hierarRepo.save(country);
	        return "redirect:/countryList";
	    } else {
	        List<Locationhierarchylevel> hierarchyList = hierarchyRepo.findAll();
	        model.addAttribute("hierarchy", hierarchyList);
	        model.addAttribute("error", "Invalid Level UUID");
	        return "add_country";
	    }
	}
	
	@GetMapping("/countryList/edit/{extId}")
	public String editCountry(@PathVariable("extId") String extId, Model model) {
		Locationhierarchy country = hierarRepo.findById(extId).orElse(null);
	if (country == null) {
	return "redirect:/countryList";
	}
	List<Locationhierarchylevel> hierarchyList = hierarchyRepo.findAll();
	model.addAttribute("country", country);
	model.addAttribute("hierarchy", hierarchyList);
	return "edit_country";
	}
	
	@PostMapping("/countryList/{extId}")
	public String updateCountry(@PathVariable("extId") String uuid, @ModelAttribute("country") Locationhierarchy country, Model model) {
	Locationhierarchylevel locationhierarchylevel = hierarchyRepo.findById(country.getLevel_uuid()).orElse(null);
	if (locationhierarchylevel != null) {
	country.setLevel_uuid(locationhierarchylevel.getUuid());
	//country.setParent_uuid(hierarchy.getKeyIdentifier());
	hierarRepo.save(country);
	return "redirect:/countryList";
	} else {
	List<Locationhierarchylevel> hierarchyList = hierarchyRepo.findAll();
	model.addAttribute("country", country);
	model.addAttribute("hierarchy", hierarchyList);
	model.addAttribute("error", "Invalid Level UUID");
	return "edit_country";
	}
	}
	
	
		//Region
		@GetMapping("/regionList")
		public String findRegion(Model model) {
		    List<Locationhierarchy> regionList = hierarRepo.findAll();
		    model.addAttribute("region", regionList);
		    return "regionList";
		}

		@GetMapping("/regionList/add")
		public String addRegion(Model model) {
			Locationhierarchy region = new Locationhierarchy();
			List<Locationhierarchylevel> locationhierarchylevel = hierarchyRepo.findAll();
			List<Locationhierarchy> country = hierarRepo.findAll();
			model.addAttribute("region", region);
			model.addAttribute("hierarchy", locationhierarchylevel);
			model.addAttribute("country", country);
			return "add_region";
			
		}
		/*
		@PostMapping("/regionList")
		public String saveRegion(@ModelAttribute("region") Region region, Model model) {
		    Locationhierarchylevel hierarchy = hierarchyRepo.findById(region.getLevel_uuid()).orElse(null);
		    Country country = countryRepo.findById(region.getCountryId()).orElse(null);
		    if (hierarchy != null) {
		        region.setLevel_uuid(hierarchy.getUuid());
		        region.setCountryId(country.getExtId());
		        //country.setParent_uuid(hierarchy.getKeyIdentifier());
		        regionRepo.save(region);
		        return "redirect:/regionList";
		    } else {
		        List<Locationhierarchylevel> hierarchyList = hierarchyRepo.findAll();
		        List<Country> countryList = countryRepo.findAll();
		        model.addAttribute("hierarchy", hierarchyList);
		        model.addAttribute("country", countryList);
		        model.addAttribute("error", "Invalid Level UUID");
		        return "add_region";
		    }
		}
		
		@GetMapping("/regionList/edit/{extId}")
		public String editRegion(@PathVariable("extId") String extId, Model model) {
		Region region = regionRepo.findById(extId).orElse(null);
		if (region == null) {
		return "redirect:/regionList";
		}
		List<Locationhierarchylevel> hierarchyList = hierarchyRepo.findAll();
		List<Country> countryList = countryRepo.findAll();
		model.addAttribute("region", region);
		model.addAttribute("hierarchy", hierarchyList);
		model.addAttribute("country", countryList);
		return "edit_region";
		}
		
		@PostMapping("/regionList/{extId}")
		public String updateRegion(@PathVariable("extId") String uuid, @ModelAttribute("region") Region region, Model model) {
		Locationhierarchylevel hierarchy = hierarchyRepo.findById(region.getLevel_uuid()).orElse(null);
		Country country = countryRepo.findById(region.getCountryId()).orElse(null);
		if (hierarchy != null) {
		region.setLevel_uuid(hierarchy.getUuid());
		region.setCountryId(country.getExtId());
		//country.setParent_uuid(hierarchy.getKeyIdentifier());
		regionRepo.save(region);
		return "redirect:/regionList";
		} else {
		List<Locationhierarchylevel> hierarchyList = hierarchyRepo.findAll();
		List<Country> countryList = countryRepo.findAll();
		model.addAttribute("region", region);
		model.addAttribute("hierarchy", hierarchyList);
		model.addAttribute("country", countryList);
		model.addAttribute("error", "Invalid Level UUID");
		return "edit_region";
		}
		}


		//District
				@GetMapping("/districtList")
				public String findDistrict(Model model) {
				    List<District> districtList = districtRepo.findAll();
				    model.addAttribute("district", districtList);
				    return "districtList";
				}

				@GetMapping("/districtList/add")
				public String addDistrict(Model model) {
					District district = new District();
					List<Locationhierarchylevel> hierarchy = hierarchyRepo.findAll();
					List<Region> region = regionRepo.findAll();
					model.addAttribute("district", district);
					model.addAttribute("hierarchy", hierarchy);
					model.addAttribute("region", region);
					return "add_district";
					
				}
				
				@PostMapping("/districtList")
				public String saveDistrict(@ModelAttribute("district") District district, Model model) {
				    Locationhierarchylevel hierarchy = hierarchyRepo.findById(district.getLevel_uuid()).orElse(null);
				    Region region = regionRepo.findById(district.getRegionId()).orElse(null);
				    if (hierarchy != null) {
				        region.setLevel_uuid(hierarchy.getUuid());
				        district.setRegionId(region.getExtId());
				        //country.setParent_uuid(hierarchy.getKeyIdentifier());
				        districtRepo.save(district);
				        return "redirect:/districtList";
				    } else {
				        List<Locationhierarchylevel> hierarchyList = hierarchyRepo.findAll();
				        List<Region> regionList = regionRepo.findAll();
				        model.addAttribute("hierarchy", hierarchyList);
				        model.addAttribute("region", regionList);
				        model.addAttribute("error", "Invalid Level UUID");
				        return "add_district";
				    }
				}
				
				@GetMapping("/districtList/edit/{extId}")
				public String editDistrict(@PathVariable("extId") String extId, Model model) {
				District district = districtRepo.findById(extId).orElse(null);
				if (district == null) {
				return "redirect:/districtList";
				}
				List<Locationhierarchylevel> hierarchyList = hierarchyRepo.findAll();
				List<Region> regionList = regionRepo.findAll();
				model.addAttribute("district", district);
				model.addAttribute("hierarchy", hierarchyList);
				model.addAttribute("region", regionList);
				return "edit_district";
				}
				
				@PostMapping("/districtList/{extId}")
				public String updateDistrict(@PathVariable("extId") String uuid, @ModelAttribute("district") District district, Model model) {
				Locationhierarchylevel hierarchy = hierarchyRepo.findById(district.getLevel_uuid()).orElse(null);
				Region region = regionRepo.findById(district.getRegionId()).orElse(null);
				if (hierarchy != null) {
				district.setLevel_uuid(hierarchy.getUuid());
				district.setRegionId(region.getExtId());
				//country.setParent_uuid(hierarchy.getKeyIdentifier());
				districtRepo.save(district);
				return "redirect:/districtList";
				} else {
				List<Locationhierarchylevel> hierarchyList = hierarchyRepo.findAll();
				List<Region> regionList = regionRepo.findAll();
				model.addAttribute("district", district);
				model.addAttribute("hierarchy", hierarchyList);
				model.addAttribute("region", regionList);
				model.addAttribute("error", "Invalid Level UUID");
				return "edit_district";
				}
				}
		*/
}

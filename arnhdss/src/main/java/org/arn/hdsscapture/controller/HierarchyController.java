package org.arn.hdsscapture.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.arn.hdsscapture.entity.Locationhierarchy;
import org.arn.hdsscapture.entity.Locationhierarchylevel;
import org.arn.hdsscapture.repository.LocationhierarchyRepository;
import org.arn.hdsscapture.repository.LocationhierarchylevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("")
public class HierarchyController {
	
	

	@Autowired
	LocationhierarchyRepository repo;
	@Autowired
	LocationhierarchylevelRepository level;

	// Community
	@GetMapping("/hierarchy/list")
	public String findHierarchy(@RequestParam(name = "level_uuid", required = false)  String level_uuid,
            Model model) {
		
		List<Locationhierarchylevel> villages = level.hierarchy();
		model.addAttribute("villages", villages);
		//model.addAttribute("villageCount", villages.size());

		// Fetch the count from DB and pass to Thymeleaf
	    Long count = level.hierarchycnt();  // This should return COUNT(uuid)
	    model.addAttribute("villageCount", count);
		
		if (level_uuid != null) {
	        List<Locationhierarchy> items = repo.Report(level_uuid);
	        model.addAttribute("items", items);
	    } else {
	    	model.addAttribute("message", "Select Level");
	    }

		return "hierarchy/hierarchy";
	}
	
	@GetMapping("/hierarchy/add")
	public String NewHierarchy(Model model,@RequestParam(name = "level_uuid", required = false)  String level_uuid,
	                       @RequestParam(name = "success", required = false) String success) {
		List<Locationhierarchylevel> villages = level.hierarchy();
		model.addAttribute("villages", villages);
		
		Locationhierarchy item = new Locationhierarchy();
	    model.addAttribute("item", item);
	    if (success != null) {
	        model.addAttribute("success", "Successfully saved");
	    }
	    return "hierarchy/hierarchy_new";
	}
	
	@PostMapping("/hierarchy/add")
    public String saveNewHierarchy(@ModelAttribute("item") Locationhierarchy item, BindingResult bindingResult, Model model) {
		
	    
	    List<Locationhierarchylevel> villages = level.hierarchy();
		model.addAttribute("villages", villages);
		model.addAttribute("levels", repo.apilist(item.getLevel_uuid()));
		
		//Generate a UUID
		String levelUuid = item.getLevel_uuid();

	    // 1. Extract the number from level_uuid, e.g., hierarchyLevelId4 -> 4
	    String levelNumber = "";
	    if (levelUuid != null) {
	        Pattern pattern = Pattern.compile("(\\d+)$");
	        Matcher matcher = pattern.matcher(levelUuid);
	        if (matcher.find()) {
	            levelNumber = matcher.group(1); // "4"
	        }
	    }

	    // 2. Count existing items in that level to generate next sequence
	    Integer count = repo.findMaxSequenceByLevelUuid(levelUuid); // We'll define this method
	    long nextNumber = count + 1;

	    // 3. Construct UUID like: hierarchy4_1
	    String finalUuid = "hierarchy" + levelNumber + "_" + nextNumber;
	    item.setUuid(finalUuid);

	    // 4. Check for duplicate extId
	    if (item.getExtId() != null && item.getName() != null) {
	        if (repo.codeErrs(item.getExtId()) > 0) {
	            model.addAttribute("error", " Already Exists.");
	            model.addAttribute("villages", villages);
	    		model.addAttribute("levels", repo.apilist(item.getLevel_uuid()));
	            return "hierarchy/hierarchy_new";
	        }
	    }	    

        // Save the Locationhierarchy object using the repository
        repo.save(item);

        return "redirect:/hierarchy/add?success";
    }
	
	@GetMapping("/hierarchy/edit/{uuids}")
	public String updateHierarchy(@PathVariable("uuids") String uuid, Model model,@RequestParam(name = "level_uuid", required = false)  String level_uuid) {
		List<Locationhierarchy> optionalItem = repo.findUuid(uuid);
		if (!optionalItem.isEmpty()) {
			Locationhierarchy item = optionalItem.get(0);
			model.addAttribute("item", item);
			
			List<Locationhierarchylevel> villages = level.hierarchy();
			model.addAttribute("villages", villages);
			
			model.addAttribute("levels", repo.apilist(item.getLevel_uuid())); 
			model.addAttribute("level_uuid", item.getLevel_uuid());
			
			return "hierarchy/hierarchy_update";
		} else {
			return "error";
		}
	}
	
	@PostMapping("/hierarchy/edit/{uuids}")
	public String updateHierarchyl(@PathVariable("uuids") String uuids, @ModelAttribute("item") Locationhierarchy item,
			BindingResult result, Model model,@RequestParam(name = "level_uuid", required = false) String level_uuid) {
		if (result.hasErrors()) {
			// Handle validation errors if necessary
			return "hierarchy_update";
		} else {
			List<Locationhierarchy> optionalItem = repo.findUuid(uuids);
			if (!optionalItem.isEmpty()) {
				Locationhierarchy existingItem = optionalItem.get(0);
				
				// Check if another record (not this one) has the same extId
			    boolean duplicateExtId = repo.findByExtId(item.getExtId()).stream()
			        .anyMatch(h -> !h.getUuid().equals(uuids));
			    
			    if (duplicateExtId) {
			        model.addAttribute("error", "Already exists.");
			        return "hierarchy/hierarchy_update";
			    }				
				
				existingItem.setExtId(item.getExtId());
				existingItem.setName(item.getName());
				existingItem.setParent_uuid(item.getParent_uuid());
				existingItem.setLevel_uuid(item.getLevel_uuid());
				existingItem.setTown(item.getTown());				
				
				repo.save(existingItem);
				return "redirect:/hierarchy/list?level_uuid=" + level_uuid;
			} else {
				return "error";
			}
		}
	}
	
	@GetMapping("/api/levels")
	@ResponseBody
	public List<Locationhierarchy> getLevels(@RequestParam("level_uuid") String level_uuid) {
	    return repo.apilist(level_uuid); // This should return the matching parent options
	}
	

		//	Hierarchy Level
		@GetMapping("/hierarchylevel/list")
		public String findHierarchylevel(Model model) {

			// Fetch the count from DB and pass to Thymeleaf
		    Long count = level.hierarchycnt();  // This should return COUNT(uuid)
		    model.addAttribute("villageCount", count);
			
		        List<Locationhierarchylevel> items = level.findAll();
		        model.addAttribute("items", items);

			return "hierarchy/hierarchylevel";
		}
		
		@GetMapping("/hierarchylevel/add")
		public String NewHierarchylevel(Model model, @RequestParam(name = "success", required = false) String success) {
		    
		    Locationhierarchylevel item = new Locationhierarchylevel();
		    
		    // Retrieve the maximum keyIdentifier from the database
		    Integer maxNumber = level.findMaxNumber();
		    
		    // Set the new keyIdentifier and uuid
		    int nextNumber = (maxNumber != null) ? maxNumber + 1 : 1;
		    item.setKeyIdentifier(nextNumber);
		    item.setUuid("hierarchyLevelId" + nextNumber);
		    
		    model.addAttribute("item", item);
		    if (success != null) {
		        model.addAttribute("success", "Successfully saved");
		    }
		    
		    return "hierarchy/hierarchylevel_new";
		}

		
		@PostMapping("/hierarchylevel/add")
	    public String saveNewHierarchylevel(@ModelAttribute("item") Locationhierarchylevel item, BindingResult bindingResult, Model model) {
			 //Generate a UUID
//			Integer levelUuid = item.getKeyIdentifier();
//		    // 3. Construct UUID like: hierarchy4_1
//		    String finalUuid = "hierarchyLevelId" + levelUuid;
//		    item.setUuid(finalUuid);

	        // Save the Locationhierarchy object using the repository
	        level.save(item);

	        return "redirect:/hierarchylevel/list";
	    }
		
		@GetMapping("/hierarchylevel/edit/{uuids}")
		public String updateHierarchylevel(@PathVariable("uuids") String uuid, Model model) {
			List<Locationhierarchylevel> optionalItem = level.findUuid(uuid);
			if (!optionalItem.isEmpty()) {
				Locationhierarchylevel item = optionalItem.get(0);
				model.addAttribute("item", item);
				
				
				return "hierarchy/hierarchylevel_update";
			} else {
				return "error";
			}
		}
		
		@PostMapping("/hierarchylevel/edit/{uuids}")
		public String updateHierarchylevel(@PathVariable("uuids") String uuids, @ModelAttribute("item") Locationhierarchylevel item,
				BindingResult result) {
			if (result.hasErrors()) {
				// Handle validation errors if necessary
				return "hierarchylevel_update";
			} else {
				List<Locationhierarchylevel> optionalItem = level.findUuid(uuids);
				if (!optionalItem.isEmpty()) {
					Locationhierarchylevel existingItem = optionalItem.get(0);
												
					existingItem.setName(item.getName());							
					level.save(existingItem);
					return "redirect:/hierarchylevel/list";
				} else {
					return "error";
				}
			}
		}

//	@GetMapping("/country/add")
//	public String addHierarchy(Model model,
//	                       @RequestParam(name = "success", required = false) String success) {
//		Locationhierarchy item = new Locationhierarchy();
//	    model.addAttribute("item", item);
//	    Hierarchy(model);
//	    if (success != null) {
//	        model.addAttribute("success", "Successfully saved");
//	    }
//	    return "hierarchy/hierarchy_add";
//	}
//
//	
//	@PostMapping("/country")
//    public String saveHierarchy(@ModelAttribute("item") Locationhierarchy item, BindingResult bindingResult, Model model) {
//		 //Generate a UUID for the Fieldworker's ID
//	    String uuid = UUID.randomUUID().toString();
//	    String uuidString = uuid.replaceAll("-", "");
//
//	    // Set the ID
//	    item.setUuid(uuidString);
//
//        // Save the Round object using the repository
//        repo.save(item);
//
//        return "redirect:/hierarchy/country/add?success";
//    }
//
//
//
//	@GetMapping("/country/edit/{uuids}")
//	public String editHierarchy(@PathVariable("uuids") String uuid, Model model) {
//		List<Locationhierarchy> optionalItem = repo.findUuid(uuid);
//		if (!optionalItem.isEmpty()) {
//			Locationhierarchy item = optionalItem.get(0);
//			// Add any other necessary data to the model attribute for editing
//			model.addAttribute("item", item);
//			Hierarchy(model);
//			return "hierarchy/hierarchy_edit";
//		} else {
//			return "error";
//		}
//	}
//
//
//	@PostMapping("/country/edit/{uuids}")
//	public String updateHierarchy(@PathVariable("uuids") String uuids, @ModelAttribute("item") Locationhierarchy item,
//			BindingResult result, Model model) {
//		if (result.hasErrors()) {
//			// Handle validation errors if necessary
//			return "hierarchy_edit";
//		} else {
//			List<Locationhierarchy> optionalItem = repo.findUuid(uuids);
//			if (!optionalItem.isEmpty()) {
//				Locationhierarchy existingItem = optionalItem.get(0);
//				existingItem.setExtId(item.getExtId());
//				existingItem.setName(item.getName());
//				existingItem.setParent_uuid(item.getParent_uuid());
//				existingItem.setLevel_uuid(item.getLevel_uuid());
//				repo.save(existingItem);
//				return "redirect:/hierarchy/country";
//			} else {
//				return "error";
//			}
//		}
//	}
//	
//	
//	private void Hierarchy(Model model) {
//        model.addAttribute("root", repo.root());
//        model.addAttribute("level", level.find1());
//        
//    }
//	
//	//Region
//	@GetMapping("/region")
//	public String findregion(Model model) {
//		List<Locationhierarchy> items = repo.region();
//		model.addAttribute("items", items);
//		Region(model);
//		return "hierarchy/region_list";
//	}
//
//	@GetMapping("/region/add")
//	public String addregion(Model model,
//	                       @RequestParam(name = "success", required = false) String success) {
//		Locationhierarchy item = new Locationhierarchy();
//	    model.addAttribute("item", item);
//	    Region(model);
//	    if (success != null) {
//	        model.addAttribute("success", "Successfully saved");
//	    }
//	    return "hierarchy/region_add";
//	}
//
//	
//	@PostMapping("/region")
//    public String saveregion(@ModelAttribute("item") Locationhierarchy item, BindingResult bindingResult, Model model) {
//		 //Generate a UUID for the Fieldworker's ID
//	    String uuid = UUID.randomUUID().toString();
//	    String uuidString = uuid.replaceAll("-", "");	    
//	    //String uuid = UUID.randomUUID().toString().replaceAll("-", "");
//	    // Set the ID
//	    item.setUuid(uuidString);
//	    
//	    if (item.getExtId() != null && item.getName() != null) {
//            // Check if a Codebook with the same CodeFeature and CodeValue already exists
//            if (repo.codeErrs(item.getExtId()) > 0) {
//                model.addAttribute("error", "Code already exists.");
//                Region(model);
//                return "hierarchy/region_add";
//            }
//        }
//
//        // Save the Round object using the repository
//        repo.save(item);
//        return "redirect:/hierarchy/region/add?success";
//    }
//
//
//
//	@GetMapping("/region/edit/{uuids}")
//	public String editregion(@PathVariable("uuids") String uuid, Model model) {
//		List<Locationhierarchy> optionalItem = repo.findUuid(uuid);
//		if (!optionalItem.isEmpty()) {
//			Locationhierarchy item = optionalItem.get(0);
//			// Add any other necessary data to the model attribute for editing
//			model.addAttribute("item", item);
//			Region(model);
//			return "hierarchy/region_edit";
//		} else {
//			return "error";
//		}
//	}
//
//	@PostMapping("/region/edit/{uuids}")
//	public String updateregion(@PathVariable("uuids") String uuids, @ModelAttribute("item") Locationhierarchy item,
//			BindingResult result, Model model) {
//		if (result.hasErrors()) {
//			// Handle validation errors if necessary
//			return "region_edit";
//		} else {
//			List<Locationhierarchy> optionalItem = repo.findUuid(uuids);
//			if (!optionalItem.isEmpty()) {
//				Locationhierarchy existingItem = optionalItem.get(0);
//				existingItem.setExtId(item.getExtId());
//				existingItem.setName(item.getName());
//				existingItem.setParent_uuid(item.getParent_uuid());
//				existingItem.setLevel_uuid(item.getLevel_uuid());
//				repo.save(existingItem);
//				return "redirect:/hierarchy/region";
//			} else {
//				return "error";
//			}
//		}
//	}
//
//	
//	private void Region(Model model) {
//        model.addAttribute("country", repo.country());
//        model.addAttribute("level2", level.find2());
//        
//    }
//	
//	
//		//district
//		@GetMapping("/district")
//		public String finddistrict(Model model) {
//			List<Locationhierarchy> items = repo.district();
//			model.addAttribute("items", items);
//			district(model);
//			return "hierarchy/district_list";
//		}
//
//		@GetMapping("/district/add")
//		public String adddistrict(Model model,
//		                       @RequestParam(name = "success", required = false) String success) {
//			Locationhierarchy item = new Locationhierarchy();
//		    model.addAttribute("item", item);
//		    district(model);
//		    if (success != null) {
//		        model.addAttribute("success", "Successfully saved");
//		    }
//		    return "hierarchy/district_add";
//		}
//
//		
//		@PostMapping("/district")
//	    public String savedistrict(@ModelAttribute("item") Locationhierarchy item, BindingResult bindingResult, Model model) {
//			 //Generate a UUID for the Fieldworker's ID
//		    String uuid = UUID.randomUUID().toString();
//		    String uuidString = uuid.replaceAll("-", "");
//
//		    // Set the ID
//		    item.setUuid(uuidString);
//		    
//		    if (item.getExtId() != null && item.getName() != null) {
//	            // Check if a Codebook with the same CodeFeature and CodeValue already exists
//	            if (repo.codeErrs(item.getExtId()) > 0) {
//	                model.addAttribute("error", "Code already exists.");
//	                district(model);
//	                return "hierarchy/district_add";
//	            }
//	        }
//
//	        // Save the Round object using the repository
//	        repo.save(item);
//
//	        return "redirect:/hierarchy/district/add?success";
//	    }
//
//
//
//		@GetMapping("/district/edit/{uuids}")
//		public String editdistrict(@PathVariable("uuids") String uuid, Model model) {
//			List<Locationhierarchy> optionalItem = repo.findUuid(uuid);
//			if (!optionalItem.isEmpty()) {
//				Locationhierarchy item = optionalItem.get(0);
//				// Add any other necessary data to the model attribute for editing
//				model.addAttribute("item", item);
//				district(model);
//				return "hierarchy/district_edit";
//			} else {
//				return "error";
//			}
//		}
//
//
//		@PostMapping("/district/edit/{uuids}")
//		public String updatedistrict(@PathVariable("uuids") String uuids, @ModelAttribute("item") Locationhierarchy item,
//				BindingResult result, Model model) {
//			if (result.hasErrors()) {
//				// Handle validation errors if necessary
//				return "district_edit";
//			} else {
//				List<Locationhierarchy> optionalItem = repo.findUuid(uuids);
//				if (!optionalItem.isEmpty()) {
//					Locationhierarchy existingItem = optionalItem.get(0);
//					existingItem.setExtId(item.getExtId());
//					existingItem.setName(item.getName());
//					existingItem.setParent_uuid(item.getParent_uuid());
//					existingItem.setLevel_uuid(item.getLevel_uuid());
//					repo.save(existingItem);
//					return "redirect:/hierarchy/district";
//				} else {
//					return "error";
//				}
//			}
//		}
//		
//		
//		private void district(Model model) {
//	        model.addAttribute("region", repo.region());
//	        model.addAttribute("level3", level.find3());
//	        
//	    }
//		
//		
//		//subdistrict
//		@GetMapping("/subdistrict")
//		public String findsubdistrict(Model model) {
//			List<Locationhierarchy> items = repo.subdistrict();
//			model.addAttribute("items", items);
//			subdistrict(model);
//			return "hierarchy/subdistrict_list";
//		}
//
//		@GetMapping("/subdistrict/add")
//		public String addsubdistrict(Model model,
//		                       @RequestParam(name = "success", required = false) String success) {
//			Locationhierarchy item = new Locationhierarchy();
//		    model.addAttribute("item", item);
//		    subdistrict(model);
//		    if (success != null) {
//		        model.addAttribute("success", "Successfully saved");
//		    }
//		    return "hierarchy/subdistrict_add";
//		}
//
//		
//		@PostMapping("/subdistrict")
//	    public String savesubdistrict(@ModelAttribute("item") Locationhierarchy item, BindingResult bindingResult, Model model) {
//			 //Generate a UUID for the Fieldworker's ID
//		    String uuid = UUID.randomUUID().toString();
//		    String uuidString = uuid.replaceAll("-", "");
//
//		    // Set the ID
//		    item.setUuid(uuidString);
//		    
//		    if (item.getExtId() != null && item.getName() != null) {
//	            // Check if a Codebook with the same CodeFeature and CodeValue already exists
//	            if (repo.codeErrs(item.getExtId()) > 0) {
//	                model.addAttribute("error", "Code already exists.");
//	                subdistrict(model);
//	                return "hierarchy/subdistrict_add";
//	            }
//	        }
//
//	        // Save the Round object using the repository
//	        repo.save(item);
//
//	        return "redirect:/hierarchy/subdistrict/add?success";
//	    }
//
//
//
//		@GetMapping("/subdistrict/edit/{uuids}")
//		public String editsubdistrict(@PathVariable("uuids") String uuid, Model model) {
//			List<Locationhierarchy> optionalItem = repo.findUuid(uuid);
//			if (!optionalItem.isEmpty()) {
//				Locationhierarchy item = optionalItem.get(0);
//				// Add any other necessary data to the model attribute for editing
//				model.addAttribute("item", item);
//				subdistrict(model);
//				return "hierarchy/subdistrict_edit";
//			} else {
//				return "error";
//			}
//		}
//
//
//		@PostMapping("/subdistrict/edit/{uuids}")
//		public String updatesubdistrict(@PathVariable("uuids") String uuids, @ModelAttribute("item") Locationhierarchy item,
//				BindingResult result, Model model) {
//			if (result.hasErrors()) {
//				// Handle validation errors if necessary
//				return "subdistrict_edit";
//			} else {
//				List<Locationhierarchy> optionalItem = repo.findUuid(uuids);
//				if (!optionalItem.isEmpty()) {
//					Locationhierarchy existingItem = optionalItem.get(0);
//					existingItem.setExtId(item.getExtId());
//					existingItem.setName(item.getName());
//					existingItem.setParent_uuid(item.getParent_uuid());
//					existingItem.setLevel_uuid(item.getLevel_uuid());
//					
//					if (item.getExtId() != null && item.getName() != null) {
//			            // Check if a Extid already exists
//			            if (repo.codeErrs(item.getExtId()) > 0) {
//			                model.addAttribute("error", "Code already exists.");
//			                subdistrict(model);
//			                return "hierarchy/subdistrict_add";
//			            }
//			        }
//					
//					repo.save(existingItem);
//					return "redirect:/hierarchy/subdistrict";
//				} else {
//					return "error";
//				}
//			}
//		}
//		
//		
//		private void subdistrict(Model model) {
//	        model.addAttribute("district", repo.district());
//	        model.addAttribute("level4", level.find4());
//	        
//	    }
//		
//		
//		//village
//				@GetMapping("/village")
//				public String findvillage(Model model) {
//					List<Locationhierarchy> items = repo.village();
//					model.addAttribute("items", items);
//					village(model);
//					return "hierarchy/village_list";
//				}
//
//				@GetMapping("/village/add")
//				public String addvillage(Model model,
//				                       @RequestParam(name = "success", required = false) String success) {
//					Locationhierarchy item = new Locationhierarchy();
//				    model.addAttribute("item", item);
//				    village(model);
//				    if (success != null) {
//				        model.addAttribute("success", "Successfully saved");
//				    }
//				    return "hierarchy/village_add";
//				}
//
//				
//				@PostMapping("/village")
//			    public String savevillage(@ModelAttribute("item") Locationhierarchy item, BindingResult bindingResult, Model model) {
//					 //Generate a UUID for the Fieldworker's ID
//				    String uuid = UUID.randomUUID().toString();
//				    String uuidString = uuid.replaceAll("-", "");
//
//				    // Set the ID
//				    item.setUuid(uuidString);
//				    
//				    if (item.getExtId() != null && item.getName() != null) {
//			            // Check if a Codebook with the same CodeFeature and CodeValue already exists
//			            if (repo.codeErrs(item.getExtId()) > 0) {
//			                model.addAttribute("error", "Code already exists.");
//			                village(model);
//			                return "hierarchy/village_add";
//			            }
//			        }
//
//			        // Save the Round object using the repository
//			        repo.save(item);
//
//			        return "redirect:/hierarchy/village/add?success";
//			    }
//
//
//
//				@GetMapping("/village/edit/{uuids}")
//				public String editvillage(@PathVariable("uuids") String uuid, Model model) {
//					List<Locationhierarchy> optionalItem = repo.findUuid(uuid);
//					if (!optionalItem.isEmpty()) {
//						Locationhierarchy item = optionalItem.get(0);
//						// Add any other necessary data to the model attribute for editing
//						model.addAttribute("item", item);
//						village(model);
//						return "hierarchy/village_edit";
//					} else {
//						return "error";
//					}
//				}
//
//
//				@PostMapping("/village/edit/{uuids}")
//				public String updatevillage(@PathVariable("uuids") String uuids, @ModelAttribute("item") Locationhierarchy item,
//						BindingResult result, Model model) {
//					if (result.hasErrors()) {
//						// Handle validation errors if necessary
//						return "village_edit";
//					} else {
//						List<Locationhierarchy> optionalItem = repo.findUuid(uuids);
//						if (!optionalItem.isEmpty()) {
//							Locationhierarchy existingItem = optionalItem.get(0);
//							existingItem.setExtId(item.getExtId());
//							existingItem.setName(item.getName());
//							existingItem.setParent_uuid(item.getParent_uuid());
//							existingItem.setLevel_uuid(item.getLevel_uuid());
//							
//							if (item.getExtId() != null && item.getName() != null) {
//					            // Check if a Extid already exists
//					            if (repo.codeErrs(item.getExtId()) > 0) {
//					                model.addAttribute("error", "Code already exists.");
//					                village(model);
//					                return "hierarchy/village_add";
//					            }
//					        }
//							
//							repo.save(existingItem);
//							return "redirect:/hierarchy/village";
//						} else {
//							return "error";
//						}
//					}
//				}
//				
//				
//				private void village(Model model) {
//			        model.addAttribute("subdistrict", repo.subdistrict());
//			        model.addAttribute("level5", level.find5());
//			        
//			    }
//				
//				
//				//subvillage
//				@GetMapping("/subvillage")
//				public String findsubvillage(Model model) {
//					List<Locationhierarchy> items = repo.subvillage();
//					model.addAttribute("items", items);
//					subvillage(model);
//					return "hierarchy/subvillage_list";
//				}
//
//				@GetMapping("/subvillage/add")
//				public String addsubvillage(Model model,
//				                       @RequestParam(name = "success", required = false) String success) {
//					Locationhierarchy item = new Locationhierarchy();
//				    model.addAttribute("item", item);
//				    subvillage(model);
//				    if (success != null) {
//				        model.addAttribute("success", "Successfully saved");
//				    }
//				    return "hierarchy/subvillage_add";
//				}
//
//				
//				@PostMapping("/subvillage")
//			    public String savesubvillage(@ModelAttribute("item") Locationhierarchy item, BindingResult bindingResult, Model model) {
//					 //Generate a UUID for the Fieldworker's ID
//				    String uuid = UUID.randomUUID().toString();
//				    String uuidString = uuid.replaceAll("-", "");
//
//				    // Set the ID
//				    item.setUuid(uuidString);
//				    
//				    if (item.getExtId() != null && item.getName() != null) {
//			            // Check if a Codebook with the same CodeFeature and CodeValue already exists
//			            if (repo.codeErrs(item.getExtId()) > 0) {
//			                model.addAttribute("error", "Code already exists.");
//			                subvillage(model);
//			                return "hierarchy/subvillage_add";
//			            }
//			        }
//
//			        // Save the Round object using the repository
//			        repo.save(item);
//
//			        return "redirect:/hierarchy/subvillage/add?success";
//			    }
//
//
//
//				@GetMapping("/subvillage/edit/{uuids}")
//				public String editsubvillage(@PathVariable("uuids") String uuid, Model model) {
//					List<Locationhierarchy> optionalItem = repo.findUuid(uuid);
//					if (!optionalItem.isEmpty()) {
//						Locationhierarchy item = optionalItem.get(0);
//						// Add any other necessary data to the model attribute for editing
//						model.addAttribute("item", item);
//						subvillage(model);
//						return "hierarchy/subvillage_edit";
//					} else {
//						return "error";
//					}
//				}
//
//
//				@PostMapping("/subvillage/edit/{uuids}")
//				public String updatesubvillage(@PathVariable("uuids") String uuids, @ModelAttribute("item") Locationhierarchy item,
//						BindingResult result, Model model) {
//					if (result.hasErrors()) {
//						// Handle validation errors if necessary
//						return "subvillage_edit";
//					} else {
//						List<Locationhierarchy> optionalItem = repo.findUuid(uuids);
//						if (!optionalItem.isEmpty()) {
//							Locationhierarchy existingItem = optionalItem.get(0);
//							existingItem.setExtId(item.getExtId());
//							existingItem.setName(item.getName());
//							existingItem.setParent_uuid(item.getParent_uuid());
//							existingItem.setLevel_uuid(item.getLevel_uuid());
//							
//							if (item.getExtId() != null && item.getName() != null) {
//					            // Check if a Extid already exists
//					            if (repo.codeErrs(item.getExtId()) > 0) {
//					                model.addAttribute("error", "Code already exists.");
//					                subvillage(model);
//					                return "hierarchy/subvillage_add";
//					            }
//					        }
//							
//							repo.save(existingItem);
//							return "redirect:/hierarchy/subvillage";
//						} else {
//							return "error";
//						}
//					}
//				}
//				
//				
//				private void subvillage(Model model) {
//			        model.addAttribute("village", repo.village());
//			        model.addAttribute("level6", level.find6());
//			        
//			    }

}

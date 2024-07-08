package org.arn.hdsscapture.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.arn.hdsscapture.entity.Codebook;
import org.arn.hdsscapture.entity.CommunityReport;
import org.arn.hdsscapture.entity.DataDictionary;
import org.arn.hdsscapture.entity.Fieldworker;
import org.arn.hdsscapture.entity.Round;
import org.arn.hdsscapture.entity.Settings;
import org.arn.hdsscapture.entity.Task;
import org.arn.hdsscapture.odk.OdkRepository;
import org.arn.hdsscapture.odk.ODK;
import org.arn.hdsscapture.repository.CodebookRepository;
import org.arn.hdsscapture.repository.CommunityRepository;
import org.arn.hdsscapture.repository.DictionaryRepository;
import org.arn.hdsscapture.repository.FieldworkerRepository;
import org.arn.hdsscapture.repository.LocationhierarchyRepository;
import org.arn.hdsscapture.repository.RoundRepository;
import org.arn.hdsscapture.repository.SettingsRepository;
import org.arn.hdsscapture.repository.TaskRepository;
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




@Controller
@RequestMapping("/utility")
public class UtilityController {

	@Autowired
	FieldworkerRepository repo;
	@Autowired
	CodebookRepository code;
	@Autowired
	DictionaryRepository dic;

	@GetMapping("/fw")
	public String fw(Model model
			) {
		List<Fieldworker> items = repo.findFieldworker();

		//System.err.println("Size of list " + items.size());
		
		model.addAttribute("items", items);
		

		return "utility/fw_list";
	}
	
	@GetMapping("/activefw")
	public String activefw(Model model
			) {
		List<Fieldworker> items = repo.fw();

		//System.err.println("Size of list " + items.size());
		
		model.addAttribute("items", items);
		

		return "report/activefw";
	}

	@GetMapping("/fw/add")
	public String addFieldworker(Model model,
			@RequestParam(name = "success", required=false) String success
			) {
		Fieldworker fieldworker = new Fieldworker();
		model.addAttribute("fieldworker", fieldworker);
		if(success != null) {
			model.addAttribute("success", "Successfully saved. Username: " + success);
		}
		return "utility/fw_add";
		
	}

	@PostMapping("/fw")
	public String saveFieldworker(@ModelAttribute("fieldworker") Fieldworker fieldworker, Model model) {
		// Generate a UUID for the Fieldworker's ID
		String uuid = UUID.randomUUID().toString();
		String uuidString = uuid.toString().replaceAll("-", "");
		// Set the ID of the Fieldworker object
		fieldworker.setFw_uuid(uuidString);
		if (fieldworker.insertDate == null) {
			fieldworker.insertDate = new Date();
		}

		// Generate username based on initials and incrementing sequence number
		String initials = (String.valueOf(fieldworker.getFirstName().charAt(0))
				+ String.valueOf(fieldworker.getLastName().charAt(0))).toUpperCase();
		int sequenceNumber = 1;
		String fw = "FW";
		String username = fw + initials + sequenceNumber;
		Fieldworker existingFieldworker = repo.findByUsername(username);

		// Check if username already exists and increment sequence number if it does
		while (existingFieldworker != null) {
			sequenceNumber++;
			username = fw + initials + sequenceNumber;
			existingFieldworker = repo.findByUsername(username);
		}

		// Set the username of the Fieldworker object
		fieldworker.setUsername(username);

		// Save the fieldworker to the database
		repo.save(fieldworker);
		
		return "redirect:/utility/fw/add?success=" + username;
	}

	@GetMapping("/fw/edit/{username}")
	public String editFieldworker(@PathVariable("username") String username, Model model) {
		List<Fieldworker> optionalFieldworker = repo.findFieldworkerByUsername(username);
		if (!optionalFieldworker.isEmpty()) {
			Fieldworker fieldworker = optionalFieldworker.get(0);
			// Add any other necessary data to the model attribute for editing
			model.addAttribute("fieldworker", fieldworker);
			return "utility/fw_edit";
		} else {
			// Handle case where Fieldworker object is not found
			return "error";
		}
	}

	@PostMapping("/fw/{username}")
	public String updateFieldworker(@PathVariable("username") String username,
			@ModelAttribute("fieldworker") Fieldworker fieldworker, BindingResult result, Model model) {
		if (result.hasErrors()) {
			// Handle validation errors if necessary
			return "utility/fw_edit";
		} else {
			List<Fieldworker> optionalFieldworker = repo.findFieldworkerByUsername(username);
			if (!optionalFieldworker.isEmpty()) {
				Fieldworker existingFieldworker = optionalFieldworker.get(0);
				existingFieldworker.setUsername(fieldworker.getUsername());
				existingFieldworker.setFirstName(fieldworker.getFirstName());
				existingFieldworker.setLastName(fieldworker.getLastName());
				existingFieldworker.setPassword(fieldworker.getPassword());
				existingFieldworker.setStatus(fieldworker.getStatus());
				// Update any other necessary properties of the Fieldworker object
				repo.save(existingFieldworker);
				return "redirect:/utility/fw";
			} else {
				// Handle case where Fieldworker object is not found
				return "error";
			}
		}
	}

	@Autowired
	RoundRepository roundrepo;

	// Round
	@GetMapping("/round")
	public String findRound(Model model) {
		List<Round> rounds = roundrepo.findRound();
		model.addAttribute("rounds", rounds);
		return "utility/round_list";
	}

	@GetMapping("/round/add")
	public String addRound(Model model,
	                       @RequestParam(name = "success", required = false) String success) {
	    Round round = new Round();

	    // Retrieve the maximum roundNumber from the database
	    Integer maxRoundNumber = roundrepo.findMaxRoundNumber();

	    // If maxRoundNumber is not null, increment it by 1; otherwise, set it to 1
	    round.setRoundNumber((maxRoundNumber != null) ? maxRoundNumber + 1 : 1);

	    model.addAttribute("round", round);

	    if (success != null) {
	        model.addAttribute("success", "Successfully saved. Round: " + success);
	    }

	    return "utility/round_add";
	}

	
	@PostMapping("/round")
    public String saveRound(@ModelAttribute("round") Round round, BindingResult bindingResult, Model model) {
		 //Generate a UUID for the Fieldworker's ID
	    String uuid = UUID.randomUUID().toString();
	    String uuidString = uuid.replaceAll("-", "");
	    
	    Round rond = roundrepo.endD();
	    
	    // Set the ID of the Fieldworker object
	    round.setUuid(uuidString);
        // Your validation logic here, if needed
        if (round.getStartDate() != null && round.getEndDate() != null && round.getEndDate().before(round.getStartDate())) {
            model.addAttribute("error", "Invalid date range. Please make sure the end date is not before the start date.");
            return "utility/round_add";
        } 
        
        if(round.getStartDate().before(rond.getEndDate())) {
        	model.addAttribute("error", "Invalid date range. Start date is before previous end date.");
            return "utility/round_add";
        }

        // Save the Round object using the repository
        roundrepo.save(round);

        return "redirect:/utility/round/add?success";
    }



	@GetMapping("/round/edit/{roundNumber}")
	public String editRound(@PathVariable("roundNumber") Integer roundNumber, Model model) {
		List<Round> optionalROund = roundrepo.findFieldworkerByroundNumber(roundNumber);
		if (!optionalROund.isEmpty()) {
			Round round = optionalROund.get(0);
			// Add any other necessary data to the model attribute for editing
			model.addAttribute("round", round);
			return "utility/round_edit";
		} else {
			return "error";
		}
	}

	@PostMapping("/round/{roundNumber}")
	public String updateRound(@PathVariable("roundNumber") Integer roundNumber, @ModelAttribute("round") Round round,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			// Handle validation errors if necessary
			return "round_edit";
		} else {
			List<Round> optionalRound = roundrepo.findFieldworkerByroundNumber(roundNumber);
			if (!optionalRound.isEmpty()) {
				Round existingRound = optionalRound.get(0);
				existingRound.setRoundNumber(round.getRoundNumber());
				existingRound.setRemark(round.getRemark());
				existingRound.setStartDate(round.getStartDate());
				existingRound.setEndDate(round.getEndDate());
				roundrepo.save(existingRound);
				return "redirect:/utility/round";
			} else {
				return "error";
			}
		}
	}
	
	@Autowired
	TaskRepository taskrepo;

	// Round
	@GetMapping("/task")
	public String findTask(Model model) {
		List<Task> items = taskrepo.findAll();
		
		//System.err.println("Size of list " + items.size());
		
		model.addAttribute("items", items);
		return "utility/task";
	}
	
	@Autowired
	SettingsRepository settingsrepo;
	
	
	@GetMapping("/setting")
	public String findSettings(Model model) {
//		List<Settings> settings = settingsrepo.findAll();
//		model.addAttribute("settings", settings);
		return "utility/settings_list";
	}
	

	
	@GetMapping("/assignment")
	public String Assignarea(Model model) {
//		List<Locationhierarchy> hierarchy = loc.villages();
//		model.addAttribute("hierarchy", hierarchy);
		return "utility/area_list";
	}
	
	
	@Autowired
	OdkRepository odkrepo;
	
	@Autowired
	CodebookRepository codebook;

	@GetMapping("/extra-forms")
	public String findOdk(Model model) {
		
		List<ODK> items = odkrepo.findAll();
		
		
		model.addAttribute("items", items);
		return "utility/odk_list";
	}

	@GetMapping("/extra-forms/add")
	public String addODK(Model model,
	                       @RequestParam(name = "success", required = false) String success, String error) {
	    ODK odk = new ODK();
	    
	    List<Codebook> items = codebook.odk_gender();
//	    gender.forEach(codebook -> System.out.println("Codebook: " + codebook));
		model.addAttribute("items", items);
		
//	    List<Codebook> modules = codebook.modules();
//		model.addAttribute("modules", modules);
		
		List<Codebook> enabled = codebook.enabled();
		model.addAttribute("enabled", enabled);

	    model.addAttribute("odk", odk);

	    if (success != null) {
	        model.addAttribute("success", "Successfully saved. " + success);
	    }
	    
	    if (error != null) {
	        model.addAttribute("error", "formID already Exists " + error);
	    }

	    return "utility/odk_add";
	}

	
	@PostMapping("/extra-forms")
    public String saveOdk(@ModelAttribute("odk") ODK odk, BindingResult bindingResult, Model model) {
		
	    List<Codebook> items = codebook.odk_gender();
		model.addAttribute("items", items);
		
//	    List<Codebook> modules = codebook.modules();
//		model.addAttribute("modules", modules);
		
		List<Codebook> enabled = codebook.enabled();
		model.addAttribute("enabled", enabled);
		
		boolean formIDExists = odkrepo.findByformID(odk.getFormID()).isPresent();

		if (formIDExists) {
			//bindingResult.rejectValue("formID", "formID.exists");
			model.addAttribute("error", "Form ID Exists");
            return "utility/odk_add";
		}

		if (odk.insertDate == null) {
			odk.insertDate = new Date();
		}
 

        // Save the Round object using the repository
        odkrepo.save(odk);

        return "redirect:/utility/extra-forms/add?success";
    }



	@GetMapping("/extra-forms/edit/{id}")
	public String editOdk(@PathVariable("id") Integer id, Model model) {
		List<ODK> optionalOdk = odkrepo.findID(id);
		if (!optionalOdk.isEmpty()) {
			ODK odk = optionalOdk.get(0);
			
		    List<Codebook> items = codebook.odk_gender();
			model.addAttribute("items", items);
			
//		    List<Codebook> modules = codebook.modules();
//			model.addAttribute("modules", modules);
			
			List<Codebook> enabled = codebook.enabled();
			model.addAttribute("enabled", enabled);
			// Add any other necessary data to the model attribute for editing
			model.addAttribute("odk", odk);
			return "utility/odk_edit";
		} else {
			return "error";
		}
	}

	@PostMapping("/extra-forms/{id}")
	public String updateOdk(@PathVariable("id") Integer id, @ModelAttribute("round") ODK odk,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			// Handle validation errors if necessary
			return "odk_edit";
		} else {
			List<ODK> optionalOdk = odkrepo.findID(id);
			if (!optionalOdk.isEmpty()) {
				ODK existingOdk = optionalOdk.get(0);
				existingOdk.setFormID(odk.getFormID());
				//existingOdk.setInsertDate(odk.getInsertDate());
				existingOdk.setFormName(odk.getFormName());
				existingOdk.setFormDesc(odk.getFormDesc());
				existingOdk.setGender(odk.getGender());
				existingOdk.setEnabled(odk.getEnabled());
				existingOdk.setMinAge(odk.getMinAge());
				existingOdk.setMaxAge(odk.getMaxAge());
				odkrepo.save(existingOdk);
				return "redirect:/utility/extra-forms";
			} else {
				return "error";
			}
		}
	}

	

	@Autowired
	SettingsRepository setrepo;

	@GetMapping("/parameters")
	public String settings(Model model
			) {
		List<Settings> items = setrepo.findAll();

		//System.err.println("Size of list " + items.size());
		
		model.addAttribute("items", items);
		

		return "utility/settings";
	}
	
	
	@GetMapping("/parameters/edit/{id}")
	public String editParameters(@PathVariable("id") Integer id, Model model) {
		List<Settings> optionalSettings = setrepo.findBy(id);
		if (!optionalSettings.isEmpty()) {
			Settings settings = optionalSettings.get(0);
			// Add any other necessary data to the model attribute for editing
			model.addAttribute("settings", settings);
			return "utility/settings_edit";
		} else {
			// Handle case where Fieldworker object is not found
			return "error";
		}
	}

	@PostMapping("/parameters/{id}")
	public String updateSettings(@PathVariable("id") Integer id,
			@ModelAttribute("settings") Settings settings, BindingResult result, Model model) {
		
		if (settings.isUpdates() == true && settings.isEnumeration() == true) {
            model.addAttribute("error", "Update & Enumeration Cannot run Concurrently");
            return "utility/settings_edit";
        }
		
		
		if (result.hasErrors()) {
			// Handle validation errors if necessary
			return "utility/settings_edit";
		} else {
			List<Settings> optionalSettings = setrepo.findBy(id);
			if (!optionalSettings.isEmpty()) {
				Settings existingSettings = optionalSettings.get(0);
				existingSettings.setEarliestDate(settings.getEarliestDate());
				existingSettings.setEnumeration(settings.isEnumeration());
				existingSettings.setFather_age(settings.getFather_age());
				existingSettings.setHoh_age(settings.getHoh_age());
				existingSettings.setMother_age(settings.getMother_age());
				existingSettings.setRel_age(settings.getRel_age());
				existingSettings.setUpdates(settings.isUpdates());
				existingSettings.setSesDate(settings.getSesDate());
				existingSettings.setSite(settings.getSite());
				// Update any other necessary properties of the object
				setrepo.save(existingSettings);
				return "redirect:/utility/parameters";
			} else {
				// Handle case where Fieldworker object is not found
				return "error";
			}
		}
	}
	
	
	
	@Autowired
	CommunityRepository comrepo;
	@Autowired
	LocationhierarchyRepository locrepo;

	// Community
	@GetMapping("/community")
	public String findCom(Model model) {
		List<CommunityReport> items = comrepo.findAll();
		model.addAttribute("items", items);
		Community(model);
		return "utility/community_list";
	}

	@GetMapping("/community/add")
	public String addCommunity(Model model,
	                       @RequestParam(name = "success", required = false) String success, Principal principal) {
		CommunityReport item = new CommunityReport();
		
		String userName = principal.getName();

        if (item.getFw_uuid() == null || item.getFw_uuid().isEmpty()) {
            item.setFw_uuid(userName);
        }

	    model.addAttribute("item", item);

	    if (success != null) {
	        model.addAttribute("success", "Successfully saved");
	    }
	    Community(model);
	    return "utility/community_add";
	}

	
	@PostMapping("/community")
    public String saveCommunity(@ModelAttribute("item") CommunityReport item, BindingResult bindingResult, Model model, Principal principal) {
		 //Generate a UUID for the Fieldworker's ID
	    String uuid = UUID.randomUUID().toString();
	    //String uuidString = uuid.replaceAll("-", "");

	    String userName = principal.getName();
	    // Set the ID
	    item.setUuid(uuid);
	    item.setInsertDate(new Date());
	    if (item.getFw_uuid() == null || item.getFw_uuid().isEmpty()) {
            item.setFw_uuid(userName);
        }

        // Save the Round object using the repository
        comrepo.save(item);

        return "redirect:/utility/community/add?success";
    }



	@GetMapping("/community/edit/{uuid}")
	public String editCom(@PathVariable("uuid") String uuid, Model model) {
		List<CommunityReport> optionalCom = comrepo.findCom(uuid);
		if (!optionalCom.isEmpty()) {
			CommunityReport item = optionalCom.get(0);
			// Add any other necessary data to the model attribute for editing
			model.addAttribute("item", item);
			Community(model);
			return "utility/community_edit";
		} else {
			return "error";
		}
	}
	
	private void Community(Model model) {
        model.addAttribute("community", locrepo.village());
        model.addAttribute("itemlist", code.itemlist());
    }

	@PostMapping("/community/{uuid}")
	public String updateCom(@PathVariable("uuid") String uuid, @ModelAttribute("item") CommunityReport item,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			// Handle validation errors if necessary
			return "community_edit";
		} else {
			List<CommunityReport> optionalItem = comrepo.findCom(uuid);
			if (!optionalItem.isEmpty()) {
				CommunityReport existingItem = optionalItem.get(0);
				existingItem.setName(item.getName());
				existingItem.setCommunity(item.getCommunity());
				existingItem.setDescription(item.getDescription());
				existingItem.setName(item.getName());
				comrepo.save(existingItem);
				return "redirect:/utility/community";
			} else {
				return "error";
			}
		}
	}
	
	


	// Codebook
	@GetMapping("/codebook")
	public String findCode(Model model) {
		List<Codebook> items = code.findAll();
		model.addAttribute("items", items);
		
		return "utility/codebook_list";
	}

	@GetMapping("/codebook/add")
	public String addCode(Model model,
	                       @RequestParam(name = "success", required = false) String success) {
		Codebook item = new Codebook();

	    model.addAttribute("item", item);

	    if (success != null) {
	        model.addAttribute("success", "Codebook entry added successfully ");
	    }
	    Code(model);
	    return "utility/codebook_add";
	}
	
	@PostMapping("/codebook")
    public String saveCodebook(@ModelAttribute("item") Codebook item, BindingResult bindingResult, Model model) {
		 //Generate a UUID for the Fieldworker's ID
		if (item.getCodeFeature() != null && item.getCodeValue() != null) {
            // Check if a Codebook with the same CodeFeature and CodeValue already exists
            if (code.codeErr(item.getCodeFeature(), item.getCodeValue()) > 0) {
                model.addAttribute("error", "Code Value with the same Value and Code Feature already exists.");
                Code(model);
                return "utility/codebook_add";
            }
        }


        // Save the Round object using the repository
        code.save(item);
        ///model.addAttribute("success", "Codebook entry added successfully ");

        return "redirect:/utility/codebook/add?success";
    }
	
	@GetMapping("/codebook/adds")
	public String addCodeV(Model model,
	                       @RequestParam(name = "success", required = false) String success) {
		Codebook item = new Codebook();

	    model.addAttribute("item", item);

	    if (success != null) {
	        model.addAttribute("success", "Codebook Variable added successfully " );
	    }
	    return "utility/codebook_adds";
	}

	@PostMapping("/codebooks")
    public String saveCodebookv(@ModelAttribute("item") Codebook item, BindingResult bindingResult, Model model) {
		 //Generate a UUID for the Fieldworker's ID
		if (item.getCodeFeature() != null && item.getCodeValue() != null) {
            // Check if a Codebook with the same CodeFeature and CodeValue already exists
            if (code.codeErrs(item.getCodeFeature()) > 0) {
                model.addAttribute("error", "Code Feature already exists.");
                Code(model);
                return "utility/codebook_adds";
            }
        }


        // Save the Round object using the repository
        code.save(item);
        ///model.addAttribute("success", "Codebook entry added successfully ");

        return "redirect:/utility/codebook/adds?success";
    }



	@GetMapping("/codebook/edit/{uuid}")
	public String editCode(@PathVariable("uuid") String uuid, Model model) {
		List<Codebook> optionalCom = code.findCode(uuid);
		if (!optionalCom.isEmpty()) {
			Codebook item = optionalCom.get(0);
			// Add any other necessary data to the model attribute for editing
			model.addAttribute("item", item);
			Code(model);
			return "utility/codebook_edit";
		} else {
			return "error";
		}
	}
	
	private void Code(Model model) {
        model.addAttribute("codeFeature", code.codeFeature());
    }

	@PostMapping("/codebook/{uuid}")
	public String updateCom(@PathVariable("uuid") String uuid, @ModelAttribute("item") Codebook item,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			// Handle validation errors if necessary
			return "codebook_edit";
		} else {
			List<Codebook> optionalItem = code.findCode(uuid);
			if (!optionalItem.isEmpty()) {
				Codebook existingItem = optionalItem.get(0);
				existingItem.setCodeFeature(item.getCodeFeature());
				existingItem.setCodeLabel(item.getCodeLabel());
				existingItem.setCodeValue(item.getCodeValue());
				code.save(existingItem);
				return "redirect:/utility/codebook";
			} else {
				return "error";
			}
		}
	}
	
	
	//Data Dictionary
	@GetMapping("/dictionary")
	public String findDic(@RequestParam(name = "ev", required = false)  String ev,Model model) {
		
		List<DataDictionary> fws = dic.findEid();
		//System.out.println("Villages: " + villages);
		model.addAttribute("fws", fws);
		
		if(ev !=null ) {
		List<DataDictionary> items = dic.findEvent(ev);
		model.addAttribute("items", items);
		model.addAttribute("selected", ev);
		}else {
			
		}
		
		
		return "utility/dictionary_list";
	}
	


	@GetMapping("/dictionary/add")
	public String addDic(Model model,
	                       @RequestParam(name = "success", required = false) String success, Principal principal) {
		DataDictionary item = new DataDictionary();
		
		//List of items in dropdown
		List<DataDictionary> fws = dic.findEid();
		model.addAttribute("fws", fws);

	    model.addAttribute("item", item);

	    if (success != null) {
	        model.addAttribute("success", "Successfully saved");
	    }
	    Community(model);
	    return "utility/community_add";
	}

	
	@PostMapping("/dictionary")
    public String saveDic(@ModelAttribute("item") DataDictionary item, BindingResult bindingResult, Model model, Principal principal) {
		 //Generate a UUID for the Fieldworker's ID
	   		   

        // Save the Round object using the repository
        dic.save(item);

        return "redirect:/utility/dictionary/add?success";
    }


	@GetMapping("/dictionary/edit/{uuid}")
	public String editDic(@PathVariable("uuid") Integer uuid,@RequestParam(name = "ev", required = false) String ev, Model model) {
		List<DataDictionary> optionalCom = dic.findId(uuid);
		if (!optionalCom.isEmpty()) {
			DataDictionary item = optionalCom.get(0);
			// Add any other necessary data to the model attribute for editing
			model.addAttribute("item", item);
			model.addAttribute("ev", ev);
			return "utility/dictionary_edit";
		} else {
			return "error";
		}
	}
	

	@PostMapping("/dictionary/{uuid}")
	public String updateDict(@PathVariable("uuid") Integer uuid,@RequestParam(name = "ev", required = false) String ev, @ModelAttribute("item") DataDictionary item,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("ev", ev);
			// Handle validation errors if necessary
			return "dictionary_edit";
		} else {
			List<DataDictionary> optionalItem = dic.findId(uuid);
			if (!optionalItem.isEmpty()) {
				DataDictionary existingItem = optionalItem.get(0);
				existingItem.setEvent(item.getEvent());
				existingItem.setQuestion(item.getQuestion());
				existingItem.setOptions(item.getOptions());
				dic.save(existingItem);
				return "redirect:/utility/dictionary?ev=" + ev;
			} else {
				return "error";
			}
		}
	}


}

package org.arn.hdsscapture.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.arn.hdsscapture.entity.Fieldworker;
import org.arn.hdsscapture.entity.Round;
import org.arn.hdsscapture.entity.Settings;
import org.arn.hdsscapture.entity.Task;
import org.arn.hdsscapture.repository.FieldworkerRepository;
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

	@GetMapping("/fw")
	public String fw(Model model
			) {
		List<Fieldworker> items = repo.findFieldworker();

		//System.err.println("Size of list " + items.size());
		
		model.addAttribute("items", items);
		

		return "utility/fw_list";
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
			@RequestParam(name = "success", required=false) String success
			) {
		Round round = new Round();
		model.addAttribute("round", round);
		if(success != null) {
			model.addAttribute("success", "Successfully saved. Round: " + success);
		}
		return "utility/round_add";

	}

	@PostMapping("/round")
	public String saveRound(@ModelAttribute("rounds") Round round) {
		// Generate a UUID for the Fieldworker's ID
		String uuid = UUID.randomUUID().toString();
		String uuidString = uuid.toString().replaceAll("-", "");
		// Set the ID of the Fieldworker object
		round.setUuid(uuidString);
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
	
	@GetMapping("/settings")
	public String findSettings(Model model) {
		List<Settings> settings = settingsrepo.findAll();
		model.addAttribute("settings", settings);
		return "utility/settings_list";
	}
	
	@GetMapping("/settings/edit/{id}")
	public String editSettings(@PathVariable("id") Integer id, Model model) {
		List<Settings> optionalSettings = settingsrepo.findBy(id);
		if (!optionalSettings.isEmpty()) {
			Settings settings = optionalSettings.get(0);
			// Add any other necessary data to the model attribute for editing
			model.addAttribute("settings", settings);
			return "utility/settings_edit";
		} else {
			return "error";
		}
	}
	
	@PostMapping("/settings/{id}")
	public String updateSettings(@PathVariable("id") Integer id, @ModelAttribute("settings") Settings settings,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			// Handle validation errors if necessary
			return "settings_edit";
		} else {
			List<Settings> optionalSettings = settingsrepo.findBy(id);
			if (!optionalSettings.isEmpty()) {
				Settings existingSettings = optionalSettings.get(0);
				existingSettings.setHoh_age(settings.getHoh_age());
				existingSettings.setMother_age(settings.getMother_age());
				existingSettings.setFather_age(settings.getFather_age());
				existingSettings.setRel_age(settings.getRel_age());
				existingSettings.setEarliestDate(settings.getEarliestDate());
				settingsrepo.save(existingSettings);
				return "redirect:/utility/settings";
			} else {
				return "error";
			}
		}
	}
	
	
	
}

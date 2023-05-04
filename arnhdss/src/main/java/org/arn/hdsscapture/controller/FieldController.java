package org.arn.hdsscapture.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.arn.hdsscapture.entity.Fieldworker;
import org.arn.hdsscapture.entity.Round;
import org.arn.hdsscapture.repository.FieldworkerRepository;
import org.arn.hdsscapture.repository.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class FieldController {
	
	
	@Autowired
	FieldworkerRepository repo;

	//Field Workers
	@GetMapping("/fieldworkers")
	public String findFieldworker(Model model) {
	    List<Fieldworker> fieldworkers = repo.findFieldworker();
	    model.addAttribute("fieldworkers", fieldworkers);
	    return "fieldworkers";
	}
	
	@GetMapping("/fieldworkers/add")
	public String addFieldworker(Model model) {
		Fieldworker fieldworker = new Fieldworker();
		model.addAttribute("fieldworker", fieldworker);
		return "add_fieldworker";
		
	}
	
	@PostMapping("/fieldworkers")
	public String saveFieldworker(@ModelAttribute("fieldworker") Fieldworker fieldworker) {
		// Generate a UUID for the Fieldworker's ID
	    String uuid = UUID.randomUUID().toString();
	    String uuidString = uuid.toString().replaceAll("-", "");
	    // Set the ID of the Fieldworker object
	    fieldworker.setFw_uuid(uuidString);
	    if (fieldworker.insertDate==null) {
	    fieldworker.insertDate =new Date();
	    }
	    
	 // Generate username based on initials and incrementing sequence number
	    String initials = (String.valueOf(fieldworker.getFirstName().charAt(0)) + String.valueOf(fieldworker.getLastName().charAt(0))).toUpperCase();
	    int sequenceNumber = 1;
	    String username = initials + sequenceNumber;
	    Fieldworker existingFieldworker = repo.findByUsername(username);
	    
	    // Check if username already exists and increment sequence number if it does
	    while(existingFieldworker != null) {
	        sequenceNumber++;
	        username = initials + sequenceNumber;
	        existingFieldworker = repo.findByUsername(username);
	    }
	    
	    // Set the username of the Fieldworker object
	    fieldworker.setUsername(username);
	    
	    // Save the fieldworker to the database
	    repo.save(fieldworker);
	    return "redirect:/fieldworkers";
	}
	
	@GetMapping("/fieldworkers/edit/{username}")
	public String editFieldworker(@PathVariable("username") String username, Model model) {
	    List<Fieldworker> optionalFieldworker = repo.findFieldworkerByUsername(username);
	    if (!optionalFieldworker.isEmpty()) {
	        Fieldworker fieldworker = optionalFieldworker.get(0);
	        // Add any other necessary data to the model attribute for editing
	        model.addAttribute("fieldworker", fieldworker);
	        return "edit_fieldworker";
	    } else {
	        // Handle case where Fieldworker object is not found
	        return "error";
	    }
	}

	
	@PostMapping("/fieldworkers/{username}")
	public String updateFieldworker(@PathVariable("username") String username, @ModelAttribute("fieldworker") Fieldworker fieldworker, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        // Handle validation errors if necessary
	        return "edit_fieldworker";
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
	            return "redirect:/fieldworkers";
	        } else {
	            // Handle case where Fieldworker object is not found
	            return "error";
	        }
	    }
	}


	@GetMapping("/fieldworkers/{username}")
	public String deleteFieldworker(@PathVariable("username") String username) {
	    repo.findFieldworkerByUsername(username);
	    return "redirect:/fieldworkers";
	}

	
	@Autowired
	RoundRepository roundrepo;

	//Round 
	@GetMapping("/rounds")
	public String findRound(Model model) {
	    List<Round> rounds = roundrepo.findRound();
	    model.addAttribute("rounds", rounds);
	    return "rounds";
	}
	
	@GetMapping("/rounds/add")
	public String addRound(Model model) {
		Round round = new Round();
		model.addAttribute("round", round);
		return "add_round";
		
	}
	
	@PostMapping("/rounds")
	public String saveRound(@ModelAttribute("rounds") Round round) {
		// Generate a UUID for the Fieldworker's ID
	    String uuid = UUID.randomUUID().toString();
	    String uuidString = uuid.toString().replaceAll("-", "");
	    // Set the ID of the Fieldworker object
	    round.setUuid(uuidString);
		roundrepo.save(round);
	    return "redirect:/rounds";
	}
	
	@GetMapping("/rounds/edit/{roundNumber}")
	public String editRound(@PathVariable("roundNumber") Integer roundNumber, Model model) {
	    List<Round> optionalROund = roundrepo.findFieldworkerByroundNumber(roundNumber);
	    if (!optionalROund.isEmpty()) {
	    	Round round = optionalROund.get(0);
	        // Add any other necessary data to the model attribute for editing
	        model.addAttribute("round", round);
	        return "edit_round";
	    } else {
	        return "error";
	    }
	}

	
	@PostMapping("/rounds/{roundNumber}")
	public String updateRound(@PathVariable("roundNumber") Integer roundNumber, @ModelAttribute("round") Round round, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        // Handle validation errors if necessary
	        return "edit_round";
	    } else {
	        List<Round> optionalRound = roundrepo.findFieldworkerByroundNumber(roundNumber);
	        if (!optionalRound.isEmpty()) {
	        	Round existingRound = optionalRound.get(0);
	        	existingRound.setRoundNumber(round.getRoundNumber());
	        	existingRound.setRemark(round.getRemark());
	        	existingRound.setStartDate(round.getStartDate());
	        	existingRound.setEndDate(round.getEndDate());
	        	roundrepo.save(existingRound);
	            return "redirect:/rounds";
	        } else {
	            return "error";
	        }
	    }
	}
	

}

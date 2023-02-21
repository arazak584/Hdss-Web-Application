package com.arn.hdss.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.arn.hdss.entity.Fieldworker;
import com.arn.hdss.repository.FieldworkerRepository;

@Controller
public class FieldController {
	
	
	@Autowired
	FieldworkerRepository repo;

	
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
	    // Save the fieldworker to the database
	    repo.save(fieldworker);
	    return "redirect:/fieldworkers";
	}
	
	@GetMapping("/fieldworkers/edit/{id}")
	public String editFieldworker(@PathVariable("id") Long id, Model model) {
	    Optional<Fieldworker> optionalFieldworker = repo.findById(id);
	    if (optionalFieldworker.isPresent()) {
	        Fieldworker fieldworker = optionalFieldworker.get();
	        // Add any other necessary data to the model attribute for editing
	        model.addAttribute("fieldworker", fieldworker);
	        return "edit_fieldworker";
	    } else {
	        // Handle case where Fieldworker object is not found
	        return "error";
	    }
	}

	
	@PostMapping("/fieldworkers/{id}")
	public String updateFieldworker(@PathVariable("id") Long id, @ModelAttribute("fieldworker") Fieldworker fieldworker, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        // Handle validation errors if necessary
	        return "edit_fieldworker";
	    } else {
	        Optional<Fieldworker> optionalFieldworker = repo.findById(id);
	        if (optionalFieldworker.isPresent()) {
	            Fieldworker existingFieldworker = optionalFieldworker.get();
	            existingFieldworker.setUsername(fieldworker.getUsername());
	            existingFieldworker.setFullName(fieldworker.getFullName());
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


	@GetMapping("/fieldworkers/{id}")
	public String deleteFieldworker(@PathVariable("id") Long id) {
	    repo.deleteById(id);
	    return "redirect:/fieldworkers";
	}



}

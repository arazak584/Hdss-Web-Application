package org.arn.hdsscapture.controller;

import java.util.List;

import org.arn.hdsscapture.entity.Round;
import org.arn.hdsscapture.repository.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class HomeController {

	@GetMapping("/hdss/map")
    public String map() {
        return "map";
    }
	
	@Autowired
	RoundRepository round;
	
	@GetMapping("")
	public String fw(Model model) {
			
	        List<Round> items = round.findAll();
	        model.addAttribute("items", items);
	        
	return "report/dashboard";
	        
	}
	
}

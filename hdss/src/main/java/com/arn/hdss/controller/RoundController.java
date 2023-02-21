package com.arn.hdss.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arn.hdss.entity.Round;
import com.arn.hdss.repository.RoundRepository;


@Controller
@RestController
@RequestMapping("")
public class RoundController {
	
	@Autowired
	RoundRepository repo;
	
	@GetMapping("/api/round")
	public DataWrapper<Round> findAll() {

		List<Round> data = repo.findAll();

		DataWrapper<Round> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@PostMapping("/api/round")
	public DataWrapper<Round> saveAll(@RequestBody DataWrapper<Round> data) {

		List<Round> saved =  repo.saveAll(data.getData());

		DataWrapper<Round> s = new DataWrapper<>();
		s.setData(saved);

		return s;
	}
	
	@PostMapping("/api/round/save")
	public Round save(@RequestBody Round Round) {
		return repo.save(Round);
	}


	@PostMapping("/api/round/{id}")
	public Round replace(@RequestBody Round newRound, @PathVariable("id") String id) {

		return repo.save(newRound);
	}
	
	@DeleteMapping("/api/round/{id}")
	void delete(@PathVariable("id") Long id) {
		repo.deleteById(id);
	}
	
	
	
	@RequestMapping("/round/roundList")
	public String roundList(Model model) {
		
		
		model.addAttribute("roundList", repo.findAll());
	
		return "roundList";

	}
	
	@GetMapping("/round/roundList")
	public String findAll(Model model) {
	    List<Round> roundList = repo.findAll();
	    model.addAttribute("roundList", roundList);
	    return "roundList";
	}


}

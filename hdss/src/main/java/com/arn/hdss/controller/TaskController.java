package com.arn.hdss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.arn.hdss.repository.IndividualRepository;

@Controller
public class TaskController {
	
	
	@Autowired
	IndividualRepository repo;

	
	@RequestMapping("/task")
	public String task(){
		return "task";
	}

}

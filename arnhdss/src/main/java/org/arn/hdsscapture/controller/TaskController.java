package org.arn.hdsscapture.controller;

import org.arn.hdsscapture.repository.IndividualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TaskController {
	
	
	@Autowired
	IndividualRepository repo;

	
	@RequestMapping("/task")
	public String task(){
		return "task";
	}
	
	@RequestMapping("/upload")
	public String upload(){
		return "upload";
	}
	

}

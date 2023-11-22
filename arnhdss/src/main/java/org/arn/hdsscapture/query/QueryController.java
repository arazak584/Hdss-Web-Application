package org.arn.hdsscapture.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hdss/query")
public class QueryController {
	
	@Autowired
	QueryRepository repo;
	
	@GetMapping("/minorhoh")
	public String activehoh(@RequestParam(name = "query", required = false)  String query,
	                 Model model) {	    
	    if (query != null) {
	        List<Queries> items = repo.Minor(query);
	        model.addAttribute("items", items);
	    } else {
	    }

	    return "query/minor";
	}
	
	@GetMapping("/nomembership")
	public String nomemb(@RequestParam(name = "query", required = false)  String query,
	                 Model model) {	    
	    if (query != null) {
	        List<Queries> items = repo.Nomem(query);
	        model.addAttribute("items", items);
	    } else {
	    }

	    return "query/nomemb";
	}
	
	@GetMapping("/dob")
	public String dob(@RequestParam(name = "query", required = false)  String query,
	                 Model model) {	    
	    if (query != null) {
	        List<Queries> items = repo.Dob(query);
	        model.addAttribute("items", items);
	    } else {
	    }

	    return "query/dob";
	}
	
	@GetMapping("/residency")
	public String dup(@RequestParam(name = "query", required = false)  String query,
	                 Model model) {	    
	    if (query != null) {
	        List<Queries> items = repo.Dup(query);
	        model.addAttribute("items", items);
	    } else {
	    }

	    return "query/dupres";
	}
	
	@GetMapping("/lagging")
	public String lag(@RequestParam(name = "query", required = false)  String query,
	                 Model model) {	    
	    if (query != null) {
	        List<Queries> items = repo.Lag(query);
	        model.addAttribute("items", items);
	    } else {
	    }

	    return "query/lag";
	}
	
	@GetMapping("/minors")
	public String minors(@RequestParam(name = "query", required = false)  String query,
	                 Model model) {	    
	    if (query != null) {
	        List<Queries> items = repo.Minors(query);
	        model.addAttribute("items", items);
	    } else {
	    }

	    return "query/minors";
	}

}

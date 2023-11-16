package org.arn.hdsscapture.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.arn.hdsscapture.entity.Fieldworker;
import org.arn.hdsscapture.entity.Round;
import org.arn.hdsscapture.repository.FieldworkerRepository;
import org.arn.hdsscapture.repository.ReportRepository;
import org.arn.hdsscapture.repository.RoundRepository;
import org.arn.hdsscapture.views.ListVisitRepository;
import org.arn.hdsscapture.views.ViewListing;
import org.arn.hdsscapture.views.ViewLocRepository;
import org.arn.hdsscapture.views.ViewLocation;
import org.arn.hdsscapture.views.ViewVisit;
import org.arn.hdsscapture.views.ViewVisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hdss")
public class ReportController {


	@Autowired
	ReportRepository repo;
	
	@GetMapping("/asyncReport")
    public ResponseEntity<Map<String, Object>> getAsyncReport() {
        Map<String, Object> data = new HashMap<>();
        data.put("items", visit.findAll());
        //Residency
        data.put("countRes", repo.countResidency());
        data.put("perres", repo.perRES());
        // Individual
        data.put("ind", repo.countInd());
        data.put("perind", repo.perInd());
        //Socialgroup
        data.put("counthh", repo.countSocialgroup());
        data.put("perhh", repo.perHH());
        //Compounds
        data.put("countcomp", repo.countCompound());
        data.put("percomp", repo.perComp());
        //HH VISIT
        data.put("hh", repo.countSocialgroup());
        data.put("hhvisit", repo.hhVisit());
        data.put("visit", repo.countVisit());
        //Comp Visited
        data.put("comp", repo.countCompound());
        data.put("compvisit", repo.countList());
        data.put("compper", repo.perComp());
        data.put("comploc", repo.perLoc());

        data.put("pregs", repo.countPreg());
        data.put("death", repo.countDth());
        data.put("rel", repo.countRel());
        data.put("sess", repo.countSes());
        data.put("vacc", repo.countVac());
        data.put("newhh", repo.countHH());
        data.put("newperson", repo.countPerson());
        data.put("newloc", repo.countLoc());


        return ResponseEntity.ok(data);
    }

	@GetMapping("/report")
	public String fw(Model model) {
		//Visit
		long visit = repo.countVisit();
		long listing = repo.countList();
		//Inmigration
		long img = repo.countImg();
		long omg = repo.countOmg();
		long outcome = repo.countOutcome();
		long preg = repo.countPreg();
		long dth = repo.countDth();
		long ses = repo.countSes();
		long vac = repo.countVac();
		long ind = repo.countInd();
		double perind = repo.perInd();
		

		//visit
		model.addAttribute("visit", visit);
		model.addAttribute("listing", listing);
		//Inmigration //Outmigration //Outcome //Pregnancy
		model.addAttribute("img", img);
		model.addAttribute("omg", omg);
		model.addAttribute("outcome", outcome);
		model.addAttribute("preg", preg);
		model.addAttribute("dth", dth);
		model.addAttribute("ses", ses);
		model.addAttribute("vac", vac);
		model.addAttribute("ind", ind);
		model.addAttribute("perind", perind);
		

		return "report/dashboard";
	}
	
	
	@Autowired
	FieldworkerRepository repos;

	@GetMapping("/report/ind")
	public String fws(Model model) {
		List<Fieldworker> items = repos.findFieldworker();

		//System.err.println("Size of list " + items.size());
		
		model.addAttribute("items", items);
		

		return "report/round_ad";
	}
	
	@Autowired
	RoundRepository roundrepo;
	
	
	@GetMapping("/report/locations")
	public String fround(@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
	                 @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
	                 Model model) {

//	    List<Round> items = roundrepo.findAll();
	    
	    if (startDate != null && endDate != null) {
	        List<Round> rounds = roundrepo.findRoundsBetweenDates(startDate, endDate);
	        model.addAttribute("rounds", rounds);
	    } else {
//	        List<Round> rounds = roundrepo.findAll(); // Or whatever method you use to fetch all rounds
//	        model.addAttribute("rounds", rounds);
	    }

	    return "report/round";
	}
	
	@Autowired
	ViewLocRepository loc;
	
	@GetMapping("/report/location")
	public String location(@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
	                 @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
	                 Model model) {

	    //List<ViewLocation> items = repo.locReport();
	    
	    if (startDate != null && endDate != null) {
	        List<ViewLocation> items = loc.locReport(startDate, endDate);
	        model.addAttribute("items", items);
	    } else {
	    }

	    return "report/location";
	}
	
	@Autowired
	ViewVisitRepository visit;
	
	@GetMapping("/report/visit")
	public String visit(@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
	                 @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
	                 Model model) {

	    
	    if (startDate != null && endDate != null) {
	        List<ViewVisit> items = visit.visitReport(startDate, endDate);
	        model.addAttribute("items", items);
	    } else {
	    }

	    return "report/visit";
	}
	
	@Autowired
	ListVisitRepository list;
	
	@GetMapping("/report/listing")
	public String listing(@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
	                 @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
	                 Model model) {

	    
	    if (startDate != null && endDate != null) {
	        List<ViewListing> items = list.Report(startDate, endDate);
	        model.addAttribute("items", items);
	    } else {
	    }

	    return "report/list";
	}


	
}


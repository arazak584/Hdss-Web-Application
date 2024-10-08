package org.arn.hdsscapture.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.arn.hdsscapture.entity.ErrorLog;
import org.arn.hdsscapture.entity.Fieldworker;
import org.arn.hdsscapture.entity.Population;
import org.arn.hdsscapture.entity.RegisterBook;
import org.arn.hdsscapture.entity.Round;
import org.arn.hdsscapture.query.Queries;
import org.arn.hdsscapture.query.QueryRepository;
import org.arn.hdsscapture.repository.CodebookRepository;
import org.arn.hdsscapture.repository.ErrorLogRepository;
import org.arn.hdsscapture.repository.FieldworkerRepository;
import org.arn.hdsscapture.repository.LocationhierarchyRepository;
import org.arn.hdsscapture.repository.PopulationRepository;
import org.arn.hdsscapture.repository.RegisterRepository;
import org.arn.hdsscapture.repository.ReportRepository;
import org.arn.hdsscapture.repository.RoundRepository;
import org.arn.hdsscapture.views.ActiveHouseholdRepository;
import org.arn.hdsscapture.views.ActiveHouseholds;
import org.arn.hdsscapture.views.FieldReport;
import org.arn.hdsscapture.views.FieldRepository;
import org.arn.hdsscapture.views.IndividualSearch;
import org.arn.hdsscapture.views.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/hdss")
public class ReportController {


	@Autowired
	ReportRepository repo;
	
	@Autowired
	FieldRepository rep;
	
	@Autowired
	FieldworkerRepository field;
	
	@Autowired
	QueryRepository queryrepo;
	@Autowired
	LocationhierarchyRepository loc;
	@Autowired
	RoundRepository round;
	@Autowired
	CodebookRepository codebook;
	@Autowired
	RegisterRepository registry;
	
	@GetMapping("/asyncReport")
    public ResponseEntity<Map<String, Object>> getAsyncReport() {
        Map<String, Object> data = new HashMap<>();
        data.put("items", codebook.findAll());
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
        data.put("percomp", repo.perLoc());
        


        return ResponseEntity.ok(data);
    }
	
	@GetMapping("/asyncReport/query")
    public ResponseEntity<Map<String, Object>> getAsyncQuery() {
        Map<String, Object> data = new HashMap<>();
        data.put("items", codebook.findAll());
        //Query
        data.put("nomemb", repo.noMem());
        data.put("minor", repo.Minor());
        data.put("dupres", repo.Dupres());
        data.put("dobs", repo.Dobs());
        data.put("lag", repo.Lag());
        data.put("minors", repo.Minors());
        data.put("out", repo.Outcome());
        data.put("dthoh", repo.dthhoh());

        return ResponseEntity.ok(data);
    }
	
	@GetMapping("/asyncReport/hierarchy")
    public ResponseEntity<Map<String, Object>> getAsyncHierarchy() {
        Map<String, Object> data = new HashMap<>();
        data.put("items", codebook.findAll());
        //Query
        data.put("cnt1", loc.cnt1());
        data.put("cnt2", loc.cnt2());
        data.put("cnt3", loc.cnt3());
        data.put("cnt4", loc.cnt4());
        data.put("cnt5", loc.cnt5());
        data.put("cnt6", loc.cnt6());

        return ResponseEntity.ok(data);
    }
	
	@GetMapping("/asyncReport/report")
    public ResponseEntity<Map<String, Object>> getAsyncReports() {
        Map<String, Object> data = new HashMap<>();
        data.put("items", codebook.findAll());
        //Report
      //HH VISIT
        data.put("hh", repo.countSocialgroup());
        data.put("hhvisit", repo.hhVisit());
        data.put("visit", repo.countVisit());
        //Comp Visited
        data.put("comp", repo.countCompounds());
        data.put("compvisit", repo.countList());
        data.put("compper", repo.perComp());
        data.put("comploc", repo.perLoc());
        
        data.put("comp1", repo.visit());
        data.put("comp2", repo.hhAct());
        data.put("comp3", repo.hhNot());

        data.put("pregs", repo.countPreg());
        data.put("death", repo.countDth());
        data.put("rel", repo.countRel());
        data.put("sess", repo.countSes());
        //data.put("eses", repo.countoldSes());
        data.put("vacc", repo.countVac());
        data.put("newhh", repo.countHH());
        data.put("newperson", repo.countPerson());
        data.put("newloc", repo.countLoc());
        
        data.put("img", repo.countImg());
        data.put("omg", repo.countOmg());
        data.put("outcome", repo.countOutcome());       
        data.put("morb", repo.countMor());

        return ResponseEntity.ok(data);
    }
	
	@GetMapping("/syncreport")
    public ResponseEntity<Map<String, Object>> getSyncReports() {
        Map<String, Object> data = new HashMap<>();
        data.put("items", codebook.findAll());
        //Report
        //Failure to sync for 2 plus days
        data.put("notify", repo.sync());

     // Get fieldworkers and format their details
        List<Fieldworker> fws = field.lastSync();
        List<String> formattedFieldworkers = new ArrayList<>();
        for (Fieldworker fw : fws) {
            String formattedString = String.format(
                "- %s %s, : %s",
                fw.getFirstName(),
                fw.getLastName(),
                fw.getInsertDate()
            );
            formattedFieldworkers.add(formattedString);
        }
        
        // Add formatted fieldworkers to the data map
        data.put("fieldworkers", formattedFieldworkers);

        return ResponseEntity.ok(data);
    }

	@GetMapping("/report")
	public String fw(Model model) {
			
	        List<Round> items = round.findAll();
	        model.addAttribute("items", items);
	       
	   
//		List<Population> items = pop.pyramid();
//		System.err.println("Size of list " + items.size());
//		
//		model.addAttribute("items", items);
		//Visit
//		long visit = repo.countVisit();
//		long listing = repo.countList();
//		//Inmigration
//
//		long preg = repo.countPreg();
//		long dth = repo.countDth();
//		long ses = repo.countSes();
//		long vac = repo.countVac();
//		long ind = repo.countInd();
//		double perind = repo.perInd();
//		
//
//		//visit
//		//model.addAttribute("visit", visit);
//		model.addAttribute("listing", listing);
//		//Inmigration //Outmigration //Outcome //Pregnancy
//		model.addAttribute("preg", preg);
//		model.addAttribute("dth", dth);
//		model.addAttribute("ses", ses);
//		model.addAttribute("vac", vac);
//		model.addAttribute("ind", ind);
//		model.addAttribute("perind", perind);
		

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

	
	@GetMapping("/report/location")
	public String location(@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
	                 @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
	                 Model model) {

	    //List<ViewLocation> items = repo.locReport();
	    
	    if (startDate != null && endDate != null) {
	        List<FieldReport> items = rep.Loc(startDate, endDate);
	        model.addAttribute("items", items);
	    } else {
	    }

	    return "report/location";
	}

	
	@GetMapping("/report/visit")
	public String visit(@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
	                 @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
	                 Model model) {

	    
	    if (startDate != null && endDate != null) {
	        List<FieldReport> items = rep.Vis(startDate, endDate);
	        model.addAttribute("items", items);
	    } else {
	    }

	    return "report/visit";
	}

	
	@GetMapping("/report/listing")
	public String listing(@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
	                 @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
	                 Model model) {

	    
	    if (startDate != null && endDate != null) {
	        List<FieldReport> items = rep.List(startDate, endDate);
	        model.addAttribute("items", items);
	    } else {
	    }

	    return "report/list";
	}

	
	@GetMapping("/report/inmigration")
	public String img(@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
	                 @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
	                 Model model) {

	    
	    if (startDate != null && endDate != null) {
	        List<FieldReport> items = rep.Img(startDate, endDate);
	        model.addAttribute("items", items);
	    } else {
	    }

	    return "report/inmigration";
	}


	
	@GetMapping("/report/outmigration")
	public String omg(@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
	                 @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
	                 Model model) {

	    
	    if (startDate != null && endDate != null) {
	        List<FieldReport> items = rep.Omg(startDate, endDate);
	        model.addAttribute("items", items);
	    } else {
	    }

	    return "report/outmigration";
	}
	

	
	@GetMapping("/report/death")
	public String dth(@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
	                 @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
	                 Model model) {

	    
	    if (startDate != null && endDate != null) {
	        List<FieldReport> items = rep.Dth(startDate, endDate);
	        model.addAttribute("items", items);
	    } else {
	    }

	    return "report/death";
	}
	
	
	
	@GetMapping("/report/pregnancy")
	public String preg(@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
	                 @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
	                 Model model) {

	    
	    if (startDate != null && endDate != null) {
	        List<FieldReport> items = rep.Preg(startDate, endDate);
	        model.addAttribute("items", items);
	    } else {
	    }

	    return "report/pregnancy";
	}

	
	@GetMapping("/report/outcome")
	public String outcome(@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
	                 @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
	                 Model model) {

	    
	    if (startDate != null && endDate != null) {
	        List<FieldReport> items = rep.Outcome(startDate, endDate);
	        model.addAttribute("items", items);
	    } else {
	    }

	    return "report/outcome";
	}

	
	@GetMapping("/report/ses")
	public String ses(@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
	                 @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
	                 Model model) {
		
		long ses = repo.countoldSes();
		model.addAttribute("ses", ses);
	    
	    if (startDate != null && endDate != null) {
	        List<FieldReport> items = rep.Ses(startDate, endDate);
	        model.addAttribute("items", items);
	    } else {
	    }

	    return "report/ses";
	}
	
	@GetMapping("/report/edit-ses")
	public String sesOld(@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
	                 @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
	                 Model model) {

	    
	    if (startDate != null && endDate != null) {
	        List<FieldReport> items = rep.SesEdit(startDate, endDate);
	        model.addAttribute("items", items);
	    } else {
	    }

	    return "report/edit_ses";
	}

	
	@GetMapping("/report/relationship")
	public String rel(@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
	                 @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
	                 Model model) {

	    
	    if (startDate != null && endDate != null) {
	        List<FieldReport> items = rep.Rel(startDate, endDate);
	        model.addAttribute("items", items);
	    } else {
	    }

	    return "report/rel";
	}

	
	@GetMapping("/report/individual")
	public String individual(@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
	                 @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
	                 Model model) {

	    
	    if (startDate != null && endDate != null) {
	        List<FieldReport> items = rep.Ind(startDate, endDate);
	        model.addAttribute("items", items);
	    } else {
	    }

	    return "report/individual";
	}

	
	@GetMapping("/report/vaccination")
	public String vac(@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
	                 @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
	                 Model model) {

	    
	    if (startDate != null && endDate != null) {
	        List<FieldReport> items = rep.Vac(startDate, endDate);
	        model.addAttribute("items", items);
	    } else {
	    }

	    return "report/vaccination";
	}
	
	@GetMapping("/report/morbidity")
	public String morbidity(@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
	                 @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
	                 Model model) {

	    
	    if (startDate != null && endDate != null) {
	        List<FieldReport> items = rep.Mor(startDate, endDate);
	        model.addAttribute("items", items);
	    } else {
	    }

	    return "report/morbidity";
	}
	
	@GetMapping("/report/registry")
	public String registry(@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
	                 @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
	                 Model model) {
	    
	    if (startDate != null && endDate != null) {
	        List<Queries> items = queryrepo.registry(startDate, endDate);
	        model.addAttribute("items", items);
	    } else {
	    }

	    return "report/registry";
	}
	
	@GetMapping("/report/regis")
	@ResponseBody
	public List<RegisterBook> getItemsByCompno(@RequestParam("compno") String compno) {
	    // Retrieve the list of items based on the selected compound Number
	    List<RegisterBook> regis = registry.Search(compno);
	    return regis;
	}

	
	@GetMapping("/report/household")
	public String hoh(@RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
	                 @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
	                 Model model) {

	    
	    if (startDate != null && endDate != null) {
	        List<FieldReport> items = rep.Hoh(startDate, endDate);
	        model.addAttribute("items", items);
	    } else {
	    }

	    return "report/household";
	}
	
	@Autowired
	ActiveHouseholdRepository activehoh;
	
	@GetMapping("/report/activehoh")
	public String activehoh(@RequestParam(name = "village", required = false)  String village,
	                 Model model) {

		List<String> villages = activehoh.villages();
		//System.out.println("Villages: " + villages);
		model.addAttribute("villages", villages);
	    
	    if (village != null) {
	        List<ActiveHouseholds> items = activehoh.Report(village);
	        model.addAttribute("items", items);
	    } else {
	    	List<ActiveHouseholds> items = activehoh.Reports();
	    	model.addAttribute("items", items);
	    }

	    return "report/activehoh";
	}
	
	@GetMapping("/report/unvisited")
	public String unvisited(@RequestParam(name = "village", required = false)  String village,
	                 Model model) {

		List<String> villages = activehoh.subvillages();
		//System.out.println("Villages: " + villages);
		model.addAttribute("villages", villages);
	    
	    if (village != null) {
	        List<ActiveHouseholds> items = activehoh.Unvisited(village);
	        model.addAttribute("items", items);
	        model.addAttribute("selected", village);
	    } else {
//	    	List<ActiveHouseholds> items = activehoh.Unvisit();
//	    	model.addAttribute("items", items);
	    }

	    return "report/unvisited";
	}
	
	@Autowired
	ErrorLogRepository errorrepo;
	
	@GetMapping("/errorlog")
	public String errorlog(@RequestParam(name = "error", required = false)  String error,
	                 Model model) {

		List<String> errors = errorrepo.errorentity();
		//System.out.println("forms: " + errors);
		model.addAttribute("errors", errors);
	    
	    if (error != null) {
	        List<ErrorLog> items = errorrepo.errorlog(error);
	        model.addAttribute("items", items);
	        model.addAttribute("selected", error);
	      //System.out.println("itemlist: " + items);
	    } else {
	    	//
	    }

	    return "report/errorlog";
	}
	
	@PostMapping("/truncate-error-log")
    public String truncateErrorLog(RedirectAttributes redirectAttributes) {
        // Truncate the error log table
        errorrepo.truncateErrorLog();
        
        // Add a success message to be shown on the next request
        redirectAttributes.addFlashAttribute("successMessage", "Error log table has been successfully truncated.");
        
        // Redirect back to the error log page
        return "redirect:/hdss/errorlog";
    }
	
	@Autowired
	SearchRepository searchs;
	
	@GetMapping("/report/search")
	public String search(@RequestParam(name = "search", required = false)  String search,
	                 Model model) {

	    
	    if (search != null) {
	        List<IndividualSearch> items = searchs.Report(search);
	        model.addAttribute("items", items);
	    } else {
//	    	List<ActiveHouseholds> items = activehoh.Reports();
//	    	model.addAttribute("items", items);
	    }
	    return "report/search";
	}
	
	@GetMapping("/api/search")
	@ResponseBody
	public List<IndividualSearch> searchByCompno(@RequestParam("compno") String compno) {
	    // Retrieve the list of items based on the selected compound Number
	    List<IndividualSearch> items = searchs.Report(compno);
	    return items;
	}
	
	@Autowired
	PopulationRepository poprepo;
	
	@GetMapping("/report/pop")
	public String pop(Model model) {

	        List<Population> items = poprepo.pyramid();
	        //System.err.println("Size of list " + items.size());
	        model.addAttribute("items", items);	 

	    return "report/pop";
	}
	
	@GetMapping("/report/workoutput")
	public String output(@RequestParam(name = "query", required = false)  String query,Model model) {
		
		if (query != null) {
	        List<Queries> items = queryrepo.Compvisit(query);
	        model.addAttribute("items", items);
		}else {
			
		}
	  
		

		return "report/workoutput";
	}

	
}


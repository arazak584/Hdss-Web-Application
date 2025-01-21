package org.arn.hdsscapture.formapproval;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.arn.hdsscapture.entity.Death;
import org.arn.hdsscapture.entity.Demographic;
import org.arn.hdsscapture.entity.Fieldworker;
import org.arn.hdsscapture.entity.Inmigration;
import org.arn.hdsscapture.entity.Morbidity;
import org.arn.hdsscapture.entity.Outmigration;
import org.arn.hdsscapture.entity.Pregnancyobservation;
import org.arn.hdsscapture.entity.Pregnancyoutcome;
import org.arn.hdsscapture.entity.Relationship;
import org.arn.hdsscapture.entity.Sociodemographic;
import org.arn.hdsscapture.entity.Vaccination;
import org.arn.hdsscapture.repository.CodebookRepository;
import org.arn.hdsscapture.repository.DeathRepository;
import org.arn.hdsscapture.repository.DemographicRepository;
import org.arn.hdsscapture.repository.FieldworkerRepository;
import org.arn.hdsscapture.repository.InmigrationRepository;
import org.arn.hdsscapture.repository.MorbidityRepository;
import org.arn.hdsscapture.repository.OutmigrationRepository;
import org.arn.hdsscapture.repository.PregnancyobservationRepository;
import org.arn.hdsscapture.repository.PregnancyoutcomeRepository;
import org.arn.hdsscapture.repository.RelationshipRepository;
import org.arn.hdsscapture.repository.SesRepository;
import org.arn.hdsscapture.repository.VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/hdss")
public class FormController {


	@Autowired
	CodebookRepository repo;
	@Autowired
	FormRepository frepo;
	@Autowired
	FieldworkerRepository field;
	
	@GetMapping("/async")
    public ResponseEntity<Map<String, Object>> getAsync() {
        Map<String, Object> data = new HashMap<>();
        data.put("items", field.findAll());
        //Inmigration
        data.put("img0", frepo.Img0());
        data.put("img1", frepo.Img1());
        data.put("img2", frepo.Img2());
        data.put("img3", frepo.Img3());
        // Outmigration
        data.put("omg0", frepo.Omg0());
        data.put("omg1", frepo.Omg1());
        data.put("omg2", frepo.Omg2());
        data.put("omg3", frepo.Omg3());
        // Death
        data.put("dth0", frepo.Dth0());
        data.put("dth1", frepo.Dth1());
        data.put("dth2", frepo.Dth2());
        data.put("dth3", frepo.Dth3());
        
        // Pregnancy
        data.put("preg0", frepo.Preg0());
        data.put("preg1", frepo.Preg1());
        data.put("preg2", frepo.Preg2());
        data.put("preg3", frepo.Preg3());  
        
        // Outcome
        data.put("out0", frepo.Out0());
        data.put("out1", frepo.Out1());
        data.put("out2", frepo.Out2());
        data.put("out3", frepo.Out3()); 
        
        // Demographic
        data.put("demo0", frepo.Demo0());
        data.put("demo1", frepo.Demo1());
        data.put("demo2", frepo.Demo2());
        data.put("demo3", frepo.Demo3()); 
        
        // Relationship
        data.put("rel0", frepo.Rel0());
        data.put("rel1", frepo.Rel1());
        data.put("rel2", frepo.Rel2());
        data.put("rel3", frepo.Rel3()); 
        
        // Vaccination
        data.put("vac0", frepo.Vac0());
        data.put("vac1", frepo.Vac1());
        data.put("vac2", frepo.Vac2());
        data.put("vac3", frepo.Vac3()); 
        
        // Ses
        data.put("ses0", frepo.Ses0());
        data.put("ses1", frepo.Ses1());
        data.put("ses2", frepo.Ses2());
        data.put("ses3", frepo.Ses3()); 
        
     // Morbidity
        data.put("mor0", frepo.Mor0());
        data.put("mor1", frepo.Mor1());
        data.put("mor2", frepo.Mor2());
        data.put("mor3", frepo.Mor3()); 


        return ResponseEntity.ok(data);
    }

	@GetMapping("/approval")
	public String main(Model model) {

		return "approvals/approval";
	}
	
	@Autowired
	ApprovalRepository aprepo;
	
	@GetMapping("/approved")
	@ResponseBody
	public List<approvals> getItems() {
	    // Retrieve the list of items based on the selected compound Number
	    List<approvals> regis = aprepo.approval();
	    return regis;
	}

	@Autowired
	InmigrationRepository imgrepo;

	@GetMapping("/approval/imglist")
	public String search(@RequestParam(name = "fw", required = false)  String fw,Model model) {
		
		List<Fieldworker> fws = field.fw();
		//System.out.println("Villages: " + villages);
		model.addAttribute("fws", fws);
    	
    	
		if (fw != null && !fw.equals("Select User")) {
			List<Inmigration> items = imgrepo.findImg(fw);
	        model.addAttribute("items", items);
	        model.addAttribute("selectedFw", fw);
		}else if ("Select User".equals(fw))  {
			List<Inmigration> items = imgrepo.findImgs();
	        model.addAttribute("items", items);
			model.addAttribute("selectedFw", "Select User");
		}else {
			List<Inmigration> items = imgrepo.findImgs();
	        model.addAttribute("items", items);
			model.addAttribute("selectedFw", "Select User");
		}


	    return "approvals/img_list";
	}
	
	@GetMapping("/reject/img")
	public String imgReject(Model model) {

			List<Inmigration> items = imgrepo.findRej();
	        model.addAttribute("items", items);

	    return "approvals/img_reject";
	}


	@GetMapping("/approval/edit/{uuid}")
    public String editImg(@PathVariable("uuid") String uuid,@RequestParam(name = "fw", required = false) String fw, Model model, Principal principal) {
        List<Inmigration> optionalImg = imgrepo.findByUuid(uuid);

        if (!optionalImg.isEmpty()) {
        	Inmigration item = optionalImg.get(0);

            // Supervisor
            String userName = principal.getName();
            String groupRole = getGroupRole();
            
            if (item.getSupervisor() == null || item.getSupervisor().isEmpty()) {
                item.setSupervisor(userName);
            }else {
            	item.getSupervisor();
            }
            
            //System.out.println(groupRole);

            model.addAttribute("item", item);
            model.addAttribute("fw", fw);
            model.addAttribute("groupRole", groupRole);
            // Populate model with codebook data
            populateModelWithCodebookData(model);

            return "approvals/img";
        } else {
            return "error";
        }
    }

    private void populateModelWithCodebookData(Model model) {
        model.addAttribute("origin", repo.origin());
        model.addAttribute("migtype", repo.migtype());
        model.addAttribute("reason", repo.reason());
        model.addAttribute("occupation", repo.occupation());
        model.addAttribute("farm", repo.farm());
        model.addAttribute("livestock", repo.livestock());
        model.addAttribute("food", repo.food());
        model.addAttribute("yn", repo.yn());
        model.addAttribute("cashcrops", repo.cashcrops());
    }

    @PostMapping("/approval/{uuids}")
    public String updateImg(@PathVariable("uuids") String uuids,@RequestParam(name = "fw", required = false) String fw, @ModelAttribute("item") Inmigration item,
            BindingResult result, Model model, Principal principal) {
        if (result.hasErrors()) {
        	model.addAttribute("fw", fw);
            // Handle validation errors if necessary
            return "approvals/img";
        } else {
            List<Inmigration> optionalImg = imgrepo.findByUuids(uuids);
            if (!optionalImg.isEmpty()) {
            	String userName = principal.getName();
            	String groupRole = getGroupRole();
            	
                Inmigration existingImg = optionalImg.get(0);
                existingImg.setApproveDate(new Date()); // Set the approveDate using a method
                //existingImg.setStatus(item.getStatus());
                existingImg.setSupervisor(userName);
                if ("ROLE_SUPERVISOR".equals(groupRole) && item.getStatus() == 1) {
                    existingImg.setStatus(4);
                } else {
                    existingImg.setStatus(item.getStatus());
                }               
                // Ensure comment is null if status is not 2
                if (item.getStatus() != 2) {
                    existingImg.setComment(null);
                } else {
                    existingImg.setComment(item.getComment());
                }
                
                imgrepo.save(existingImg);
                return "redirect:/hdss/approval/imglist?fw=" + fw;
            } else {
                return "error"; // Handle the case when the Inmigration with the given UUID is not found
            }
        }
    }

    @Autowired
	OutmigrationRepository omgrepo;
    
    @GetMapping("/approval/omglist")
	public String omg(@RequestParam(name = "fw", required = false)  String fw,Model model) {
        
	        List<Fieldworker> fws = field.fw();
			//System.out.println("Villages: " + villages);
			model.addAttribute("fws", fws);
	    	
	    	
			if (fw != null && !fw.equals("Select User")) {
				List<Outmigration> items = omgrepo.findItem(fw);
		        model.addAttribute("items", items);
		        model.addAttribute("selectedFw", fw);
			}else if ("Select User".equals(fw)) {
				model.addAttribute("selectedFw", "Select User");
				List<Outmigration> items = omgrepo.findItems();
		        model.addAttribute("items", items);				
			}else {
				model.addAttribute("selectedFw", "Select User");
				List<Outmigration> items = omgrepo.findItems();
		        model.addAttribute("items", items);				
			}			
			


	    return "approvals/omg_list";
	}
    
    @GetMapping("/reject/omg")
	public String omgReject(Model model) {

			List<Outmigration> items = omgrepo.findRej();
	        model.addAttribute("items", items);

	    return "approvals/omg_reject";
	}


	@GetMapping("/approval/omg/{uuid}")
    public String editOmg(@PathVariable("uuid") String uuid,@RequestParam(name = "fw", required = false) String fw, Model model, Principal principal) {
        List<Outmigration> optionalImg = omgrepo.findByUuid(uuid);

        if (!optionalImg.isEmpty()) {
        	Outmigration item = optionalImg.get(0);

            // Supervisor
            String userName = principal.getName();

            if (item.getSupervisor() == null || item.getSupervisor().isEmpty()) {
                item.setSupervisor(userName);
            }
            
            System.out.println("Item status: " + item.getStatus());

            model.addAttribute("item", item);
            model.addAttribute("fw", fw);
            // Populate model with codebook data
            OmgCodebookData(model);

            return "approvals/omg";
        } else {
            return "error";
        }
    }

    private void OmgCodebookData(Model model) {
        model.addAttribute("destination", repo.destination());
        model.addAttribute("reason", repo.reasons());
    }

    @PostMapping("/approval/omg/{uuids}")
    public String updateOmg(@PathVariable("uuids") String uuids,@RequestParam(name = "fw", required = false) String fw, @ModelAttribute("item") Outmigration item,
            BindingResult result, Model model,Principal principal) {
        if (result.hasErrors()) {
        	model.addAttribute("fw", fw);
            // Handle validation errors if necessary
            return "approvals/omg";
        } else {
            List<Outmigration> optionalImg = omgrepo.findByUuids(uuids);
            if (!optionalImg.isEmpty()) {
            	Outmigration existingImg = optionalImg.get(0);
            	String userName = principal.getName();
            	String groupRole = getGroupRole();
            	
                existingImg.setApproveDate(new Date()); // Set the approveDate using a method
                //existingImg.setStatus(item.getStatus());
                existingImg.setSupervisor(userName);
                if ("ROLE_SUPERVISOR".equals(groupRole) && item.getStatus() == 1) {
                    existingImg.setStatus(4);
                } else {
                    existingImg.setStatus(item.getStatus());
                }               
                // Ensure comment is null if status is not 2
                if (item.getStatus() != 2) {
                    existingImg.setComment(null);
                } else {
                    existingImg.setComment(item.getComment());
                }
                omgrepo.save(existingImg);
                return "redirect:/hdss/approval/omglist?fw=" + fw;
            } else {
                return "error"; // Handle the case when the Inmigration with the given UUID is not found
            }
        }
    }
    
    
    @Autowired
	DeathRepository dthrepo;
    
    @GetMapping("/approval/dthlist")
	public String dth(@RequestParam(name = "fw", required = false)  String fw,Model model) {	        
	        
	        List<Fieldworker> fws = field.fw();
			//System.out.println("Villages: " + villages);
			model.addAttribute("fws", fws);
	    	
	    	
			if (fw != null && !fw.equals("Select User")) {
				List<Death> items = dthrepo.findItem(fw);
		        model.addAttribute("items", items);
		        model.addAttribute("selectedFw", fw);
			}else if ("Select User".equals(fw)) {
				List<Death> items = dthrepo.findItems();
		        model.addAttribute("items", items);
				model.addAttribute("selectedFw", "Select User");
			}else {
				List<Death> items = dthrepo.findItems();
		        model.addAttribute("items", items);
				model.addAttribute("selectedFw", "Select User");
			}

	    return "approvals/dth_list";
	}
    
    @GetMapping("/reject/death")
	public String dthReject(Model model) {

			List<Death> items = dthrepo.findRej();
	        model.addAttribute("items", items);

	    return "approvals/dth_reject";
	}


	@GetMapping("/approval/dth/{uuid}")
    public String editDth(@PathVariable("uuid") String uuid,@RequestParam(name = "fw", required = false) String fw, Model model, Principal principal) {
        List<Death> optionalImg = dthrepo.findByUuid(uuid);

        if (!optionalImg.isEmpty()) {
        	Death item = optionalImg.get(0);

            // Supervisor
            String userName = principal.getName();

            if (item.getSupervisor() == null || item.getSupervisor().isEmpty()) {
                item.setSupervisor(userName);
            }

            model.addAttribute("item", item);
            model.addAttribute("fw", fw);
            // Populate model with codebook data
            DthCodebookData(model);

            return "approvals/dth";
        } else {
            return "error";
        }
    }

    private void DthCodebookData(Model model) {
        model.addAttribute("deathCause", repo.deathCause());
        model.addAttribute("deathPlace", repo.deathPlace());
        model.addAttribute("yn", repo.yn());
    }

    @PostMapping("/approval/dth/{uuids}")
    public String updateDth(@PathVariable("uuids") String uuids,@RequestParam(name = "fw", required = false) String fw, @ModelAttribute("item") Death item,
            BindingResult result, Model model,Principal principal) {
        if (result.hasErrors()) {
        	model.addAttribute("fw", fw);
            // Handle validation errors if necessary
            return "approvals/dth";
        } else {
            List<Death> optionalImg = dthrepo.findByUuids(uuids);
            if (!optionalImg.isEmpty()) {
            	Death existingImg = optionalImg.get(0);
            	String userName = principal.getName();
            	String groupRole = getGroupRole();
            	
                existingImg.setApproveDate(new Date()); // Set the approveDate using a method
                //existingImg.setStatus(item.getStatus());
                existingImg.setSupervisor(userName);
                if ("ROLE_SUPERVISOR".equals(groupRole) && item.getStatus() == 1) {
                    existingImg.setStatus(4);
                } else {
                    existingImg.setStatus(item.getStatus());
                }               
                // Ensure comment is null if status is not 2
                if (item.getStatus() != 2) {
                    existingImg.setComment(null);
                } else {
                    existingImg.setComment(item.getComment());
                }
                dthrepo.save(existingImg);
                return "redirect:/hdss/approval/dthlist?fw=" + fw; // Corrected redirect URL
            } else {
                return "error"; // Handle the case when the Inmigration with the given UUID is not found
            }
        }
    }

    
    @Autowired
	PregnancyobservationRepository pregrepo;
    
    @GetMapping("/approval/preglist")
	public String preg(@RequestParam(name = "fw", required = false)  String fw,Model model) {
     
	        List<Fieldworker> fws = field.fw();
			//System.out.println("Villages: " + villages);
			model.addAttribute("fws", fws);
	    	
	    	
			if (fw != null && !fw.equals("Select User")) {
				 List<Pregnancyobservation> items = pregrepo.findItem(fw);
			     model.addAttribute("items", items);
		        model.addAttribute("selectedFw", fw);
			}else if ("Select User".equals(fw)) {
				model.addAttribute("selectedFw", "Select User");
				List<Pregnancyobservation> items = pregrepo.findItems();
			     model.addAttribute("items", items);
			}else {
				model.addAttribute("selectedFw", "Select User");
				List<Pregnancyobservation> items = pregrepo.findItems();
			     model.addAttribute("items", items);
			}


	    return "approvals/preg_list";
	}

    @GetMapping("/reject/pregnancy")
	public String pregReject(Model model) {

			List<Pregnancyobservation> items = pregrepo.findRej();
	        model.addAttribute("items", items);

	    return "approvals/preg_reject";
	}

	@GetMapping("/approval/preg/{uuid}")
    public String editPreg(@PathVariable("uuid") String uuid,@RequestParam(name = "fw", required = false) String fw, Model model, Principal principal) {
        List<Pregnancyobservation> optionalImg = pregrepo.findByUuid(uuid);

        if (!optionalImg.isEmpty()) {
        	Pregnancyobservation item = optionalImg.get(0);

            // Supervisor
            String userName = principal.getName();

            if (item.getSupervisor() == null || item.getSupervisor().isEmpty()) {
                item.setSupervisor(userName);
            }

            model.addAttribute("item", item);
            model.addAttribute("fw", fw);
            // Populate model with codebook data
            PregCodebookData(model);

            return "approvals/preg";
        } else {
            return "error";
        }
    }

    private void PregCodebookData(Model model) {
        model.addAttribute("yn", repo.yn());
        model.addAttribute("notdel", repo.notdel());
        model.addAttribute("bnetSou", repo.bnetSou());
        model.addAttribute("yn_anc", repo.yn_anc());
        model.addAttribute("assist", repo.assist());
        model.addAttribute("bnetLoc", repo.bnetLoc());
        model.addAttribute("plan_method", repo.fam_plan_method());
        
    }

    @PostMapping("/approval/preg/{uuids}")
    public String updatePreg(@PathVariable("uuids") String uuids,@RequestParam(name = "fw", required = false) String fw, @ModelAttribute("item") Pregnancyobservation item,
            BindingResult result, Model model,Principal principal) {
        if (result.hasErrors()) {
        	model.addAttribute("fw", fw);
            // Handle validation errors if necessary
            return "approvals/preg";
        } else {
            List<Pregnancyobservation> optionalImg = pregrepo.findByUuids(uuids);
            if (!optionalImg.isEmpty()) {
            	Pregnancyobservation existingImg = optionalImg.get(0);
            	String userName = principal.getName();
            	String groupRole = getGroupRole();
            	
                existingImg.setApproveDate(new Date()); // Set the approveDate using a method
                //existingImg.setStatus(item.getStatus());
                existingImg.setSupervisor(userName);
                if ("ROLE_SUPERVISOR".equals(groupRole) && item.getStatus() == 1) {
                    existingImg.setStatus(4);
                } else {
                    existingImg.setStatus(item.getStatus());
                }               
                // Ensure comment is null if status is not 2
                if (item.getStatus() != 2) {
                    existingImg.setComment(null);
                } else {
                    existingImg.setComment(item.getComment());
                }
                pregrepo.save(existingImg);
                return "redirect:/hdss/approval/preglist?fw=" + fw; // Corrected redirect URL
            } else {
                return "error"; // Handle the case when the Inmigration with the given UUID is not found
            }
        }
    }
    
    
    @Autowired
	PregnancyoutcomeRepository outrepo;
    
    @GetMapping("/approval/outcomelist")
	public String out(@RequestParam(name = "fw", required = false)  String fw,Model model) {

	        
	        List<Fieldworker> fws = field.fw();
			//System.out.println("Villages: " + villages);
			model.addAttribute("fws", fws);		
	    	
	    	
			if (fw != null && !fw.equals("Select User")) {
				List<Pregnancyoutcome> items = outrepo.findItem(fw);
		        model.addAttribute("items", items);
		        model.addAttribute("selectedFw", fw);
			}else if ("Select User".equals(fw)) {
				model.addAttribute("selectedFw", "Select User");
				List<Pregnancyoutcome> items = outrepo.findItems();
		        model.addAttribute("items", items);
	
			}else {
				model.addAttribute("selectedFw", "Select User");
				List<Pregnancyoutcome> items = outrepo.findItems();
		        model.addAttribute("items", items);
	
			}		


	    return "approvals/outcome_list";
	}

    @GetMapping("/reject/outcome")
	public String outcomeReject(Model model) {

			List<Pregnancyoutcome> items = outrepo.findRej();
	        model.addAttribute("items", items);

	    return "approvals/outcome_reject";
	}

	@GetMapping("/approval/outcome/{uuid}")
    public String editOut(@PathVariable("uuid") String uuid,@RequestParam(name = "fw", required = false) String fw, Model model, Principal principal) {
        List<Pregnancyoutcome> optionalItem = outrepo.findByUuid(uuid);

        if (!optionalItem.isEmpty()) {
        	Pregnancyoutcome item = optionalItem.get(0);

            // Supervisor
            String userName = principal.getName();

            if (item.getSupervisor() == null || item.getSupervisor().isEmpty()) {
                item.setSupervisor(userName);
            }

            model.addAttribute("item", item);
            model.addAttribute("fw", fw);
            // Populate model with codebook data
            OutCodebookData(model);

            return "approvals/outcome";
        } else {
            return "error";
        }
    }

    private void OutCodebookData(Model model) {
        model.addAttribute("yn", repo.yn());
        model.addAttribute("notdel", repo.notdel());
        model.addAttribute("outcometype", repo.outcometype());
        model.addAttribute("yn_anc", repo.yn_anc());
        model.addAttribute("assist", repo.assist());
        model.addAttribute("birthPlace", repo.birthPlace());
        model.addAttribute("howdel", repo.howdel());
        model.addAttribute("size", repo.size());
        model.addAttribute("how_lng", repo.how_lng());        
        model.addAttribute("feed_chd", repo.feed_chd());
        model.addAttribute("more_chd", repo.more_chd());
        model.addAttribute("preg_chd", repo.preg_chd());
        model.addAttribute("fam_plan_method", repo.fam_plan_method());       
        
        
    }

    @PostMapping("/approval/outcome/{uuids}")
    public String updateOut(@PathVariable("uuids") String uuids,@RequestParam(name = "fw", required = false) String fw, @ModelAttribute("item") Pregnancyoutcome item,
            BindingResult result, Model model,Principal principal) {
        if (result.hasErrors()) {
        	model.addAttribute("fw", fw);
            // Handle validation errors if necessary
            return "approvals/outcome";
        } else {
            List<Pregnancyoutcome> optionalImg = outrepo.findByUuids(uuids);
            if (!optionalImg.isEmpty()) {
            	Pregnancyoutcome existingImg = optionalImg.get(0);
            	String userName = principal.getName();
            	String groupRole = getGroupRole();
            	
                existingImg.setApproveDate(new Date()); // Set the approveDate using a method
                //existingImg.setStatus(item.getStatus());
                existingImg.setSupervisor(userName);
                if ("ROLE_SUPERVISOR".equals(groupRole) && item.getStatus() == 1) {
                    existingImg.setStatus(4);
                } else {
                    existingImg.setStatus(item.getStatus());
                }               
                // Ensure comment is null if status is not 2
                if (item.getStatus() != 2) {
                    existingImg.setComment(null);
                } else {
                    existingImg.setComment(item.getComment());
                }
                outrepo.save(existingImg);
                return "redirect:/hdss/approval/outcomelist?fw=" + fw; // Corrected redirect URL
            } else {
                return "error"; // Handle the case when the Inmigration with the given UUID is not found
            }
        }
    }
    
    //Open Pregnancy from outcome
    @GetMapping("/approval/pregout/{uuid}")
    public String editPregs(@PathVariable("uuid") String uuid,@RequestParam(name = "fw", required = false) String fw, Model model, Principal principal) {
        List<Pregnancyobservation> optionalImg = pregrepo.findUuid(uuid);

        if (!optionalImg.isEmpty()) {
        	Pregnancyobservation item = optionalImg.get(0);

            // Supervisor
            String userName = principal.getName();

            if (item.getSupervisor() == null || item.getSupervisor().isEmpty()) {
                item.setSupervisor(userName);
            }

            model.addAttribute("item", item);
            model.addAttribute("fw", fw);
            // Populate model with codebook data
            PregCodebookData(model);

            return "approvals/preg_out";
        } else {
            return "error";
        }
    }
    
    //Save Pregnancy and return to the Outcome
    @PostMapping("/approval/pregs/{uuids}")
    public String updatePregs(@PathVariable("uuids") String uuids,@RequestParam(name = "fw", required = false) String fw, @ModelAttribute("item") Pregnancyobservation item,
            BindingResult result, Model model,Principal principal) {
        if (result.hasErrors()) {
        	model.addAttribute("fw", fw);
            // Handle validation errors if necessary
            return "approvals/preg_out";
        } else {
            List<Pregnancyobservation> optionalImg = pregrepo.findByUuids(uuids);
            if (!optionalImg.isEmpty()) {
            	Pregnancyobservation existingImg = optionalImg.get(0);
            	String userName = principal.getName();
            	String groupRole = getGroupRole();
            	
                existingImg.setApproveDate(new Date()); // Set the approveDate using a method
                //existingImg.setStatus(item.getStatus());
                existingImg.setSupervisor(userName);
                if ("ROLE_SUPERVISOR".equals(groupRole) && item.getStatus() == 1) {
                    existingImg.setStatus(4);
                } else {
                    existingImg.setStatus(item.getStatus());
                }               
                // Ensure comment is null if status is not 2
                if (item.getStatus() != 2) {
                    existingImg.setComment(null);
                } else {
                    existingImg.setComment(item.getComment());
                }
                pregrepo.save(existingImg);
                return "redirect:/hdss/approval/outcomelist?fw=" + fw; // Corrected redirect URL
            } else {
                return "error"; // Handle the case when the Inmigration with the given UUID is not found
            }
        }
    }
    
    
    
    
    @Autowired
	DemographicRepository demorepo;
    
    @GetMapping("/approval/demolist")
	public String demo(@RequestParam(name = "fw", required = false)  String fw,Model model) {
    	
    	List<Fieldworker> fws = field.fw();
		//System.out.println("Villages: " + villages);
		model.addAttribute("fws", fws);
    	
    	
		if (fw != null && !fw.equals("Select User")) {
	        List<Demographic> items = demorepo.findItem(fw);
	        model.addAttribute("items", items);
	        model.addAttribute("selectedFw", fw);
		}else if ("Select User".equals(fw)) {
			List<Demographic> items = demorepo.findItems();
	        model.addAttribute("items", items);
			model.addAttribute("selectedFw", "Select User");
		}else {
			List<Demographic> items = demorepo.findItems();
	        model.addAttribute("items", items);
			model.addAttribute("selectedFw", "Select User");
		}


	    return "approvals/demo_list";
	}
    
    @GetMapping("/reject/demographic")
	public String demoReject(Model model) {

			List<Demographic> items = demorepo.findRej();
	        model.addAttribute("items", items);

	    return "approvals/demo_reject";
	}


	@GetMapping("/approval/demo/{uuid}")
    public String editDemo(@PathVariable("uuid") String uuid,@RequestParam(name = "fw", required = false) String fw, Model model, Principal principal) {
        List<Demographic> optionalImg = demorepo.findByUuid(uuid);

        if (!optionalImg.isEmpty()) {
        	Demographic item = optionalImg.get(0);

            // Supervisor
            String userName = principal.getName();

            if (item.getSupervisor() == null || item.getSupervisor().isEmpty()) {
                item.setSupervisor(userName);
            }

            model.addAttribute("item", item);
            model.addAttribute("fw", fw);
            // Populate model with codebook data
            DemoCodebookData(model);

            return "approvals/demo";
        } else {
            return "error";
        }
    }

    private void DemoCodebookData(Model model) {
        model.addAttribute("education", repo.education());
        model.addAttribute("marital", repo.marital());
        model.addAttribute("occupation", repo.occupation());
        model.addAttribute("religion", repo.religion());
        model.addAttribute("tribe", repo.tribe());
        model.addAttribute("akan", repo.akan());
        model.addAttribute("deno", repo.deno());
                
        
    }

    @PostMapping("/approval/demo/{uuids}")
    public String updateDemo(@PathVariable("uuids") String uuids,@RequestParam(name = "fw", required = false) String fw, @ModelAttribute("item") Demographic item,
            BindingResult result, Model model,Principal principal) {
        if (result.hasErrors()) {
        	model.addAttribute("fw", fw);
            // Handle validation errors if necessary
            return "approvals/demo";
        } else {
            List<Demographic> optionalImg = demorepo.findByUuids(uuids);
            if (!optionalImg.isEmpty()) {
            	Demographic existingImg = optionalImg.get(0);
            	String userName = principal.getName();
            	String groupRole = getGroupRole();
            	
                existingImg.setApproveDate(new Date()); // Set the approveDate using a method
                //existingImg.setStatus(item.getStatus());
                existingImg.setSupervisor(userName);
                if ("ROLE_SUPERVISOR".equals(groupRole) && item.getStatus() == 1) {
                    existingImg.setStatus(4);
                } else {
                    existingImg.setStatus(item.getStatus());
                }               
                // Ensure comment is null if status is not 2
                if (item.getStatus() != 2) {
                    existingImg.setComment(null);
                } else {
                    existingImg.setComment(item.getComment());
                }
                demorepo.save(existingImg);
                return "redirect:/hdss/approval/demolist?fw=" + fw; // Corrected redirect URL
            } else {
                return "error"; // Handle the case when the Inmigration with the given UUID is not found
            }
        }
    }
    
    
    
    @Autowired
  	RelationshipRepository relrepo;
      
    @GetMapping("/approval/rellist")
  	public String rel(@RequestParam(name = "fw", required = false)  String fw,Model model) {
  	        
  	      	List<Fieldworker> fws = field.fw();
			//System.out.println("Villages: " + villages);
			model.addAttribute("fws", fws);
	    	
	    	
			if (fw != null && !fw.equals("Select User")) {
				List<Relationship> items = relrepo.findItem(fw);
	  	        model.addAttribute("items", items);
		        model.addAttribute("selectedFw", fw);
			}else if ("Select User".equals(fw)) {
				List<Relationship> items = relrepo.findItems();
	  	        model.addAttribute("items", items);
				model.addAttribute("selectedFw", "Select User");
			}else {
				List<Relationship> items = relrepo.findItems();
	  	        model.addAttribute("items", items);
				model.addAttribute("selectedFw", "Select User");
			}			


  	    return "approvals/rel_list";
  	}

    @GetMapping("/reject/relationship")
	public String relReject(Model model) {

			List<Relationship> items = relrepo.findRej();
	        model.addAttribute("items", items);

	    return "approvals/rel_reject";
	}

  	@GetMapping("/approval/rel/{uuid}")
      public String editRel(@PathVariable("uuid") String uuid,@RequestParam(name = "fw", required = false) String fw, Model model, Principal principal) {
          List<Relationship> optionalImg = relrepo.findByUuid(uuid);

          if (!optionalImg.isEmpty()) {
        	  Relationship item = optionalImg.get(0);

              // Supervisor
              String userName = principal.getName();

              if (item.getSupervisor() == null || item.getSupervisor().isEmpty()) {
                  item.setSupervisor(userName);
              }

              model.addAttribute("item", item);
              model.addAttribute("fw", fw);
              // Populate model with codebook data
              RelCodebookData(model);

              return "approvals/rel";
          } else {
              return "error";
          }
      }

      private void RelCodebookData(Model model) {
          model.addAttribute("yn", repo.yn());
          model.addAttribute("start", repo.start());
          model.addAttribute("end", repo.end());
                 
          
      }

      @PostMapping("/approval/rel/{uuids}")
      public String updateRel(@PathVariable("uuids") String uuids,@RequestParam(name = "fw", required = false) String fw, @ModelAttribute("item") Relationship item,
              BindingResult result, Model model,Principal principal) {
          if (result.hasErrors()) {
        	  model.addAttribute("fw", fw);
              // Handle validation errors if necessary
              return "approvals/rel";
          } else {
              List<Relationship> optionalImg = relrepo.findByUuids(uuids);
              if (!optionalImg.isEmpty()) {
            	  Relationship existingImg = optionalImg.get(0);
            	  String userName = principal.getName();
              	String groupRole = getGroupRole();
              	
                  existingImg.setApproveDate(new Date()); // Set the approveDate using a method
                  //existingImg.setStatus(item.getStatus());
                  existingImg.setSupervisor(userName);
                  if ("ROLE_SUPERVISOR".equals(groupRole) && item.getStatus() == 1) {
                      existingImg.setStatus(4);
                  } else {
                      existingImg.setStatus(item.getStatus());
                  }               
                  // Ensure comment is null if status is not 2
                  if (item.getStatus() != 2) {
                      existingImg.setComment(null);
                  } else {
                      existingImg.setComment(item.getComment());
                  }
                  relrepo.save(existingImg);
                  return "redirect:/hdss/approval/rellist?fw=" + fw; // Corrected redirect URL
              } else {
                  return "error"; // Handle the case when the Inmigration with the given UUID is not found
              }
          }
      }

      
      @Autowired
      SesRepository sesrepo;
        
      @GetMapping("/approval/seslist")
    	public String ses(@RequestParam(name = "fw", required = false)  String fw,Model model) {
    	        
    	      	List<Fieldworker> fws = field.fw();
  			//System.out.println("Villages: " + villages);
  			model.addAttribute("fws", fws);
  	    	
  	    	
  			if (fw != null && !fw.equals("Select User")) {
  				List<Sociodemographic> items = sesrepo.findItem(fw);
  	  	        model.addAttribute("items", items);
  		        model.addAttribute("selectedFw", fw);
  		      //System.out.println("Print Items: " + items);
  			}else if ("Select User".equals(fw)) {
  			    // Handle the case where fw is explicitly "Select User"
  			    List<Sociodemographic> items = sesrepo.findItems();
  			    model.addAttribute("items", items);
  			    model.addAttribute("selectedFw", "Select User");
  			} else {
  			    // Handle the case where fw is null or any other value
  			    List<Sociodemographic> items = sesrepo.findItems();
  			    model.addAttribute("items", items);
  			    model.addAttribute("selectedFw", "Select User");
  			}


    	    return "approvals/ses_list";
    	}

    @GetMapping("/reject/ses")
  	public String sesReject(Model model) {

  			List<Sociodemographic> items = sesrepo.findRej();
  	        model.addAttribute("items", items);

  	    return "approvals/ses_reject";
  	}

    	@GetMapping("/approval/ses/{uuid}")
        public String editSes(@PathVariable("uuid") String uuid,@RequestParam(name = "fw", required = false) String fw, Model model, Principal principal) {
            List<Sociodemographic> optionalItem = sesrepo.findByUuid(uuid);

            if (!optionalItem.isEmpty()) {
            	Sociodemographic item = optionalItem.get(0);

                // Supervisor
                String userName = principal.getName();

                if (item.getSupervisor() == null || item.getSupervisor().isEmpty()) {
                    item.setSupervisor(userName);
                }

                model.addAttribute("item", item);
                model.addAttribute("fw", fw);
                // Populate model with codebook data
                SesCodebookData(model);

                return "approvals/ses";
            } else {
                return "error";
            }
        }

        private void SesCodebookData(Model model) {
            model.addAttribute("yn", repo.yn());
            model.addAttribute("h2o_fcorres", repo.h2o_fcorres());
            model.addAttribute("marital", repo.marital_sco());
            model.addAttribute("religion", repo.religion());
            model.addAttribute("tribe", repo.tribe());
            model.addAttribute("rltnhead", repo.rltnhead());
            model.addAttribute("toilet_fcorres", repo.toilet_fcorres());       
            model.addAttribute("toilet_loc_fcorres", repo.toilet_loc_fcorres());  
            model.addAttribute("pet_vac", repo.pet_vac());
            model.addAttribute("toilet_share_num_fcorres", repo.toilet_share_num_fcorres());
            model.addAttribute("ext_wall_fcorres", repo.ext_wall_fcorres());
            model.addAttribute("floor_fcorres", repo.floor_fcorres());
            model.addAttribute("roof_fcorres", repo.roof_fcorres());
            model.addAttribute("mobile_access_fcorres", repo.mobile_access_fcorres());
            model.addAttribute("own_rent_scorres", repo.own_rent_scorres());
            model.addAttribute("job", repo.job());
            model.addAttribute("stove_fcorres", repo.stove_fcorres());
            model.addAttribute("cooking_loc_fcorres", repo.cooking_loc_fcorres());
            model.addAttribute("frq", repo.frq());          
            model.addAttribute("nhis", repo.nhis());
            model.addAttribute("nhis_no", repo.nhis_no()); 
            model.addAttribute("crop", repo.crop()); 
            
        }

        @PostMapping("/approval/ses/{uuids}")
        public String updateSes(@PathVariable("uuids") String uuids,@RequestParam(name = "fw", required = false) String fw, @ModelAttribute("item") Sociodemographic item,
                BindingResult result, Model model,Principal principal) {
            if (result.hasErrors()) {
          	  model.addAttribute("fw", fw);
                // Handle validation errors if necessary
                return "approvals/ses";
            } else {
                List<Sociodemographic> optionalItem = sesrepo.findByUuids(uuids);
                if (!optionalItem.isEmpty()) {
                	Sociodemographic existingImg = optionalItem.get(0);
                	String userName = principal.getName();
                	String groupRole = getGroupRole();
                	
                    existingImg.setApproveDate(new Date()); // Set the approveDate using a method
                    //existingImg.setStatus(item.getStatus());
                    existingImg.setSupervisor(userName);
                    if ("ROLE_SUPERVISOR".equals(groupRole) && item.getStatus() == 1) {
                        existingImg.setStatus(4);
                    } else {
                        existingImg.setStatus(item.getStatus());
                    }               
                    // Ensure comment is null if status is not 2
                    if (item.getStatus() != 2) {
                        existingImg.setComment(null);
                    } else {
                        existingImg.setComment(item.getComment());
                    }
                    sesrepo.save(existingImg);
                    return "redirect:/hdss/approval/seslist?fw=" + fw; // Corrected redirect URL
                } else {
                    return "error"; 
                }
            }
        }
        
        
        
        @Autowired
        VaccinationRepository vacrepo;
          
        @GetMapping("/approval/vaclist")
      	public String vac(@RequestParam(name = "fw", required = false)  String fw,Model model) {
      	        
      	      	List<Fieldworker> fws = field.fw();
    			//System.out.println("Villages: " + villages);
    			model.addAttribute("fws", fws);
    	    	
    	    	
    			if (fw != null && !fw.equals("Select User")) {
    				List<Vaccination> items = vacrepo.findItem(fw);
    	  	        model.addAttribute("items", items);
    		        model.addAttribute("selectedFw", fw);
    		      //System.out.println("Print Items: " + items);
    			}else if ("Select User".equals(fw)) {
    				List<Vaccination> items = vacrepo.findItems();
    	  	        model.addAttribute("items", items);
    				model.addAttribute("selectedFw", "Select User");
    			}else {
    				List<Vaccination> items = vacrepo.findItems();
    	  	        model.addAttribute("items", items);
    				model.addAttribute("selectedFw", "Select User");
    			}
    			   				


      	    return "approvals/vac_list";
      	}

        
        @GetMapping("/reject/vaccination")
      	public String vacReject(Model model) {

      			List<Vaccination> items = vacrepo.findRej();
      	        model.addAttribute("items", items);

      	    return "approvals/vac_reject";
      	}

      	@GetMapping("/approval/vac/{uuid}")
          public String editVav(@PathVariable("uuid") String uuid,@RequestParam(name = "fw", required = false) String fw, Model model, Principal principal) {
              List<Vaccination> optionalItem = vacrepo.findByUuid(uuid);

              if (!optionalItem.isEmpty()) {
            	  Vaccination item = optionalItem.get(0);

                  // Supervisor
                  String userName = principal.getName();

                  if (item.getSupervisor() == null || item.getSupervisor().isEmpty()) {
                      item.setSupervisor(userName);
                  }

                  model.addAttribute("item", item);
                  model.addAttribute("fw", fw);
                  // Populate model with codebook data
                  VacCodebookData(model);

                  return "approvals/vac";
              } else {
                  return "error";
              }
          }

          private void VacCodebookData(Model model) {
              model.addAttribute("yn", repo.yn());
              model.addAttribute("hcard", repo.hcard());
              model.addAttribute("reavac", repo.reavac());
              model.addAttribute("onet", repo.onet());
              model.addAttribute("rea", repo.rea());
              model.addAttribute("scar", repo.scar());
              model.addAttribute("hl", repo.hl());
              model.addAttribute("nhis", repo.nhis());
              
              
          }

          @PostMapping("/approval/vac/{uuids}")
          public String updateVac(@PathVariable("uuids") String uuids,@RequestParam(name = "fw", required = false) String fw, @ModelAttribute("item") Vaccination item,
                  BindingResult result, Model model,Principal principal) {
              if (result.hasErrors()) {
            	  model.addAttribute("fw", fw);
                  // Handle validation errors if necessary
                  return "approvals/vac";
              } else {
                  List<Vaccination> optionalItem = vacrepo.findByUuids(uuids);
                  if (!optionalItem.isEmpty()) {
                	  Vaccination existingImg = optionalItem.get(0);
                	  String userName = principal.getName();
                  	String groupRole = getGroupRole();
                  	
                      existingImg.setApproveDate(new Date()); // Set the approveDate using a method
                      //existingImg.setStatus(item.getStatus());
                      existingImg.setSupervisor(userName);
                      if ("ROLE_SUPERVISOR".equals(groupRole) && item.getStatus() == 1) {
                          existingImg.setStatus(4);
                      } else {
                          existingImg.setStatus(item.getStatus());
                      }               
                      // Ensure comment is null if status is not 2
                      if (item.getStatus() != 2) {
                          existingImg.setComment(null);
                      } else {
                          existingImg.setComment(item.getComment());
                      }
                      vacrepo.save(existingImg);
                      return "redirect:/hdss/approval/vaclist?fw=" + fw; // Corrected redirect URL
                  } else {
                      return "error"; 
                  }
              }
          }
          
          
          
          @Autowired
          MorbidityRepository morrepo;
            
          @GetMapping("/approval/morlist")
        	public String mor(@RequestParam(name = "fw", required = false)  String fw,Model model) {
        	        
        	      	List<Fieldworker> fws = field.fw();
      			//System.out.println("Villages: " + villages);
      			model.addAttribute("fws", fws);
      	    	
      	    	
      			if (fw != null && !fw.equals("Select User")) {
      				List<Morbidity> items = morrepo.findItem(fw);
      	  	        model.addAttribute("items", items);
      		        model.addAttribute("selectedFw", fw);
      		      //System.out.println("Print Items: " + items);
      			}else if ("Select User".equals(fw)) {
      				List<Morbidity> items = morrepo.findItems();
      	  	        model.addAttribute("items", items);
      				model.addAttribute("selectedFw", "Select User");
      			}else {
      				List<Morbidity> items = morrepo.findItems();
      	  	        model.addAttribute("items", items);
      				model.addAttribute("selectedFw", "Select User");
      			}

        	    return "approvals/mor_list";
        	}

          
          @GetMapping("/reject/morbidity")
        	public String morReject(Model model) {

        			List<Morbidity> items = morrepo.findRej();
        	        model.addAttribute("items", items);

        	    return "approvals/mor_reject";
        	}

        	@GetMapping("/approval/mor/{uuid}")
            public String editmorbidity(@PathVariable("uuid") String uuid,@RequestParam(name = "fw", required = false) String fw, Model model, Principal principal) {
                List<Morbidity> optionalItem = morrepo.findByUuid(uuid);

                if (!optionalItem.isEmpty()) {
                	Morbidity item = optionalItem.get(0);

                    // Supervisor
                    String userName = principal.getName();

                    if (item.getSupervisor() == null || item.getSupervisor().isEmpty()) {
                        item.setSupervisor(userName);
                    }

                    model.addAttribute("item", item);
                    model.addAttribute("fw", fw);
                    // Populate model with codebook data
                    MorCodebookData(model);

                    return "approvals/mor";
                } else {
                    return "error";
                }
            }

            private void MorCodebookData(Model model) {
                model.addAttribute("yn", repo.yn());
              
                
            }

            @PostMapping("/approval/mor/{uuids}")
            public String updateMor(@PathVariable("uuids") String uuids,@RequestParam(name = "fw", required = false) String fw, @ModelAttribute("item") Morbidity item,
                    BindingResult result, Model model,Principal principal) {
                if (result.hasErrors()) {
              	  model.addAttribute("fw", fw);
                    // Handle validation errors if necessary
                    return "approvals/mor";
                } else {
                    List<Morbidity> optionalItem = morrepo.findByUuids(uuids);
                    if (!optionalItem.isEmpty()) {
                    	Morbidity existingImg = optionalItem.get(0);
                  	  String userName = principal.getName();
                    	String groupRole = getGroupRole();
                    	
                        existingImg.setApproveDate(new Date()); // Set the approveDate using a method
                        //existingImg.setStatus(item.getStatus());
                        existingImg.setSupervisor(userName);
                        if ("ROLE_SUPERVISOR".equals(groupRole) && item.getStatus() == 1) {
                            existingImg.setStatus(4);
                        } else {
                            existingImg.setStatus(item.getStatus());
                        }               
                        // Ensure comment is null if status is not 2
                        if (item.getStatus() != 2) {
                            existingImg.setComment(null);
                        } else {
                            existingImg.setComment(item.getComment());
                        }
                        morrepo.save(existingImg);
                        return "redirect:/hdss/approval/morlist?fw=" + fw; // Corrected redirect URL
                    } else {
                        return "error"; 
                    }
                }
            }
          
          
          private String getGroupRole() {
        	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        	    if (authentication != null) {
        	        for (GrantedAuthority authority : authentication.getAuthorities()) {
        	            // Assuming authorities are SimpleGrantedAuthority with role as authority name
        	            return authority.getAuthority(); // This will return the group_role associated with the user
        	        }
        	    }
        	    return null; // Return null or handle accordingly if no group_role found
        	}        
          
 
          
}

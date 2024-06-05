package org.arn.hdsscapture.formapproval;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.arn.hdsscapture.entity.Death;
import org.arn.hdsscapture.entity.Demographic;
import org.arn.hdsscapture.entity.Inmigration;
import org.arn.hdsscapture.entity.Outmigration;
import org.arn.hdsscapture.entity.Pregnancyobservation;
import org.arn.hdsscapture.entity.Pregnancyoutcome;
import org.arn.hdsscapture.entity.Relationship;
import org.arn.hdsscapture.repository.CodebookRepository;
import org.arn.hdsscapture.repository.DeathRepository;
import org.arn.hdsscapture.repository.DemographicRepository;
import org.arn.hdsscapture.repository.FieldworkerRepository;
import org.arn.hdsscapture.repository.InmigrationRepository;
import org.arn.hdsscapture.repository.OutmigrationRepository;
import org.arn.hdsscapture.repository.PregnancyobservationRepository;
import org.arn.hdsscapture.repository.PregnancyoutcomeRepository;
import org.arn.hdsscapture.repository.RelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


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


        return ResponseEntity.ok(data);
    }

	@GetMapping("/approval")
	public String main(Model model) {

		return "approvals/approval";
	}

	@Autowired
	InmigrationRepository imgrepo;

	@GetMapping("/approval/imglist")
	public String search(Model model) {

	        List<Inmigration> items = imgrepo.findImg();
	        model.addAttribute("items", items);


	    return "approvals/img_list";
	}


	@GetMapping("/approval/edit/{uuid}")
    public String editImg(@PathVariable("uuid") String uuid, Model model, Principal principal) {
        List<Inmigration> optionalImg = imgrepo.findByUuid(uuid);

        if (!optionalImg.isEmpty()) {
        	Inmigration item = optionalImg.get(0);

            // Supervisor
            String userName = principal.getName();

            if (item.getSupervisor() == null || item.getSupervisor().isEmpty()) {
                item.setSupervisor(userName);
            }

            model.addAttribute("item", item);

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
        model.addAttribute("cashcrops", repo.cashcrops());
    }

    @PostMapping("/approval/{uuid}")
    public String updateImg(@PathVariable("uuid") String uuid, @ModelAttribute("item") Inmigration item,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Handle validation errors if necessary
            return "approvals/img";
        } else {
            List<Inmigration> optionalImg = imgrepo.findByUuids(uuid);
            if (!optionalImg.isEmpty()) {
                Inmigration existingImg = optionalImg.get(0);
                existingImg.setApproveDate(new Date()); // Set the approveDate using a method
                existingImg.setStatus(item.getStatus());
                existingImg.setSupervisor(item.getSupervisor());
                // Ensure comment is null if status is not 2
                if (item.getStatus() != 2) {
                    existingImg.setComment(null);
                } else {
                    existingImg.setComment(item.getComment());
                }
                
                imgrepo.save(existingImg);
                return "redirect:/hdss/approval/imglist"; // Corrected redirect URL
            } else {
                return "error"; // Handle the case when the Inmigration with the given UUID is not found
            }
        }
    }

    @Autowired
	OutmigrationRepository omgrepo;
    
    @GetMapping("/approval/omglist")
	public String omg(Model model) {

	        List<Outmigration> items = omgrepo.findItem();
	        model.addAttribute("items", items);


	    return "approvals/omg_list";
	}


	@GetMapping("/approval/omg/{uuid}")
    public String editOmg(@PathVariable("uuid") String uuid, Model model, Principal principal) {
        List<Outmigration> optionalImg = omgrepo.findByUuid(uuid);

        if (!optionalImg.isEmpty()) {
        	Outmigration item = optionalImg.get(0);

            // Supervisor
            String userName = principal.getName();

            if (item.getSupervisor() == null || item.getSupervisor().isEmpty()) {
                item.setSupervisor(userName);
            }

            model.addAttribute("item", item);

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

    @PostMapping("/approval/omg/{uuid}")
    public String updateOmg(@PathVariable("uuid") String uuid, @ModelAttribute("item") Outmigration item,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Handle validation errors if necessary
            return "approvals/omg";
        } else {
            List<Outmigration> optionalImg = omgrepo.findByUuids(uuid);
            if (!optionalImg.isEmpty()) {
            	Outmigration existingImg = optionalImg.get(0);
                existingImg.setApproveDate(new Date()); // Set the approveDate using a method
                existingImg.setStatus(item.getStatus());
                existingImg.setSupervisor(item.getSupervisor());
             // Ensure comment is null if status is not 2
                if (item.getStatus() != 2) {
                    existingImg.setComment(null);
                } else {
                    existingImg.setComment(item.getComment());
                }
                omgrepo.save(existingImg);
                return "redirect:/hdss/approval/omglist"; // Corrected redirect URL
            } else {
                return "error"; // Handle the case when the Inmigration with the given UUID is not found
            }
        }
    }
    
    
    @Autowired
	DeathRepository dthrepo;
    
    @GetMapping("/approval/dthlist")
	public String dth(Model model) {

	        List<Death> items = dthrepo.findItem();
	        model.addAttribute("items", items);


	    return "approvals/dth_list";
	}


	@GetMapping("/approval/dth/{uuid}")
    public String editDth(@PathVariable("uuid") String uuid, Model model, Principal principal) {
        List<Death> optionalImg = dthrepo.findByUuid(uuid);

        if (!optionalImg.isEmpty()) {
        	Death item = optionalImg.get(0);

            // Supervisor
            String userName = principal.getName();

            if (item.getSupervisor() == null || item.getSupervisor().isEmpty()) {
                item.setSupervisor(userName);
            }

            model.addAttribute("item", item);

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
    }

    @PostMapping("/approval/dth/{uuid}")
    public String updateDth(@PathVariable("uuid") String uuid, @ModelAttribute("item") Death item,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Handle validation errors if necessary
            return "approvals/dth";
        } else {
            List<Death> optionalImg = dthrepo.findByUuids(uuid);
            if (!optionalImg.isEmpty()) {
            	Death existingImg = optionalImg.get(0);
                existingImg.setApproveDate(new Date()); // Set the approveDate using a method
                existingImg.setStatus(item.getStatus());
                existingImg.setSupervisor(item.getSupervisor());
             // Ensure comment is null if status is not 2
                if (item.getStatus() != 2) {
                    existingImg.setComment(null);
                } else {
                    existingImg.setComment(item.getComment());
                }
                dthrepo.save(existingImg);
                return "redirect:/hdss/approval/dthlist"; // Corrected redirect URL
            } else {
                return "error"; // Handle the case when the Inmigration with the given UUID is not found
            }
        }
    }

    
    @Autowired
	PregnancyobservationRepository pregrepo;
    
    @GetMapping("/approval/preglist")
	public String preg(Model model) {

	        List<Pregnancyobservation> items = pregrepo.findItem();
	        model.addAttribute("items", items);


	    return "approvals/preg_list";
	}


	@GetMapping("/approval/preg/{uuid}")
    public String editPreg(@PathVariable("uuid") String uuid, Model model, Principal principal) {
        List<Pregnancyobservation> optionalImg = pregrepo.findByUuid(uuid);

        if (!optionalImg.isEmpty()) {
        	Pregnancyobservation item = optionalImg.get(0);

            // Supervisor
            String userName = principal.getName();

            if (item.getSupervisor() == null || item.getSupervisor().isEmpty()) {
                item.setSupervisor(userName);
            }

            model.addAttribute("item", item);

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
        
        
    }

    @PostMapping("/approval/preg/{uuid}")
    public String updatePreg(@PathVariable("uuid") String uuid, @ModelAttribute("item") Pregnancyobservation item,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Handle validation errors if necessary
            return "approvals/preg";
        } else {
            List<Pregnancyobservation> optionalImg = pregrepo.findByUuids(uuid);
            if (!optionalImg.isEmpty()) {
            	Pregnancyobservation existingImg = optionalImg.get(0);
                existingImg.setApproveDate(new Date()); // Set the approveDate using a method
                existingImg.setStatus(item.getStatus());
                existingImg.setSupervisor(item.getSupervisor());
                // Ensure comment is null if status is not 2
                if (item.getStatus() != 2) {
                    existingImg.setComment(null);
                } else {
                    existingImg.setComment(item.getComment());
                }
                pregrepo.save(existingImg);
                return "redirect:/hdss/approval/preglist"; // Corrected redirect URL
            } else {
                return "error"; // Handle the case when the Inmigration with the given UUID is not found
            }
        }
    }
    
    
    @Autowired
	PregnancyoutcomeRepository outrepo;
    
    @GetMapping("/approval/outcomelist")
	public String out(Model model) {

	        List<Pregnancyoutcome> items = outrepo.findItem();
	        model.addAttribute("items", items);


	    return "approvals/outcome_list";
	}


	@GetMapping("/approval/outcome/{uuid}")
    public String editOut(@PathVariable("uuid") String uuid, Model model, Principal principal) {
        List<Pregnancyoutcome> optionalImg = outrepo.findByUuid(uuid);

        if (!optionalImg.isEmpty()) {
        	Pregnancyoutcome item = optionalImg.get(0);

            // Supervisor
            String userName = principal.getName();

            if (item.getSupervisor() == null || item.getSupervisor().isEmpty()) {
                item.setSupervisor(userName);
            }

            model.addAttribute("item", item);

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
        
        
    }

    @PostMapping("/approval/outcome/{uuid}")
    public String updateOut(@PathVariable("uuid") String uuid, @ModelAttribute("item") Pregnancyoutcome item,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Handle validation errors if necessary
            return "approvals/outcome";
        } else {
            List<Pregnancyoutcome> optionalImg = outrepo.findByUuids(uuid);
            if (!optionalImg.isEmpty()) {
            	Pregnancyoutcome existingImg = optionalImg.get(0);
                existingImg.setApproveDate(new Date()); // Set the approveDate using a method
                existingImg.setStatus(item.getStatus());
                existingImg.setSupervisor(item.getSupervisor());
                // Ensure comment is null if status is not 2
                if (item.getStatus() != 2) {
                    existingImg.setComment(null);
                } else {
                    existingImg.setComment(item.getComment());
                }
                outrepo.save(existingImg);
                return "redirect:/hdss/approval/outcomelist"; // Corrected redirect URL
            } else {
                return "error"; // Handle the case when the Inmigration with the given UUID is not found
            }
        }
    }
    
    
    
    @Autowired
	DemographicRepository demorepo;
    
    @GetMapping("/approval/demolist")
	public String demo(Model model) {

	        List<Demographic> items = demorepo.findItem();
	        model.addAttribute("items", items);


	    return "approvals/demo_list";
	}


	@GetMapping("/approval/demo/{uuid}")
    public String editDemo(@PathVariable("uuid") String uuid, Model model, Principal principal) {
        List<Demographic> optionalImg = demorepo.findByUuid(uuid);

        if (!optionalImg.isEmpty()) {
        	Demographic item = optionalImg.get(0);

            // Supervisor
            String userName = principal.getName();

            if (item.getSupervisor() == null || item.getSupervisor().isEmpty()) {
                item.setSupervisor(userName);
            }

            model.addAttribute("item", item);

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
                
        
    }

    @PostMapping("/approval/demo/{uuid}")
    public String updateDemo(@PathVariable("uuid") String uuid, @ModelAttribute("item") Demographic item,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Handle validation errors if necessary
            return "approvals/demo";
        } else {
            List<Demographic> optionalImg = demorepo.findByUuids(uuid);
            if (!optionalImg.isEmpty()) {
            	Demographic existingImg = optionalImg.get(0);
                existingImg.setApproveDate(new Date()); // Set the approveDate using a method
                existingImg.setStatus(item.getStatus());
                existingImg.setSupervisor(item.getSupervisor());
                // Ensure comment is null if status is not 2
                if (item.getStatus() != 2) {
                    existingImg.setComment(null);
                } else {
                    existingImg.setComment(item.getComment());
                }
                demorepo.save(existingImg);
                return "redirect:/hdss/approval/demolist"; // Corrected redirect URL
            } else {
                return "error"; // Handle the case when the Inmigration with the given UUID is not found
            }
        }
    }
    
    
    
    @Autowired
  	RelationshipRepository relrepo;
      
      @GetMapping("/approval/rellist")
  	public String rel(Model model) {

  	        List<Relationship> items = relrepo.findItem();
  	        model.addAttribute("items", items);


  	    return "approvals/rel_list";
  	}


  	@GetMapping("/approval/rel/{uuid}")
      public String editRel(@PathVariable("uuid") String uuid, Model model, Principal principal) {
          List<Relationship> optionalImg = relrepo.findByUuid(uuid);

          if (!optionalImg.isEmpty()) {
        	  Relationship item = optionalImg.get(0);

              // Supervisor
              String userName = principal.getName();

              if (item.getSupervisor() == null || item.getSupervisor().isEmpty()) {
                  item.setSupervisor(userName);
              }

              model.addAttribute("item", item);

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

      @PostMapping("/approval/rel/{uuid}")
      public String updateRel(@PathVariable("uuid") String uuid, @ModelAttribute("item") Relationship item,
              BindingResult result, Model model) {
          if (result.hasErrors()) {
              // Handle validation errors if necessary
              return "approvals/rel";
          } else {
              List<Relationship> optionalImg = relrepo.findByUuids(uuid);
              if (!optionalImg.isEmpty()) {
            	  Relationship existingImg = optionalImg.get(0);
                  existingImg.setApproveDate(new Date()); // Set the approveDate using a method
                  existingImg.setStatus(item.getStatus());
                  existingImg.setSupervisor(item.getSupervisor());
                  // Ensure comment is null if status is not 2
                  if (item.getStatus() != 2) {
                      existingImg.setComment(null);
                  } else {
                      existingImg.setComment(item.getComment());
                  }
                  relrepo.save(existingImg);
                  return "redirect:/hdss/approval/rellist"; // Corrected redirect URL
              } else {
                  return "error"; // Handle the case when the Inmigration with the given UUID is not found
              }
          }
      }

}

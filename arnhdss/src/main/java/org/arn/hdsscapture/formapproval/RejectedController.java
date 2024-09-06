package org.arn.hdsscapture.formapproval;

import java.util.List;

import org.arn.hdsscapture.entity.Death;
import org.arn.hdsscapture.entity.Demographic;
import org.arn.hdsscapture.entity.Morbidity;
import org.arn.hdsscapture.entity.Outmigration;
import org.arn.hdsscapture.entity.Pregnancyobservation;
import org.arn.hdsscapture.entity.Pregnancyoutcome;
import org.arn.hdsscapture.entity.Relationship;
import org.arn.hdsscapture.entity.Sociodemographic;
import org.arn.hdsscapture.entity.Vaccination;
import org.arn.hdsscapture.projection.Img;
import org.arn.hdsscapture.repository.DeathRepository;
import org.arn.hdsscapture.repository.DemographicRepository;
import org.arn.hdsscapture.repository.InmigrationRepository;
import org.arn.hdsscapture.repository.MorbidityRepository;
import org.arn.hdsscapture.repository.OutmigrationRepository;
import org.arn.hdsscapture.repository.PregnancyobservationRepository;
import org.arn.hdsscapture.repository.PregnancyoutcomeRepository;
import org.arn.hdsscapture.repository.RelationshipRepository;
import org.arn.hdsscapture.repository.SesRepository;
import org.arn.hdsscapture.repository.VaccinationRepository;
import org.arn.hdsscapture.utils.DataWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RejectedController {
	
	@Autowired
	RelationshipRepository repo;
	
	
	@GetMapping("/relationship/reject")
	public DataWrapper<Relationship> findAll(@RequestParam String fw) {

		List<Relationship> data = repo.rejected(fw);

		DataWrapper<Relationship> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@Autowired
	InmigrationRepository imgrepo;
	
	
	@GetMapping("/inmigration/reject")
	public DataWrapper<Img> findImg(@RequestParam String fw) {

		List<Img> data = imgrepo.rejected(fw);

		DataWrapper<Img> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@Autowired
	OutmigrationRepository omgrepo;
	
	
	@GetMapping("/outmigration/reject")
	public DataWrapper<Outmigration> findOmg(@RequestParam String fw) {

		List<Outmigration> data = omgrepo.rejected(fw);

		DataWrapper<Outmigration> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@Autowired
	DeathRepository dthrepo;
	
	
	@GetMapping("/death/reject")
	public DataWrapper<Death> findDth(@RequestParam String fw) {

		List<Death> data = dthrepo.rejected(fw);

		DataWrapper<Death> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@Autowired
	PregnancyobservationRepository pregrepo;
	
	
	@GetMapping("/pregnancy/reject")
	public DataWrapper<Pregnancyobservation> findPreg(@RequestParam String fw) {

		List<Pregnancyobservation> data = pregrepo.rejected(fw);

		DataWrapper<Pregnancyobservation> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@Autowired
	PregnancyoutcomeRepository outrepo;
	
	
	@GetMapping("/outcome/reject")
	public DataWrapper<Pregnancyoutcome> findOut(@RequestParam String fw) {

		List<Pregnancyoutcome> data = outrepo.rejected(fw);

		DataWrapper<Pregnancyoutcome> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@Autowired
	DemographicRepository demorepo;
	
	
	@GetMapping("/demographic/reject")
	public DataWrapper<Demographic> findDemo(@RequestParam String fw) {

		List<Demographic> data = demorepo.rejected(fw);

		DataWrapper<Demographic> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@Autowired
	VaccinationRepository vacrepo;
	
	
	@GetMapping("/vac/reject")
	public DataWrapper<Vaccination> findVac(@RequestParam String fw) {

		List<Vaccination> data = vacrepo.rejected(fw);

		DataWrapper<Vaccination> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@Autowired
	SesRepository sesrepo;
	
	
	@GetMapping("/ses/reject")
	public DataWrapper<Sociodemographic> findSes(@RequestParam String fw) {

		List<Sociodemographic> data = sesrepo.rejected(fw);

		DataWrapper<Sociodemographic> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}	
	
	@Autowired
	MorbidityRepository morrepo;
	
	@GetMapping("/mor/reject")
	public DataWrapper<Morbidity> findMor(@RequestParam String fw) {

		List<Morbidity> data = morrepo.rejected(fw);

		DataWrapper<Morbidity> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}	

}

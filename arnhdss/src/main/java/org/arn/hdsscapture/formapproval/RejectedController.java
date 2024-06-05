package org.arn.hdsscapture.formapproval;

import java.util.List;

import org.arn.hdsscapture.entity.Death;
import org.arn.hdsscapture.entity.Demographic;
import org.arn.hdsscapture.entity.Inmigration;
import org.arn.hdsscapture.entity.Outmigration;
import org.arn.hdsscapture.entity.Pregnancyobservation;
import org.arn.hdsscapture.entity.Pregnancyoutcome;
import org.arn.hdsscapture.entity.Relationship;
import org.arn.hdsscapture.repository.DeathRepository;
import org.arn.hdsscapture.repository.DemographicRepository;
import org.arn.hdsscapture.repository.InmigrationRepository;
import org.arn.hdsscapture.repository.OutmigrationRepository;
import org.arn.hdsscapture.repository.PregnancyobservationRepository;
import org.arn.hdsscapture.repository.PregnancyoutcomeRepository;
import org.arn.hdsscapture.repository.RelationshipRepository;
import org.arn.hdsscapture.utils.DataWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RejectedController {
	
	@Autowired
	RelationshipRepository repo;
	
	
	@GetMapping("/relationship/reject")
	public DataWrapper<Relationship> findAll() {

		List<Relationship> data = repo.rejected();

		DataWrapper<Relationship> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@Autowired
	InmigrationRepository imgrepo;
	
	
	@GetMapping("/inmigration/reject")
	public DataWrapper<Inmigration> findImg() {

		List<Inmigration> data = imgrepo.rejected();

		DataWrapper<Inmigration> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@Autowired
	OutmigrationRepository omgrepo;
	
	
	@GetMapping("/outmigration/reject")
	public DataWrapper<Outmigration> findOmg() {

		List<Outmigration> data = omgrepo.rejected();

		DataWrapper<Outmigration> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@Autowired
	DeathRepository dthrepo;
	
	
	@GetMapping("/death/reject")
	public DataWrapper<Death> findDth() {

		List<Death> data = dthrepo.rejected();

		DataWrapper<Death> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@Autowired
	PregnancyobservationRepository pregrepo;
	
	
	@GetMapping("/pregnancy/reject")
	public DataWrapper<Pregnancyobservation> findPreg() {

		List<Pregnancyobservation> data = pregrepo.rejected();

		DataWrapper<Pregnancyobservation> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@Autowired
	PregnancyoutcomeRepository outrepo;
	
	
	@GetMapping("/outcome/reject")
	public DataWrapper<Pregnancyoutcome> findOut() {

		List<Pregnancyoutcome> data = outrepo.rejected();

		DataWrapper<Pregnancyoutcome> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}
	
	@Autowired
	DemographicRepository demorepo;
	
	
	@GetMapping("/demographic/reject")
	public DataWrapper<Demographic> findDemo() {

		List<Demographic> data = demorepo.rejected();

		DataWrapper<Demographic> w = new DataWrapper<>();
		w.setData(data);

		return w;
	}

}

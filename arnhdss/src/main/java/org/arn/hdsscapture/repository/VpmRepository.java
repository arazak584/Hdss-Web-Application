package org.arn.hdsscapture.repository;

import java.util.List;

import org.arn.hdsscapture.entity.Vpm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VpmRepository extends JpaRepository <Vpm, String> {


	@Query(nativeQuery = true, value = "SELECT permid as uuid,dob,gender as extId,name as firstName,dod as deathDate,compno,househead,compname,"
			+ "villname,villcode,fieldworker,null as gender,null as individual_uuid,null as lastName,null as deathCause,"
			+ "null as deathPlace,null as fw_uuid,null as visit_uuid,null as insertDate,null as complete from vpms")
    List<Vpm> findVpm();
}

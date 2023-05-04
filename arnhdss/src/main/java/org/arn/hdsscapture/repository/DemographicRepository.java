package org.arn.hdsscapture.repository;

import java.util.List;

import org.arn.hdsscapture.entity.Demographic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface DemographicRepository extends JpaRepository <Demographic, String> {

	
	@Query(nativeQuery = true, value = "SELECT a.* FROM demographic a LEFT JOIN \r\n"
			+ "death b ON a.individual_uuid=b.individual_uuid where b.individual_uuid is null;")
	List<Demographic> findDemographic();


}

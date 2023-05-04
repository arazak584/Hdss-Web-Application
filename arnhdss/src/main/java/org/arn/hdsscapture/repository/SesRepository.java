package org.arn.hdsscapture.repository;

import java.util.List;

import org.arn.hdsscapture.entity.Sociodemographic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SesRepository extends JpaRepository <Sociodemographic, String> {
	
	@Query(nativeQuery = true, value = "SELECT a.* from sociodemographic a inner join residency b on "
			+ "a.socialgroup_uuid=b.socialgroup_uuid where b.endType=1 group by a.socialgroup_uuid;")
	List<Sociodemographic> findSes();

}

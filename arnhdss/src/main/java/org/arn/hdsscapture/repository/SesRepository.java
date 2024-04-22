package org.arn.hdsscapture.repository;

import java.util.List;

import org.arn.hdsscapture.entity.Sociodemographic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SesRepository extends JpaRepository <Sociodemographic, String> {
	
	@Query(nativeQuery = true, value = "SELECT a.* FROM sociodemographic a INNER JOIN residency b ON a.socialgroup_uuid = b.socialgroup_uuid "
	        + " WHERE b.endType = 1 AND a.insertDate >= (SELECT r.sesDate from settings r) GROUP BY a.socialgroup_uuid LIMIT ?1 OFFSET ?2")
	List<Sociodemographic> findSes(int pageSize, int offset);


}

package org.arn.hdsscapture.repository;

import java.util.List;

import org.arn.hdsscapture.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VisitRepository extends JpaRepository <Visit, String> {


	@Query(nativeQuery = true, value = "SELECT * from visit ORDER BY visitDate DESC limit 1")
	List<Visit> findV();
	
}

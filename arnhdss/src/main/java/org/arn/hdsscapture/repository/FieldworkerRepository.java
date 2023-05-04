package org.arn.hdsscapture.repository;

import java.util.List;

import org.arn.hdsscapture.entity.Fieldworker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface FieldworkerRepository extends JpaRepository <Fieldworker, String> {

	@Query(nativeQuery = true, value = "SELECT * from fieldworker where status=1 ORDER BY username")
	List<Fieldworker> findAll();
	
	@Query(nativeQuery = true, value = "SELECT * from fieldworker ORDER BY insertDate")
	List<Fieldworker> findFieldworker();
	
	@Query(nativeQuery = true, value = "SELECT * from fieldworker WHERE username = :username ORDER BY username")
	List<Fieldworker> findFieldworkerByUsername(@Param("username") String username);
	
	Fieldworker findByUsername(String username);
	
}

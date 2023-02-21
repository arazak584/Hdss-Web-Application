package com.arn.hdss.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.arn.hdss.entity.Fieldworker;

public interface FieldworkerRepository extends JpaRepository <Fieldworker, Long> {

	@Query(nativeQuery = true, value = "SELECT * from fieldworker where status=1 ORDER BY username")
	List<Fieldworker> findAll();
	
	@Query(nativeQuery = true, value = "SELECT * from fieldworker ORDER BY username")
	List<Fieldworker> findFieldworker();
}

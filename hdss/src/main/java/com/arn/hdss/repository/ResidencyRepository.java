package com.arn.hdss.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.arn.hdss.entity.Residency;

public interface ResidencyRepository extends JpaRepository <Residency, String> {

	@Query(nativeQuery = true, value = "SELECT m1.* FROM hdss.residency m1,\r\n"
			+ "(SELECT MAX(startDate) AS startDate, extId FROM hdss.residency\r\n"
			+ "GROUP BY extId) residency\r\n"
			+ "WHERE m1.startDate = residency.startDate AND m1.extId = residency.extId;")
	List<Residency> findAll();

}

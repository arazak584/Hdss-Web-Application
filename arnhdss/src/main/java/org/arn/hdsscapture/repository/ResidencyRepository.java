package org.arn.hdsscapture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.arn.hdsscapture.entity.Residency;

public interface ResidencyRepository extends JpaRepository <Residency, String> {

//	@Query(nativeQuery = true, value = "SELECT m1.* FROM hdss.residency m1,\r\n"
//			+ "(SELECT MAX(startDate) AS startDate, individual_uuid FROM hdss.residency\r\n"
//			+ "GROUP BY individual_uuid) residency\r\n"
//			+ "WHERE m1.startDate = residency.startDate AND m1.individual_uuid = residency.individual_uuid;")
//	List<Residency> findResidency();
	
	@Query(nativeQuery = true, value = "SELECT m1.* FROM hdss.residency m1,\r\n"
		    + "(SELECT MAX(startDate) AS startDate, individual_uuid FROM hdss.residency\r\n"
		    + "GROUP BY individual_uuid) residency\r\n"
		    + "WHERE m1.startDate = residency.startDate AND m1.individual_uuid = residency.individual_uuid "
		    + "AND NOT EXISTS (SELECT 1 FROM hdss.residency m2 "
		    + "WHERE m2.individual_uuid = residency.individual_uuid AND m2.endtype = 3);")
		List<Residency> findResidency();


}

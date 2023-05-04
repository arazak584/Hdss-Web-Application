package org.arn.hdsscapture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.arn.hdsscapture.entity.Visit;

public interface VisitRepository extends JpaRepository <Visit, String> {

//	@Query("SELECT * FROM Visit v "
//			+ " WHERE v.visitDate BETWEEN :startDate AND :endDate GROUP BY v.fw_uuid ")
//    List<Visit> RangeVisitDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);


}

package org.arn.hdsscapture.repository;

import org.arn.hdsscapture.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VisitRepository extends JpaRepository <Visit, String> {


//	@Query("SELECT CONCAT(f.firstName, ' ', f.lastName) AS fieldworker, v.insertDate, COUNT(v.visitExtId) AS number " +
//            "FROM Visit v INNER JOIN v.fieldworker f " +
//            "WHERE v.visitDate BETWEEN :startDate AND :endDate " +
//            "GROUP BY v.fieldworker")
//    List<Object[]> generateAggregateReport(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

	
}

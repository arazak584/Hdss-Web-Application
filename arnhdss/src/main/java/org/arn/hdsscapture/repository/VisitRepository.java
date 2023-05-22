package org.arn.hdsscapture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

import org.arn.hdsscapture.entity.Visit;

public interface VisitRepository extends JpaRepository <Visit, String> {


//	@Query("SELECT CONCAT(f.firstName, ' ', f.lastName) AS fieldworker, v.insertDate, COUNT(v.visitExtId) AS number " +
//            "FROM Visit v INNER JOIN v.fieldworker f " +
//            "WHERE v.visitDate BETWEEN :startDate AND :endDate " +
//            "GROUP BY v.fieldworker")
//    List<Object[]> generateAggregateReport(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
	
}

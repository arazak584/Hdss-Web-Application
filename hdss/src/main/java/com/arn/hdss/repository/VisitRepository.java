package com.arn.hdss.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.arn.hdss.entity.Visit;


public interface VisitRepository extends JpaRepository <Visit, String> {

	@Query("SELECT v FROM Visit v WHERE v.visitDate BETWEEN :startDate AND :endDate GROUP BY v.fw")
    List<Visit> findByVisitDateBetween(Date startDate, Date endDate);


}

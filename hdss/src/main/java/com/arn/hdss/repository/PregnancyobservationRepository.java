package com.arn.hdss.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.arn.hdss.entity.Pregnancyobservation;

public interface PregnancyobservationRepository extends JpaRepository <Pregnancyobservation, String> {
	
	@Query(nativeQuery = true, value = "SELECT m1.* FROM hdss.pregnancyobservation m1,\r\n"
			+ "(SELECT MAX(recordedDate) AS recordedDate, extId FROM hdss.pregnancyobservation\r\n"
			+ "GROUP BY extId) pregnancyobservation\r\n"
			+ "WHERE m1.recordedDate = pregnancyobservation.recordedDate AND m1.extId = pregnancyobservation.extId;")
	List<Pregnancyobservation> findAll();


}

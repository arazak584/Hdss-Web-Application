package org.arn.hdsscapture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.arn.hdsscapture.entity.Pregnancyobservation;

public interface PregnancyobservationRepository extends JpaRepository <Pregnancyobservation, String> {
	
	@Query(nativeQuery = true, value = "SELECT m1.* FROM hdss.pregnancyobservation m1,\r\n"
			+ "(SELECT MAX(recordedDate) AS recordedDate, individual_uuid FROM hdss.pregnancyobservation\r\n"
			+ "GROUP BY individual_uuid) pregnancyobservation\r\n"
			+ "WHERE m1.recordedDate = pregnancyobservation.recordedDate AND m1.individual_uuid = pregnancyobservation.individual_uuid;")
	List<Pregnancyobservation> findPregnancy();


}

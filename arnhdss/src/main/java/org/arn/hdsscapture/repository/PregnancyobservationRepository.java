package org.arn.hdsscapture.repository;

import java.util.List;

import org.arn.hdsscapture.entity.Pregnancyobservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PregnancyobservationRepository extends JpaRepository <Pregnancyobservation, String> {
	
//	@Query(nativeQuery = true, value = "SELECT m1.* FROM hdss.pregnancyobservation m1,\r\n"
//			+ "(SELECT MAX(recordedDate) AS recordedDate, individual_uuid FROM hdss.pregnancyobservation\r\n"
//			+ "GROUP BY individual_uuid) pregnancyobservation\r\n"
//			+ "WHERE m1.recordedDate = pregnancyobservation.recordedDate AND m1.individual_uuid = pregnancyobservation.individual_uuid;")
//	List<Pregnancyobservation> findPregnancy();
	
	@Query(nativeQuery = true, value = "SELECT m1.* FROM hdss.pregnancyobservation m1, "
	        + "(SELECT MAX(recordedDate) AS recordedDate, individual_uuid "
	        + "FROM hdss.pregnancyobservation GROUP BY individual_uuid) pregnancyobservation "
	        + "WHERE m1.recordedDate = pregnancyobservation.recordedDate "
	        + "AND m1.individual_uuid = pregnancyobservation.individual_uuid "
	        + "AND m1.recordedDate >= DATE_SUB(CURRENT_DATE(), INTERVAL 5 YEAR) LIMIT ?1 OFFSET ?2")
	List<Pregnancyobservation> findPregnancy(int pageSize, int offset);
	
	@Query(nativeQuery = true, value = "SELECT a.* FROM pregnancyobservation a WHERE a.`status`=2 AND a.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
    List<Pregnancyobservation> rejected();


	@Query(nativeQuery = true, value = "SELECT * FROM pregnancyobservation WHERE uuid = :uuid LIMIT 1")
    List<Pregnancyobservation> findByUuids(@Param("uuid") String uuid);
	
	
	@Query(nativeQuery = true, value ="SELECT	a.uuid,concat(b.firstName,' ',b.lastName,' ',COALESCE(otherName, '')) as individual_uuid,\r\n"
			+ " ageOfPregFromPregNotes, 	anc_visits, 	anteNatalClinic, 	attend_you, 	attend_you_other, 	bnet_loc, 	bnet_loc_other,\r\n" 
			+ " bnet_sou,bnet_sou_other,estimatedAgeOfPreg,expectedDeliveryDate,first_preg,first_rec,concat(f.firstName,' ',f.lastName) as fw_uuid, \r\n"
			+ "healthfacility, 	how_many, 	a.insertDate, 	lastClinicVisitDate, 	medicineforpregnancy, 	outcome, 	outcome_date, 	own_bnet,\r\n"
			+ "pregnancyNumber, 	recordedDate, 	slp_bednet, 	trt_bednet, 	ttinjection,compno as visit_uuid, why_no, why_no_other,\r\n"
			+ "TIMESTAMPDIFF(year,dob,CURDATE()) as edtime,dob as sttime, 	approveDate, 	`comment`, 	a.`status`, 	supervisor\r\n"
			+ "FROM pregnancyobservation a INNER JOIN individual b on a.individual_uuid=b.uuid\r\n"
			+ "INNER JOIN visit c on a.visit_uuid=c.uuid INNER JOIN location d on c.location_uuid=d.uuid\r\n"
			+ "INNER JOIN fieldworker f on a.fw_uuid=f.fw_uuid\r\n"
			+ "where a.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1) AND a.uuid = :uuid LIMIT 1")
	List<Pregnancyobservation> findByUuid(@Param("uuid") String uuid);

		
	@Query(nativeQuery = true, value ="SELECT	a.uuid,concat(b.firstName,' ',b.lastName,' ',COALESCE(otherName, '')) as individual_uuid, "
			+ "ageOfPregFromPregNotes, 	anc_visits,anteNatalClinic,attend_you,attend_you_other,bnet_loc,bnet_loc_other,bnet_sou,"
			+ "bnet_sou_other, 	estimatedAgeOfPreg,expectedDeliveryDate,first_preg,first_rec,concat(f.firstName,' ',f.lastName) as edtime, "
			+ " healthfacility,how_many,a.insertDate, 	lastClinicVisitDate, 	medicineforpregnancy, 	outcome, outcome_date, \r\n"
			+ " own_bnet, 	pregnancyNumber, 	recordedDate, 	slp_bednet, 	trt_bednet, 	ttinjection,compno as visit_uuid, 	"
			+ " why_no, why_no_other, 	a.fw_uuid,dob as sttime, 	approveDate, 	`comment`, 	a.`status`, 	supervisor\r\n"
			+ "FROM pregnancyobservation a INNER JOIN individual b on a.individual_uuid=b.uuid\r\n"
			+ "INNER JOIN visit c on a.visit_uuid=c.uuid INNER JOIN location d on c.location_uuid=d.uuid\r\n"
			+ "INNER JOIN fieldworker f on a.fw_uuid=f.fw_uuid\r\n"
			+ "where a.fw_uuid= :fw AND a.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	List<Pregnancyobservation> findItem(@Param("fw") String fw);
	
	@Query(nativeQuery = true, value ="SELECT	a.uuid,concat(b.firstName,' ',b.lastName,' ',COALESCE(otherName, '')) as individual_uuid, "
			+ "ageOfPregFromPregNotes, 	anc_visits,anteNatalClinic,attend_you,attend_you_other,bnet_loc,bnet_loc_other,bnet_sou,"
			+ "bnet_sou_other, 	estimatedAgeOfPreg,expectedDeliveryDate,first_preg,first_rec,concat(f.firstName,' ',f.lastName) as edtime, "
			+ " healthfacility,how_many,a.insertDate, 	lastClinicVisitDate, 	medicineforpregnancy, 	outcome, outcome_date, \r\n"
			+ " own_bnet, 	pregnancyNumber, 	recordedDate, 	slp_bednet, 	trt_bednet, 	ttinjection,compno as visit_uuid, 	"
			+ " why_no, why_no_other, 	a.fw_uuid,dob as sttime, 	approveDate, 	`comment`, 	a.`status`, 	supervisor\r\n"
			+ "FROM pregnancyobservation a INNER JOIN individual b on a.individual_uuid=b.uuid\r\n"
			+ "INNER JOIN visit c on a.visit_uuid=c.uuid INNER JOIN location d on c.location_uuid=d.uuid\r\n"
			+ "INNER JOIN fieldworker f on a.fw_uuid=f.fw_uuid\r\n"
			+ "where a.status=2 AND a.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	List<Pregnancyobservation> findRej();
	
	@Query(nativeQuery = true, value ="SELECT	a.uuid,concat(b.firstName,' ',b.lastName,' ',COALESCE(otherName, '')) as individual_uuid, "
			+ "ageOfPregFromPregNotes, 	anc_visits,anteNatalClinic,attend_you,attend_you_other,bnet_loc,bnet_loc_other,bnet_sou,"
			+ "bnet_sou_other, 	estimatedAgeOfPreg,expectedDeliveryDate,first_preg,first_rec,concat(f.firstName,' ',f.lastName) as edtime, "
			+ " healthfacility,how_many,a.insertDate, 	lastClinicVisitDate, 	medicineforpregnancy, 	outcome, outcome_date, \r\n"
			+ " own_bnet, 	pregnancyNumber, 	recordedDate, 	slp_bednet, 	trt_bednet, 	ttinjection,compno as visit_uuid, 	"
			+ " why_no, why_no_other, 	a.fw_uuid,dob as sttime, 	approveDate, 	`comment`, 	a.`status`, 	supervisor\r\n"
			+ "FROM pregnancyobservation a INNER JOIN individual b on a.individual_uuid=b.uuid\r\n"
			+ "INNER JOIN visit c on a.visit_uuid=c.uuid INNER JOIN location d on c.location_uuid=d.uuid\r\n"
			+ "INNER JOIN fieldworker f on a.fw_uuid=f.fw_uuid\r\n"
			+ "where a.`status`=3 AND a.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	List<Pregnancyobservation> findItems();
	

}

package org.arn.hdsscapture.repository;

import java.util.List;

import org.arn.hdsscapture.entity.Inmigration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface InmigrationRepository extends JpaRepository <Inmigration, String> {


	@Query(nativeQuery = true, value = "SELECT * FROM inmigration WHERE uuid = :uuid LIMIT 1")
    List<Inmigration> findByUuids(@Param("uuid") String uuid);
	
	@Query(nativeQuery = true, value = "SELECT a.* FROM inmigration a WHERE a.`status`=2 AND a.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
    List<Inmigration> rejected();
	
	
	@Query(nativeQuery = true, value ="SELECT a.uuid,concat(b.firstName,' ',b.lastName,' ',COALESCE(otherName, '')) as residency_uuid, a.`status`, `comment`, approveDate,b.dob as sttime,TIMESTAMPDIFF(year,dob,CURDATE()) as edtime, livestock_yn, food_yn, farm_other, farm, cash_yn, cash_crops, \r\n"
			+ "compno as visit_uuid,recordedDate,concat(f.firstName,' ',f.lastName) as fw_uuid,reason,origin,migType,livestock,last_other,last_occupa, \r\n"
			+ "a.insertDate, a.individual_uuid,a.reason_oth,food_crops,acres,supervisor\r\n"
			+ "FROM inmigration as a INNER JOIN individual b on a.individual_uuid=b.uuid\r\n"
			+ "INNER JOIN residency c on a.residency_uuid=c.uuid INNER JOIN location d on c.location_uuid=d.uuid \r\n"
			+ "INNER JOIN fieldworker f on a.fw_uuid=f.fw_uuid\r\n"
			+ "where a.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1) AND a.uuid = :uuid LIMIT 1")
	List<Inmigration> findByUuid(@Param("uuid") String uuid);

		
	@Query(nativeQuery = true, value ="SELECT a.uuid,concat(b.firstName,' ',b.lastName,' ',COALESCE(otherName, '')) as residency_uuid, a.`status`, `comment`, approveDate, a.sttime, a.edtime, livestock_yn, food_yn, farm_other, farm, cash_yn, cash_crops, \r\n"
			+ "compno as visit_uuid,recordedDate,concat(f.firstName,' ',f.lastName) as individual_uuid,reason,origin,migType,livestock,last_other,last_occupa, \r\n"
			+ "a.insertDate, a.fw_uuid,a.reason_oth,food_crops,acres,supervisor\r\n"
			+ "FROM inmigration as a INNER JOIN individual b on a.individual_uuid=b.uuid\r\n"
			+ "INNER JOIN residency c on a.residency_uuid=c.uuid INNER JOIN location d on c.location_uuid=d.uuid \r\n"
			+ "INNER JOIN fieldworker f on a.fw_uuid=f.fw_uuid\r\n"
			+ "where f.fw_uuid= :fw AND a.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	List<Inmigration> findImg(@Param("fw") String fw);
	
	@Query(nativeQuery = true, value ="SELECT a.uuid,concat(b.firstName,' ',b.lastName,' ',COALESCE(otherName, '')) as residency_uuid, a.`status`, `comment`, approveDate, a.sttime, a.edtime, livestock_yn, food_yn, farm_other, farm, cash_yn, cash_crops, \r\n"
			+ "compno as visit_uuid,recordedDate,concat(f.firstName,' ',f.lastName) as individual_uuid,reason,origin,migType,livestock,last_other,last_occupa, \r\n"
			+ "a.insertDate, a.fw_uuid,a.reason_oth,food_crops,acres,supervisor\r\n"
			+ "FROM inmigration as a INNER JOIN individual b on a.individual_uuid=b.uuid\r\n"
			+ "INNER JOIN residency c on a.residency_uuid=c.uuid INNER JOIN location d on c.location_uuid=d.uuid \r\n"
			+ "INNER JOIN fieldworker f on a.fw_uuid=f.fw_uuid\r\n"
			+ "where a.`status`=3 AND a.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	List<Inmigration> findImgs();
}

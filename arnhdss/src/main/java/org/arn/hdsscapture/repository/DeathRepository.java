package org.arn.hdsscapture.repository;

import java.util.List;

import org.arn.hdsscapture.entity.Death;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface DeathRepository extends JpaRepository <Death, String> {

	@Query(nativeQuery = true, value = "SELECT * FROM death WHERE uuid = :uuid LIMIT 1")
    List<Death> findByUuids(@Param("uuid") String uuid);
	
	@Query(nativeQuery = true, value = "SELECT a.* FROM death a WHERE a.`status`=2 AND a.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1) AND a.fw_uuid = :fw")
    List<Death> rejected(@Param("fw")String fw);
	
	
	@Query(nativeQuery = true, value ="SELECT a.uuid,concat(b.firstName,' ',b.lastName,' ',COALESCE(otherName, '')) as individual_uuid, deathCause,deathDate,deathPlace,concat(f.firstName,' ',f.lastName) as fw_uuid,a.insertDate,compno as visit_uuid,deathPlace_oth,\r\n"
			+ "TIMESTAMPDIFF(year,dob,deathDate) as edtime,dob as sttime,deathCause_oth,a.complete,	approveDate,`comment`,a.`status`,	supervisor\r\n"
			+ "FROM death a INNER JOIN individual b on a.individual_uuid=b.uuid\r\n"
			+ "INNER JOIN visit c on a.visit_uuid=c.uuid INNER JOIN location d on c.location_uuid=d.uuid\r\n"
			+ "INNER JOIN fieldworker f on a.fw_uuid=f.fw_uuid\r\n"
			+ "where a.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1) AND a.uuid = :uuid LIMIT 1")
	List<Death> findByUuid(@Param("uuid") String uuid);

		
	@Query(nativeQuery = true, value ="SELECT a.uuid,concat(b.firstName,' ',b.lastName,' ',COALESCE(otherName, '')) as individual_uuid, deathCause,deathDate,deathPlace,concat(f.firstName,' ',f.lastName) as edtime,a.insertDate,compno as visit_uuid,deathPlace_oth,\r\n"
			+ "a.complete,dob as sttime,	a.fw_uuid,	approveDate,`comment`,a.`status`,deathCause_oth,	supervisor\r\n"
			+ "FROM death a INNER JOIN individual b on a.individual_uuid=b.uuid\r\n"
			+ "INNER JOIN visit c on a.visit_uuid=c.uuid INNER JOIN location d on c.location_uuid=d.uuid\r\n"
			+ "INNER JOIN fieldworker f on a.fw_uuid=f.fw_uuid\r\n"
			+ "where a.fw_uuid= :fw AND a.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	List<Death> findItem(@Param("fw") String fw);
	
	@Query(nativeQuery = true, value ="SELECT a.uuid,concat(b.firstName,' ',b.lastName,' ',COALESCE(otherName, '')) as individual_uuid, deathCause,deathDate,deathPlace,concat(f.firstName,' ',f.lastName) as edtime,a.insertDate,compno as visit_uuid,deathPlace_oth,\r\n"
			+ "a.complete,dob as sttime,	a.fw_uuid,	approveDate,`comment`,a.`status`,deathCause_oth,	supervisor\r\n"
			+ "FROM death a INNER JOIN individual b on a.individual_uuid=b.uuid\r\n"
			+ "INNER JOIN visit c on a.visit_uuid=c.uuid INNER JOIN location d on c.location_uuid=d.uuid\r\n"
			+ "INNER JOIN fieldworker f on a.fw_uuid=f.fw_uuid\r\n"
			+ "where a.`status`= 3 AND a.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	List<Death> findItems();
	
	@Query(nativeQuery = true, value ="SELECT a.uuid,concat(b.firstName,' ',b.lastName,' ',COALESCE(otherName, '')) as individual_uuid, deathCause,deathDate,deathPlace,concat(f.firstName,' ',f.lastName) as edtime,a.insertDate,compno as visit_uuid,deathPlace_oth,\r\n"
			+ "a.complete,dob as sttime,	a.fw_uuid,	approveDate,`comment`,a.`status`,deathCause_oth,	supervisor\r\n"
			+ "FROM death a INNER JOIN individual b on a.individual_uuid=b.uuid\r\n"
			+ "INNER JOIN visit c on a.visit_uuid=c.uuid INNER JOIN location d on c.location_uuid=d.uuid\r\n"
			+ "INNER JOIN fieldworker f on a.fw_uuid=f.fw_uuid\r\n"
			+ "where a.`status`= 2 AND a.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	List<Death> findRej();

}

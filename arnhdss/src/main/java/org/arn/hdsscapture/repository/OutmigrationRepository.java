package org.arn.hdsscapture.repository;

import java.util.List;

import org.arn.hdsscapture.entity.Outmigration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OutmigrationRepository extends JpaRepository <Outmigration, String> {

	@Query(nativeQuery = true, value = "SELECT * FROM outmigration WHERE uuid = :uuid LIMIT 1")
    List<Outmigration> findByUuids(@Param("uuid") String uuid);
	
	@Query(nativeQuery = true, value = "SELECT a.* FROM outmigration a WHERE a.`status`=2 AND a.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
    List<Outmigration> rejected();
	
	
	@Query(nativeQuery = true, value ="SELECT a.uuid,residency_uuid, destination,concat(f.firstName,' ',f.lastName) as fw_uuid,"
			+ "concat(b.firstName,' ',b.lastName,' ',COALESCE(otherName, '')) as individual_uuid,a.insertDate,compno as visit_uuid,"
			+ "reason,reason_oth,recordedDate,TIMESTAMPDIFF(year,dob,CURDATE()) as edtime,dob as sttime,complete,approveDate,`comment`,a.`status`,supervisor FROM outmigration a "
			+ "INNER JOIN individual b on a.individual_uuid=b.uuid\r\n"
			+ "INNER JOIN residency c on a.residency_uuid=c.uuid INNER JOIN location d on c.location_uuid=d.uuid \r\n"
			+ "INNER JOIN fieldworker f on a.fw_uuid=f.fw_uuid\r\n"
			+ "where a.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1) AND a.uuid = :uuid LIMIT 1")
	List<Outmigration> findByUuid(@Param("uuid") String uuid);

		
	@Query(nativeQuery = true, value ="SELECT a.uuid,a.fw_uuid, destination,concat(f.firstName,' ',f.lastName) as sttime,"
			+ "a.insertDate,concat(b.firstName,' ',b.lastName,' ',COALESCE(otherName, '')) as edtime,a.individual_uuid,compno as visit_uuid,"
			+ "reason,reason_oth,recordedDate,a.residency_uuid,complete,approveDate,`comment`,a.`status`,supervisor FROM outmigration a "
			+ "INNER JOIN individual b on a.individual_uuid=b.uuid\r\n"
			+ "INNER JOIN residency c on a.residency_uuid=c.uuid INNER JOIN location d on c.location_uuid=d.uuid \r\n"
			+ "INNER JOIN fieldworker f on a.fw_uuid=f.fw_uuid \r\n"
			+ "where a.fw_uuid= :fw AND a.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	List<Outmigration> findItem(@Param("fw") String fw);
	
	@Query(nativeQuery = true, value ="SELECT a.uuid,a.fw_uuid, destination,concat(f.firstName,' ',f.lastName) as sttime,"
			+ "a.insertDate,concat(b.firstName,' ',b.lastName,' ',COALESCE(otherName, '')) as edtime,a.individual_uuid,compno as visit_uuid,"
			+ "reason,reason_oth,recordedDate,a.residency_uuid,complete,approveDate,`comment`,a.`status`,supervisor FROM outmigration a "
			+ "INNER JOIN individual b on a.individual_uuid=b.uuid\r\n"
			+ "INNER JOIN residency c on a.residency_uuid=c.uuid INNER JOIN location d on c.location_uuid=d.uuid \r\n"
			+ "INNER JOIN fieldworker f on a.fw_uuid=f.fw_uuid \r\n"
			+ "where a.status=2 AND a.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	List<Outmigration> findRej();
	
	@Query(nativeQuery = true, value ="SELECT a.uuid,a.fw_uuid, destination,concat(f.firstName,' ',f.lastName) as sttime,"
			+ "a.insertDate,concat(b.firstName,' ',b.lastName,' ',COALESCE(otherName, '')) as edtime,a.individual_uuid,compno as visit_uuid,"
			+ "reason,reason_oth,recordedDate,a.residency_uuid,complete,approveDate,`comment`,a.`status`,supervisor FROM outmigration a "
			+ "INNER JOIN individual b on a.individual_uuid=b.uuid\r\n"
			+ "INNER JOIN residency c on a.residency_uuid=c.uuid INNER JOIN location d on c.location_uuid=d.uuid \r\n"
			+ "INNER JOIN fieldworker f on a.fw_uuid=f.fw_uuid \r\n"
			+ "where a.`status`=3 AND a.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	List<Outmigration> findItems();

}

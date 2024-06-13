package org.arn.hdsscapture.repository;

import java.util.List;

import org.arn.hdsscapture.entity.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RelationshipRepository extends JpaRepository <Relationship, String> {

	@Query(nativeQuery = true, value = "SELECT * from relationship WHERE endType=1 LIMIT ?1 OFFSET ?2")
	List<Relationship> findRelationship(int pageSize, int offset);
	
	@Query(nativeQuery = true, value = "SELECT a.* FROM relationship a WHERE a.`status`=2 AND a.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
    List<Relationship> rejected();
	
	
	@Query(nativeQuery = true, value = "SELECT * FROM relationship WHERE uuid = :uuid LIMIT 1")
    List<Relationship> findByUuids(@Param("uuid") String uuid);
	
	
	@Query(nativeQuery = true, value ="SELECT a.uuid,aIsToB,a.endDate,a.endType,concat(f.firstName,' ',f.lastName) as fw_uuid,concat(b.firstName,' ',b.lastName,' ',COALESCE(otherName, '')) as individualA_uuid,TIMESTAMPDIFF(year,dob,CURDATE()) as individualB_uuid,a.insertDate,lcow,mar, \r\n"
			+ "mrank,nchdm,nwive,polygamous,a.startDate,tnbch,compno as edtime,dob as sttime,approveDate,`comment`,a.`status`,supervisor\r\n"
			+ "FROM relationship a INNER JOIN individual b on a.individualA_uuid=b.uuid\r\n"
			+ "INNER JOIN residency c on b.uuid=c.individual_uuid INNER JOIN location d on c.location_uuid=d.uuid\r\n"
			+ "INNER JOIN fieldworker f on a.fw_uuid=f.fw_uuid\r\n"
			+ "where a.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1) AND c.endType=1 AND a.uuid = :uuid LIMIT 1")
	List<Relationship> findByUuid(@Param("uuid") String uuid);

		
	@Query(nativeQuery = true, value ="SELECT a.uuid,aIsToB,a.endDate,a.endType,concat(f.firstName,' ',f.lastName) as fw_uuid, "
			+ "concat(b.firstName,' ',b.lastName,' ',COALESCE(otherName, '')) as individualA_uuid,individualB_uuid,a.insertDate,lcow,mar, \r\n"
			+ "mrank,nchdm,nwive,polygamous,a.startDate,tnbch,compno as edtime,dob as sttime,approveDate,`comment`,a.`status`,supervisor\r\n"
			+ "FROM relationship a INNER JOIN individual b on a.individualA_uuid=b.uuid\r\n"
			+ "INNER JOIN residency c on b.uuid=c.individual_uuid INNER JOIN location d on c.location_uuid=d.uuid\r\n"
			+ "INNER JOIN fieldworker f on a.fw_uuid=f.fw_uuid\r\n"
			+ "where a.fw_uuid= :fw AND a.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1) AND c.endType=1")
	List<Relationship> findItem(@Param("fw") String fw);
	
	@Query(nativeQuery = true, value ="SELECT a.uuid,aIsToB,a.endDate,a.endType,concat(f.firstName,' ',f.lastName) as fw_uuid, "
			+ "concat(b.firstName,' ',b.lastName,' ',COALESCE(otherName, '')) as individualA_uuid,individualB_uuid,a.insertDate,lcow,mar, \r\n"
			+ "mrank,nchdm,nwive,polygamous,a.startDate,tnbch,compno as edtime,dob as sttime,approveDate,`comment`,a.`status`,supervisor\r\n"
			+ "FROM relationship a INNER JOIN individual b on a.individualA_uuid=b.uuid\r\n"
			+ "INNER JOIN residency c on b.uuid=c.individual_uuid INNER JOIN location d on c.location_uuid=d.uuid\r\n"
			+ "INNER JOIN fieldworker f on a.fw_uuid=f.fw_uuid\r\n"
			+ "where a.`status`=3 AND a.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1) AND c.endType=1")
	List<Relationship> findItems();
}

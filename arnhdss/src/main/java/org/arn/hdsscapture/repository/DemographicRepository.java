package org.arn.hdsscapture.repository;

import java.util.List;

import org.arn.hdsscapture.entity.Demographic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface DemographicRepository extends JpaRepository <Demographic, String> {

	
	@Query(nativeQuery = true, value = "SELECT a.* FROM demographic a LEFT JOIN "
			+ "death b ON a.individual_uuid=b.individual_uuid where b.individual_uuid is null LIMIT ?1 OFFSET ?2")
	List<Demographic> findDemographic(int pageSize, int offset);
	
	@Query(nativeQuery = true, value = "SELECT a.* FROM demographic a WHERE a.`status`=2 AND a.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1) AND a.fw_uuid = :fw")
    List<Demographic> rejected(@Param("fw")String fw);
	
	
	@Query(nativeQuery = true, value = "SELECT * FROM demographic WHERE individual_uuid = :uuid LIMIT 1")
    List<Demographic> findByUuids(@Param("uuid") String uuid);
	
	
	@Query(nativeQuery = true, value ="SELECT a.individual_uuid,denomination,akan_tribe,comp_yrs,education,concat(f.firstName,' ',f.lastName) as fw_uuid,a.insertDate,marital,occupation,occupation_oth,TIMESTAMPDIFF(year,dob,CURDATE()) as phone1,concat(b.firstName,' ',b.lastName,' ',COALESCE(otherName, '')) as phone2,religion,religion_oth,tribe,tribe_oth,compno as edtime,dob as sttime,approveDate,`comment`,a.`status`,supervisor\r\n"
			+ "FROM demographic a INNER JOIN individual b on a.individual_uuid=b.uuid\r\n"
			+ "INNER JOIN residency c on b.uuid=c.individual_uuid INNER JOIN location d on c.location_uuid=d.uuid\r\n"
			+ "INNER JOIN fieldworker f on a.fw_uuid=f.fw_uuid\r\n"
			+ "where a.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1) AND endType=1 AND a.individual_uuid = :uuid LIMIT 1")
	List<Demographic> findByUuid(@Param("uuid") String uuid);

		
	@Query(nativeQuery = true, value ="SELECT a.individual_uuid,denomination,akan_tribe,comp_yrs,education,concat(f.firstName,' ',f.lastName) as fw_uuid,"
			+ "a.insertDate,marital,occupation,occupation_oth,phone1,concat(b.firstName,' ',b.lastName,' ',COALESCE(otherName, '')) as phone2,"
			+ " religion,religion_oth,tribe,tribe_oth,compno as edtime,dob as sttime,approveDate,`comment`,a.`status`,supervisor\r\n"
			+ "FROM demographic a INNER JOIN individual b on a.individual_uuid=b.uuid\r\n"
			+ "INNER JOIN residency c on b.uuid=c.individual_uuid INNER JOIN location d on c.location_uuid=d.uuid\r\n"
			+ "INNER JOIN fieldworker f on a.fw_uuid=f.fw_uuid\r\n"
			+ "where a.fw_uuid= :fw AND a.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1) AND endType=1")
	List<Demographic> findItem(@Param("fw") String fw);
	
	@Query(nativeQuery = true, value ="SELECT a.individual_uuid,denomination,akan_tribe,comp_yrs,education,concat(f.firstName,' ',f.lastName) as fw_uuid,"
			+ "a.insertDate,marital,occupation,occupation_oth,phone1,concat(b.firstName,' ',b.lastName,' ',COALESCE(otherName, '')) as phone2,"
			+ " religion,religion_oth,tribe,tribe_oth,compno as edtime,dob as sttime,approveDate,`comment`,a.`status`,supervisor\r\n"
			+ "FROM demographic a INNER JOIN individual b on a.individual_uuid=b.uuid\r\n"
			+ "INNER JOIN residency c on b.uuid=c.individual_uuid INNER JOIN location d on c.location_uuid=d.uuid\r\n"
			+ "INNER JOIN fieldworker f on a.fw_uuid=f.fw_uuid\r\n"
			+ "where a.status=2 AND a.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1) AND endType=1")
	List<Demographic> findRej();
	
	@Query(nativeQuery = true, value ="SELECT a.individual_uuid,denomination,akan_tribe,comp_yrs,education,concat(f.firstName,' ',f.lastName) as fw_uuid,"
			+ "a.insertDate,marital,occupation,occupation_oth,phone1,concat(b.firstName,' ',b.lastName,' ',COALESCE(otherName, '')) as phone2,"
			+ " religion,religion_oth,tribe,tribe_oth,compno as edtime,dob as sttime,approveDate,`comment`,a.`status`,supervisor\r\n"
			+ "FROM demographic a INNER JOIN individual b on a.individual_uuid=b.uuid\r\n"
			+ "INNER JOIN residency c on b.uuid=c.individual_uuid INNER JOIN location d on c.location_uuid=d.uuid\r\n"
			+ "INNER JOIN fieldworker f on a.fw_uuid=f.fw_uuid\r\n"
			+ "where a.`status`= 3 AND a.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1) AND endType=1")
	List<Demographic> findItems();


}

package org.arn.hdsscapture.query;

import java.util.Date;
import java.util.List;

import org.arn.hdsscapture.entity.Fieldworker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QueryRepository extends JpaRepository <Fieldworker, String> {
	
	@Query(nativeQuery = true, value = "SELECT a.uuid as id,a.extId as id1, groupName as id4,dob as id2,a.insertDate as id3,b.extId as id8, b.extId AS id5,null as id9, compno as id6,concat(x.firstName,' ',x.lastName) as id7\r\n"
			+ "FROM socialgroup a INNER JOIN individual b ON a.individual_uuid = b.uuid\r\n"
			+ "INNER JOIN fieldworker x on a.fw_uuid=x.fw_uuid\r\n"
			+ "LEFT JOIN (SELECT *, ROW_NUMBER() OVER (PARTITION BY individual_uuid ORDER BY startDate DESC) AS rn\r\n"
			+ "FROM residency) c ON b.uuid = c.individual_uuid AND c.rn = 1\r\n"
			+ "INNER JOIN location d ON c.location_uuid = d.uuid WHERE TIMESTAMPDIFF(YEAR, dob, CURDATE()) < (select hoh_age from settings)\r\n"
			+ "AND a.extId!= :query")
	List<Queries> Minor(@Param("query") String query);
	
	//Mysql 5
//	@Query(nativeQuery = true, value = "SELECT a.uuid as id,a.extId as id1, groupName as id4,dob as id2,a.insertDate as id3,\r\n"
//			+ "b.extId as id8, b.extId AS id5, compno as id6,CONCAT(x.firstName, ' ', x.lastName) as id7\r\n"
//			+ "FROM socialgroup a INNER JOIN individual b ON a.individual_uuid = b.uuid\r\n"
//			+ "INNER JOIN fieldworker x ON a.fw_uuid = x.fw_uuid LEFT JOIN \r\n"
//			+ "residency c ON b.uuid = c.individual_uuid AND c.startDate = (SELECT MAX(startDate) \r\n"
//			+ "FROM residency WHERE individual_uuid = b.uuid) INNER JOIN \r\n"
//			+ "location d ON c.location_uuid = d.uuid WHERE \r\n"
//			+ "TIMESTAMPDIFF(YEAR, dob, CURDATE()) < (SELECT hoh_age FROM settings)\r\n"
//			+ "AND a.extId!= :query")
//	List<Queries> Minor(@Param("query") String query);
	
	
	@Query(nativeQuery = true, value = "SELECT a.uuid as id,a.extId as id1,concat(a.firstName,' ',a.lastName)as id4,null as id9,"
			+ "gender as id5,dob as id3,a.insertDate as id2,concat(c.firstName,' ',c.lastName)as id6,a.extId as id7,a.extId as id8 from individual a LEFT JOIN residency b on a.uuid=b.individual_uuid\r\n"
			+ "INNER JOIN fieldworker c on a.fw_uuid=c.fw_uuid\r\n"
			+ "where b.individual_uuid IS NULL AND a.uuid!='UNK'\r\n"
			+ "AND a.extId!= :query")
	List<Queries> Nomem(@Param("query") String query);
	
	@Query(nativeQuery = true, value = "SELECT a.uuid as id,a.extId as id1,concat(a.firstName,' ',a.lastName)as id4,null as id9,dob as id2,startDate as id3,a.firstName as id5,"
			+ "a.firstName as id6,a.firstName as id7,a.firstName as id8 from "
			+ "individual a INNER JOIN residency b on a.uuid=b.individual_uuid\r\n"
			+ "WHERE a.dob>startDate\r\n"
			+ "AND a.extId!= :query")
	List<Queries> Dob(@Param("query") String query);
	
	@Query(nativeQuery = true, value = "SELECT uuid as id,extId as id8,individual_uuid as id1, name as id4, startDate as id2,null as id9, endType as id5, dob as id3, compno as id6,fw as id7\r\n"
			+ "FROM (SELECT a.uuid,extId,individual_uuid, startDate, endDate, \r\n"
			+ "CASE WHEN endType = 1 THEN 'Active' END AS endType, \r\n"
			+ "dob, compno, concat(b.firstName,' ',b.lastName)name,concat(d.firstName,' ',d.lastName)fw,\r\n"
			+ "COUNT(*) OVER(PARTITION BY individual_uuid) AS duplicates_count\r\n"
			+ "FROM residency a INNER JOIN individual b ON a.individual_uuid = b.uuid\r\n"
			+ "INNER JOIN location c ON a.location_uuid = c.uuid\r\n"
			+ "INNER JOIN fieldworker d on a.fw_uuid=d.fw_uuid\r\n"
			+ "WHERE endType = 1\r\n"
			+ ") AS duplicates WHERE duplicates_count > 1\r\n"
			+ "AND extId!= :query")
	List<Queries> Dup(@Param("query") String query);
	
	//Mysql 5
//	@Query(nativeQuery = true, value = "SELECT uuid as id,extId as id8,individual_uuid as id1,name as id4,startDate as id2,endType AS id5,\r\n"
//			+ "dob as id3,compno as id6,fw as id7\r\n"
//			+ "FROM (SELECT a.uuid,extId,individual_uuid,startDate,endDate,CASE WHEN endType = 1 THEN 'Active' END AS endType,\r\n"
//			+ "dob,compno,CONCAT(b.firstName, ' ', b.lastName) as name,CONCAT(d.firstName, ' ', d.lastName) as fw,\r\n"
//			+ "(SELECT COUNT(*) FROM residency WHERE individual_uuid = a.individual_uuid AND endType = 1) AS duplicates_count\r\n"
//			+ "FROM residency a INNER JOIN individual b ON a.individual_uuid = b.uuid\r\n"
//			+ "INNER JOIN location c ON a.location_uuid = c.uuid INNER JOIN \r\n"
//			+ "fieldworker d ON a.fw_uuid = d.fw_uuid WHERE endType = 1) AS duplicates WHERE duplicates_count > 1\r\n"
//			+ "AND extId != :query")
//	List<Queries> Dup(@Param("query") String query);
	
	@Query(nativeQuery = true, value = "SELECT uuid as id,extId as id1,individual_uuid as id4,startDate as id2,endDate as id3,null as id9,name as id5,\r\n"
			+ "prev_endDate as id6,firstName as id7,firstName as id8 \r\n"
			+ "FROM (SELECT firstName,a.uuid,extId,individual_uuid, startDate, endDate,concat(b.firstName,' ',b.lastName)name,\r\n"
			+ "LAG(endDate) OVER (PARTITION BY individual_uuid ORDER BY startDate) AS prev_endDate\r\n"
			+ "FROM residency a INNER JOIN individual b ON a.individual_uuid = b.uuid) AS subquery WHERE startDate < prev_endDate\r\n"
			+ "AND extId!= :query")
	List<Queries> Lag(@Param("query") String query);
	
//	@Query(nativeQuery = true, value = "SELECT uuid as id,extId as id1,individual_uuid as id4,startDate as id2,endDate as id3,\r\n"
//			+ "name as id5,prev_endDate as id6,name as id7,name as id8\r\n"
//			+ "FROM (SELECT a.uuid,extId,individual_uuid,startDate,endDate,CONCAT(b.firstName, ' ', b.lastName) as name,\r\n"
//			+ "(SELECT MAX(endDate) FROM residency AS r2 WHERE r2.individual_uuid = a.individual_uuid \r\n"
//			+ "AND r2.startDate < a.startDate) AS prev_endDate FROM \r\n"
//			+ "residency a INNER JOIN individual b ON a.individual_uuid = b.uuid\r\n"
//			+ ") AS subquery WHERE startDate < prev_endDate\r\n"
//			+ "AND extId != :query")
//	List<Queries> Lag(@Param("query") String query);
	
	@Query(nativeQuery = true, value = "SELECT a.uuid as id,groupName as id1,d.extId as id4,compno as id5,a.insertDate as id2,null as id9,a.dob as id3,a.firstName as id6,a.firstName as id7,a.firstName as id8 FROM individual AS a INNER JOIN residency AS b ON a.uuid = b.individual_uuid\r\n"
			+ "INNER JOIN location c on b.location_uuid=c.uuid INNER JOIN socialgroup d on b.socialgroup_uuid=d.uuid\r\n"
			+ "LEFT JOIN `duplicate` z on a.uuid=z.dup_uuid LEFT JOIN `duplicate` x on a.uuid=x.dup1_uuid \r\n"
			+ "LEFT JOIN `duplicate` y on a.uuid=y.dup2_uuid WHERE b.endType = 1 AND z.dup_uuid is null AND x.dup1_uuid is null \r\n"
			+ "AND y.dup2_uuid is null AND b.socialgroup_uuid IN (SELECT socialgroup_uuid FROM residency AS b2\r\n"
			+ "INNER JOIN individual AS a2 ON b2.individual_uuid = a2.uuid WHERE b2.endType = 1\r\n"
			+ "GROUP BY socialgroup_uuid HAVING MAX(TIMESTAMPDIFF(YEAR, a2.dob, CURDATE())) < (select hoh_age from settings)) AND a.extId!= :query GROUP BY d.extId")
	List<Queries> Minors(@Param("query") String query);
	
	@Query(nativeQuery = true, value = "SELECT a.extId as id,concat(a.firstName,' ',a.lastName)id1,outcomeDate as id2,null as id9,b.insertDate as id3,concat(d.firstName,' ',d.lastName)id4,NULL as id5,NULL as id6,NULL as id7,NULL as id8 from individual a INNER JOIN pregnancyoutcome b ON a.uuid=b.mother_uuid\r\n"
			+ "LEFT JOIN outcome c on b.uuid=c.preg_uuid \r\n"
			+ "INNER JOIN fieldworker d on b.fw_uuid=d.fw_uuid WHERE preg_uuid IS NULL \r\n"
			+ "AND a.extId!= :query")
	List<Queries> Outcome(@Param("query") String query);
	
	@Query(nativeQuery = true, value = "SELECT b.extId as id,groupName as id1,f.compno as id5,a.insertDate as id2,deathDate as id3,null as id9,concat(e.firstName,' ',e.lastName)id4,NULL as id6,NULL as id7,NULL as id8 FROM death as a INNER JOIN socialgroup as b ON a.individual_uuid=b.individual_uuid\r\n"
			+ "INNER JOIN residency as c on b.uuid=c.socialgroup_uuid\r\n"
			+ "INNER JOIN individual as d on c.individual_uuid=d.uuid\r\n"
			+ "INNER JOIN location f on c.location_uuid=f.uuid\r\n"
			+ "INNER JOIN fieldworker e on a.fw_uuid=e.fw_uuid\r\n"
			+ " WHERE c.endType=1 AND b.extId!= :query group by b.extId")
	List<Queries> DthHOH(@Param("query") String query);
	
	@Query(nativeQuery = true, value = "SELECT b.name AS id, COUNT(DISTINCT a.uuid) AS id1,\r\n"
			+ "COUNT(DISTINCT CASE WHEN c.insertDate > (SELECT startDate FROM round ORDER BY roundNumber DESC LIMIT 1) THEN c.compno END) AS id4,\r\n"
			+ "ROUND(IFNULL(COUNT(DISTINCT CASE WHEN c.insertDate > (SELECT startDate FROM round ORDER BY roundNumber DESC LIMIT 1) THEN c.compno END) / COUNT(DISTINCT a.uuid) * 100, 0), 2) AS id5,\r\n"
			+ "COUNT(DISTINCT CASE WHEN e.endType = 1 THEN e.socialgroup_uuid END) AS id6,\r\n"
			+ "COUNT(DISTINCT f.extId) AS id7,null as id2,null as id3,\r\n"
			+ "ROUND(IFNULL(COUNT(DISTINCT f.extId) / NULLIF(COUNT(DISTINCT CASE WHEN e.endType = 1 THEN e.socialgroup_uuid END), 0) * 100, 0), 2) AS id8,\r\n"
			+ "CONCAT(d.firstName, ' ', d.lastName) AS id9\r\n"
			+ "FROM location a INNER JOIN locationhierarchy b ON a.locationLevel_uuid = b.uuid\r\n"
			+ "LEFT JOIN listing c ON a.compno = c.compno LEFT JOIN fieldworker d ON b.fw_name = d.username \r\n"
			+ "LEFT JOIN residency e on a.uuid=e.location_uuid\r\n"
			+ "LEFT JOIN     (SELECT * FROM visit WHERE insertDate > (SELECT startDate FROM round ORDER BY roundNumber DESC LIMIT 1)) f ON a.uuid = f.location_uuid\r\n"
			+ "WHERE b.name!= :query GROUP BY b.uuid ORDER BY b.name")
	List<Queries> Compvisit(@Param("query") String query);
	
	@Query(nativeQuery = true, value = "SELECT a.individual_uuid as id,concat(b.firstName,' ',b.lastName)as id1,username as id4,count(a.individual_uuid) as id5,\r\n"
			+ "max(a.insertDate) as id2,max(date(a.insertDate)) as id3,e.name as id6,SUM(CASE WHEN a.`status`=1 THEN 1 ELSE 0 END) AS id7,\r\n"
			+ "SUM(CASE WHEN a.`status`=2 THEN 1 ELSE 0 END) AS id8,count(distinct a.location_uuid) as id9\r\n"
			+ "FROM registry a INNER JOIN fieldworker b ON a.fw_uuid=b.fw_uuid\r\n"
			+ "INNER JOIN location  d on  a.location_uuid=d.uuid\r\n"
			+ "inner JOIN locationhierarchy e on d.locationLevel_uuid=e.uuid\r\n"
			+ "WHERE a.insertDate BETWEEN :startDate AND :endDate GROUP BY id1,id6 ORDER BY id1,id5")
	List<Queries> registry(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
}

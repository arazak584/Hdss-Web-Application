package org.arn.hdsscapture.query;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QueryRepository extends JpaRepository <Queries, String> {
	
	@Query(nativeQuery = true, value = "SELECT a.uuid as id,a.extId as id1, groupName as id4,dob as id2,a.insertDate as id3,b.extId as id8, b.extId AS id5, compno as id6,concat(x.firstName,' ',x.lastName) as id7\r\n"
			+ "FROM socialgroup a INNER JOIN individual b ON a.individual_uuid = b.uuid\r\n"
			+ "INNER JOIN fieldworker x on a.fw_uuid=x.fw_uuid\r\n"
			+ "LEFT JOIN (SELECT *, ROW_NUMBER() OVER (PARTITION BY individual_uuid ORDER BY startDate DESC) AS rn\r\n"
			+ "FROM residency) c ON b.uuid = c.individual_uuid AND c.rn = 1\r\n"
			+ "INNER JOIN location d ON c.location_uuid = d.uuid WHERE TIMESTAMPDIFF(YEAR, dob, CURDATE()) < (select hoh_age from settings)\r\n"
			+ "AND a.extId!= :query")
	List<Queries> Minor(@Param("query") String query);
	
	@Query(nativeQuery = true, value = "SELECT a.uuid as id,a.extId as id1,concat(a.firstName,' ',a.lastName)as id4,"
			+ "gender as id5,dob as id3,a.insertDate as id2,concat(c.firstName,' ',c.lastName)as id6,a.extId as id7,a.extId as id8 from individual a LEFT JOIN residency b on a.uuid=b.individual_uuid\r\n"
			+ "INNER JOIN fieldworker c on a.fw_uuid=c.fw_uuid\r\n"
			+ "where b.individual_uuid IS NULL AND a.uuid!='UNK'\r\n"
			+ "AND a.extId!= :query")
	List<Queries> Nomem(@Param("query") String query);
	
	@Query(nativeQuery = true, value = "SELECT a.uuid as id,a.extId as id1,concat(a.firstName,' ',a.lastName)as id4,dob as id2,startDate as id3,a.firstName as id5,"
			+ "a.firstName as id6,a.firstName as id7,a.firstName as id8 from "
			+ "individual a INNER JOIN residency b on a.uuid=b.individual_uuid\r\n"
			+ "WHERE a.dob>startDate\r\n"
			+ "AND a.extId!= :query")
	List<Queries> Dob(@Param("query") String query);
	
	@Query(nativeQuery = true, value = "SELECT uuid as id,extId as id8,individual_uuid as id1, name as id4, startDate as id2, endType as id5, dob as id3, compno as id6,fw as id7\r\n"
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
	
	@Query(nativeQuery = true, value = "SELECT uuid as id,extId as id1,individual_uuid as id4,startDate as id2,endDate as id3,name as id5,\r\n"
			+ "prev_endDate as id6,firstName as id7,firstName as id8 \r\n"
			+ "FROM (SELECT firstName,a.uuid,extId,individual_uuid, startDate, endDate,concat(b.firstName,' ',b.lastName)name,\r\n"
			+ "LAG(endDate) OVER (PARTITION BY individual_uuid ORDER BY startDate) AS prev_endDate\r\n"
			+ "FROM residency a INNER JOIN individual b ON a.individual_uuid = b.uuid) AS subquery WHERE startDate < prev_endDate\r\n"
			+ "AND extId!= :query")
	List<Queries> Lag(@Param("query") String query);
	
	@Query(nativeQuery = true, value = "SELECT a.uuid as id,groupName as id1,d.extId as id4,compno as id5,a.insertDate as id2,a.dob as id3,a.firstName as id6,a.firstName as id7,a.firstName as id8 FROM individual AS a INNER JOIN residency AS b ON a.uuid = b.individual_uuid\r\n"
			+ "INNER JOIN location c on b.location_uuid=c.uuid INNER JOIN socialgroup d on b.socialgroup_uuid=d.uuid\r\n"
			+ "WHERE b.endType = 1 AND b.socialgroup_uuid IN ( SELECT socialgroup_uuid FROM residency AS b2\r\n"
			+ "INNER JOIN individual AS a2 ON b2.individual_uuid = a2.uuid WHERE b2.endType = 1\r\n"
			+ "GROUP BY socialgroup_uuid HAVING MAX(TIMESTAMPDIFF(YEAR, a2.dob, CURDATE())) < (select hoh_age from settings))\r\n"
			+ "AND a.extId!= :query")
	List<Queries> Minors(@Param("query") String query);
	
	@Query(nativeQuery = true, value = "SELECT a.extId as id,concat(a.firstName,' ',a.lastName)id1,outcomeDate as id2,b.insertDate as id3,concat(d.firstName,' ',d.lastName)id4,NULL as id5,NULL as id6,NULL as id7,NULL as id8 from individual a INNER JOIN pregnancyoutcome b ON a.uuid=b.mother_uuid\r\n"
			+ "LEFT JOIN outcome c on b.uuid=c.preg_uuid \r\n"
			+ "INNER JOIN fieldworker d on b.fw_uuid=d.fw_uuid WHERE preg_uuid IS NULL \r\n"
			+ "AND a.extId!= :query")
	List<Queries> Outcome(@Param("query") String query);
	
	
}

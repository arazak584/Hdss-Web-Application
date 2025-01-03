package org.arn.hdsscapture.views;

import java.util.List;

import org.arn.hdsscapture.entity.Socialgroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ActiveHouseholdRepository extends JpaRepository <Socialgroup, String> {

	
	@Query(nativeQuery = true, value = "SELECT * from acthoh WHERE village= :village  ORDER BY subvillage")
	List<ActiveHouseholds> Reportd(@Param("village") String village);
	
	@Query(nativeQuery = true, value = "SELECT DISTINCT name as village FROM locationhierarchy Where level_uuid='hierarchyLevelId5' ORDER BY name")
	List<String> villages();
	
	@Query(nativeQuery = true, value = "SELECT DISTINCT name as village FROM locationhierarchy Where level_uuid='hierarchyLevelId6' ORDER BY name")
	List<String> subvillages();
	
	@Query(nativeQuery = true, value = "SELECT * from acthoh")
	List<ActiveHouseholds> Reportsd();
	
	@Query(nativeQuery = true, value = "SELECT h.name as District,e.extId as villcode,f.name as village,e.name as subvillage,\r\n"
			+ "count(DISTINCT a.socialgroup_uuid)as households,\r\n"
			+ "count(DISTINCT a.location_uuid)as compounds,count(DISTINCT a.uuid)as pop\r\n"
			+ "from residency a inner join socialgroup b on a.socialgroup_uuid=b.uuid\r\n"
			+ "inner join location d on a.location_uuid=d.uuid\r\n"
			+ "left join locationhierarchy e on d.locationLevel_uuid=e.uuid\r\n"
			+ "left join locationhierarchy f on e.parent_uuid=f.uuid\r\n"
			+ "left join locationhierarchy g on f.parent_uuid=g.uuid\r\n"
			+ "left join locationhierarchy h on g.parent_uuid=h.uuid\r\n"
			+ "where a.endType=1\r\n"
			+ "group by e.name\r\n"
			+ "order by e.name;")
	List<ActiveHouseholds> Reports();
	
	@Query(nativeQuery = true, value = "SELECT h.name as District,e.extId as villcode,f.name as village,e.name as subvillage,\r\n"
			+ "count(DISTINCT a.socialgroup_uuid)as households,\r\n"
			+ "count(DISTINCT a.location_uuid)as compounds,count(DISTINCT a.uuid)as pop\r\n"
			+ "from residency a inner join socialgroup b on a.socialgroup_uuid=b.uuid\r\n"
			+ "inner join location d on a.location_uuid=d.uuid\r\n"
			+ "left join locationhierarchy e on d.locationLevel_uuid=e.uuid\r\n"
			+ "left join locationhierarchy f on e.parent_uuid=f.uuid\r\n"
			+ "left join locationhierarchy g on f.parent_uuid=g.uuid\r\n"
			+ "left join locationhierarchy h on g.parent_uuid=h.uuid\r\n"
			+ "where a.endType=1 AND f.name= :village \r\n"
			+ "group by e.name\r\n"
			+ "order by e.name;")
	List<ActiveHouseholds> Report(@Param("village") String village);
	
	@Query(nativeQuery = true, value = "SELECT a.compno as villcode,a.locationName as District,longitude as households,latitude as compounds,b.name as subvillage,CASE when a.`status`=1 then 'Active' \r\n"
			+ "when a.`status`=2 then 'Commercial' when a.`status`=3 then 'Could not locate' when a.`status`=4 then 'Deserted/Broken' \r\n"
			+ "when a.`status`=5 then 'Incomplete' \r\n"
			+ "when a.`status`=6 then 'Unoccupied' else 'Unknown' end as village,null as pop from location a\r\n"
			+ "inner join locationhierarchy b on a.locationLevel_uuid=b.uuid left join openhds.locationhierarchy c on b.parent_uuid=c.uuid\r\n"
			+ "left join listing d on a.compno=d.compno where d.compno is null\r\n"
			+ "AND b.name= :village")
	List<ActiveHouseholds> Unvisited(@Param("village") String village);
	
	@Query(nativeQuery = true, value = "SELECT a.compno as villcode,a.locationName as District,longitude as households,latitude as compounds,b.name as subvillage,CASE when a.`status`=1 then 'Active' \r\n"
			+ "when a.`status`=2 then 'Commercial' when a.`status`=3 then 'Could not locate' when a.`status`=4 then 'Deserted/Broken' \r\n"
			+ "when a.`status`=5 then 'Incomplete' \r\n"
			+ "when a.`status`=6 then 'Unoccupied' else 'Unknown' end as village from location a\r\n"
			+ "inner join locationhierarchy b on a.locationLevel_uuid=b.uuid left join openhds.locationhierarchy c on b.parent_uuid=c.uuid\r\n"
			+ "left join listing d on a.compno=d.compno where d.compno is null\r\n")
	List<ActiveHouseholds> Unvisit();
	
	
	@Query(nativeQuery = true, value = "SELECT b.name AS villcode, COUNT(DISTINCT a.uuid) AS households,CONCAT(d.firstName, ' ', d.lastName) AS District,\r\n"
			+ "COUNT(DISTINCT CASE WHEN c.insertDate > (SELECT startDate FROM round ORDER BY roundNumber DESC LIMIT 1) THEN c.compno END) AS compounds,\r\n"
			+ "ROUND(IFNULL(COUNT(DISTINCT CASE WHEN c.insertDate > (SELECT startDate FROM round ORDER BY roundNumber DESC LIMIT 1) THEN c.compno END) / COUNT(DISTINCT a.uuid) * 100, 0), 2) AS pop,null as village,null as subvillage \r\n"
			+ "FROM location a INNER JOIN locationhierarchy b ON a.locationLevel_uuid = b.uuid \r\n"
			+ "LEFT JOIN listing c ON a.compno = c.compno LEFT JOIN fieldworker d ON b.fw_name = d.username GROUP BY \r\n"
			+ "b.uuid ORDER BY District")
	List<ActiveHouseholds> Compvisit();


}

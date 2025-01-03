package org.arn.hdsscapture.views;

import java.util.List;

import org.arn.hdsscapture.entity.Fieldworker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventsRepository extends JpaRepository <Fieldworker, String> {
	
	@Query(nativeQuery = true, value ="SELECT YEAR(deathDate) as year,count(a.uuid) total,SUM(CASE WHEN Gender = 1 THEN 1 ELSE 0 END) AS male,"
			+ " SUM(CASE WHEN Gender = 2 THEN 1 ELSE 0 END) AS female,'' as abt,'' as sbr,'' as mis,'' as lbr from death a INNER JOIN individual b on a.individual_uuid=b.uuid \r\n"
			+ " WHERE deathDate >= DATE_SUB(CURDATE(), INTERVAL 5 YEAR) group by YEAR(deathDate) ORDER BY year")
	List<Events> deaths();
	
	@Query(nativeQuery = true, value ="SELECT YEAR(outcomeDate) as year,count(a.uuid) total,SUM(CASE WHEN type = 1 THEN 1 ELSE 0 END) AS lbr,\r\n"
			+ "SUM(CASE WHEN type = 2 THEN 1 ELSE 0 END) AS sbr,SUM(CASE WHEN type = 3 THEN 1 ELSE 0 END) AS mis,\r\n"
			+ "SUM(CASE WHEN type = 4 THEN 1 ELSE 0 END) AS abt,'' as male,'' as female\r\n"
			+ " from outcome a INNER JOIN pregnancyoutcome b on a.preg_uuid=b.uuid\r\n"
			+ "WHERE outcomeDate >= DATE_SUB(CURDATE(), INTERVAL 5 YEAR) group by YEAR(outcomeDate) ORDER BY year")
	List<Events> outcome();

}

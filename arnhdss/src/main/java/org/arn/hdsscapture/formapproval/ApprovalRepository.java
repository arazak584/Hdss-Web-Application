package org.arn.hdsscapture.formapproval;

import java.util.List;

import org.arn.hdsscapture.entity.Fieldworker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ApprovalRepository extends JpaRepository <Fieldworker, String>{

	
	@Query(nativeQuery = true, value = "SELECT uuid as id,'Inmigration' as event,supervisor,count(uuid)Number,SUM(CASE WHEN `status` = 1 OR `status` =4 THEN 1 ELSE 0 END) AS Approved,SUM(CASE WHEN `status` = 2 THEN 1 ELSE 0 END) AS Rejected,SUM(CASE WHEN `status` = 3 THEN 1 ELSE 0 END) AS Resolved from inmigration where supervisor IS NOT NULL AND supervisor!='' AND insertDate >= (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1) GROUP BY supervisor\r\n"
			+ "UNION\r\n"
			+ "SELECT uuid as id,'Outmigration' as event,supervisor,count(uuid)Number,SUM(CASE WHEN `status` = 1 OR `status` =4 THEN 1 ELSE 0 END) AS Approved,SUM(CASE WHEN `status` = 2 THEN 1 ELSE 0 END) AS Rejected,SUM(CASE WHEN `status` = 3 THEN 1 ELSE 0 END) AS Resolved from outmigration where supervisor IS NOT NULL AND supervisor!='' AND insertDate >= (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1) GROUP BY supervisor\r\n"
			+ "UNION\r\n"
			+ "SELECT uuid as id,'Outcome' as event,supervisor,count(uuid)Number,SUM(CASE WHEN `status` = 1 OR `status` =4 THEN 1 ELSE 0 END) AS Approved,SUM(CASE WHEN `status` = 2 THEN 1 ELSE 0 END) AS Rejected,SUM(CASE WHEN `status` = 3 THEN 1 ELSE 0 END) AS Resolved from pregnancyoutcome where supervisor IS NOT NULL AND supervisor!='' AND insertDate >= (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1) GROUP BY supervisor\r\n"
			+ "UNION\r\n"
			+ "SELECT uuid as id,'pregnancyobservation' as event,supervisor,count(uuid)Number,SUM(CASE WHEN `status` = 1 OR `status` =4 THEN 1 ELSE 0 END) AS Approved,SUM(CASE WHEN `status` = 2 THEN 1 ELSE 0 END) AS Rejected,SUM(CASE WHEN `status` = 3 THEN 1 ELSE 0 END) AS Resolved from pregnancyobservation where supervisor IS NOT NULL AND supervisor!='' AND insertDate >= (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1) GROUP BY supervisor\r\n"
			+ "UNION\r\n"
			+ "SELECT uuid as id,'Death' as event,supervisor,count(uuid)Number,SUM(CASE WHEN `status` = 1 OR `status` =4 THEN 1 ELSE 0 END) AS Approved,SUM(CASE WHEN `status` = 2 THEN 1 ELSE 0 END) AS Rejected,SUM(CASE WHEN `status` = 3 THEN 1 ELSE 0 END) AS Resolved from death where supervisor IS NOT NULL AND supervisor!='' AND insertDate >= (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1) GROUP BY supervisor\r\n"
			+ "UNION\r\n"
			+ "SELECT individual_uuid as id,'Demographic' as event,supervisor,count(individual_uuid)Number,SUM(CASE WHEN `status` = 1 OR `status` =4 THEN 1 ELSE 0 END) AS Approved,SUM(CASE WHEN `status` = 2 THEN 1 ELSE 0 END) AS Rejected,SUM(CASE WHEN `status` = 3 THEN 1 ELSE 0 END) AS Resolved from demographic where supervisor IS NOT NULL AND supervisor!='' AND insertDate >= (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1) GROUP BY supervisor\r\n"
			+ "UNION\r\n"
			+ "SELECT uuid as id,'Relationship' as event,supervisor,count(uuid)Number,SUM(CASE WHEN `status` = 1 OR `status` =4 THEN 1 ELSE 0 END) AS Approved,SUM(CASE WHEN `status` = 2 THEN 1 ELSE 0 END) AS Rejected,SUM(CASE WHEN `status` = 3 THEN 1 ELSE 0 END) AS Resolved from relationship where supervisor IS NOT NULL AND supervisor!='' AND insertDate >= (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1) GROUP BY supervisor\r\n"
			+ "UNION\r\n"
			+ "SELECT uuid as id,'SES' as event,supervisor,count(uuid)Number,SUM(CASE WHEN `status` = 1 OR `status` =4 THEN 1 ELSE 0 END) AS Approved,SUM(CASE WHEN `status` = 2 THEN 1 ELSE 0 END) AS Rejected,SUM(CASE WHEN `status` = 3 THEN 1 ELSE 0 END) AS Resolved from sociodemographic where supervisor IS NOT NULL AND supervisor!='' AND insertDate >= (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1) GROUP BY supervisor\r\n"
			+ "UNION\r\n"
			+ "SELECT uuid as id,'Vaccination' as event,supervisor,count(uuid)Number,SUM(CASE WHEN `status` = 1 OR `status` =4 THEN 1 ELSE 0 END) AS Approved,SUM(CASE WHEN `status` = 2 THEN 1 ELSE 0 END) AS Rejected,SUM(CASE WHEN `status` = 3 THEN 1 ELSE 0 END) AS Resolved from vaccination where supervisor IS NOT NULL AND supervisor!='' AND insertDate >= (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1) GROUP BY supervisor\r\n"
			+ "UNION\r\n"
			+ "SELECT uuid as id,'Morbidity' as event,supervisor,count(uuid)Number,SUM(CASE WHEN `status` = 1 OR `status` =4 THEN 1 ELSE 0 END) AS Approved,SUM(CASE WHEN `status` = 2 THEN 1 ELSE 0 END) AS Rejected,SUM(CASE WHEN `status` = 3 THEN 1 ELSE 0 END) AS Resolved from morbidity where supervisor IS NOT NULL AND supervisor!='' AND insertDate >= (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1) GROUP BY supervisor;")
	List<approvals> approval();
	
}

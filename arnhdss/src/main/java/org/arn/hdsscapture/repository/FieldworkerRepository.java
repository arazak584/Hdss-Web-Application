package org.arn.hdsscapture.repository;

import java.util.List;

import org.arn.hdsscapture.entity.Fieldworker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface FieldworkerRepository extends JpaRepository <Fieldworker, String> {

	@Query(nativeQuery = true, value = "SELECT * from fieldworker where status!=0 ORDER BY username")
	List<Fieldworker> findAll();
	
//	@Query(nativeQuery = true, value = "SELECT fw_uuid,insertDate,username,firstName,lastName,Password,Case When status=0 then 'Inactive' when status=1 then 'Active' "
//			+ " when status=3 then 'Supervisor' else 'Unknown' end as status from fieldworker ORDER BY insertDate")
//	List<Fieldworker> findFieldworker();
	
	@Query(nativeQuery = true, value = "SELECT fw_uuid, insertDate, username, firstName, lastName, Password, "
            + "CASE WHEN status = 0 THEN 'Inactive' WHEN status = 1 THEN 'Active' WHEN status = 2 THEN 'Supervisor' WHEN status = 3 THEN 'Training' ELSE 'Unknown' END AS status "
            + "FROM fieldworker ORDER BY insertDate")
    List<Fieldworker> findFieldworker();
	
	@Query(nativeQuery = true, value = "SELECT * from fieldworker WHERE username = :username ORDER BY username")
	List<Fieldworker> findFieldworkerByUsername(@Param("username") String username);
	
	Fieldworker findByUsername(String username);
	
	@Query(nativeQuery = true, value = "SELECT * FROM fieldworker Where status=1 order by firstName,lastName")
	List<Fieldworker> fw();
	
	@Query(nativeQuery = true, value = "SELECT a.fw_uuid,a.firstName,a.lastName, MAX(b.insertDate) AS insertDate,a.`status`,`password`,username\r\n"
			+ "FROM fieldworker a INNER JOIN visit b ON a.fw_uuid = b.fw_uuid\r\n"
			+ "WHERE b.insertDate > (SELECT r.startDate FROM round r ORDER BY r.roundNumber DESC LIMIT 1)\r\n"
			+ "AND a.`status` = 1 GROUP BY a.fw_uuid ORDER BY insertDate ")
	List<Fieldworker> lastSync();
	
//	@Query(nativeQuery = true, value = "SELECT a.fw_uuid,a.firstName,a.lastName, MAX(b.insertDate) AS insertDate,a.`status`,`password`,username\r\n"
//			+ "FROM fieldworker a INNER JOIN visit b ON a.fw_uuid = b.fw_uuid\r\n"
//			+ "WHERE b.insertDate > (SELECT r.startDate FROM round r ORDER BY r.roundNumber DESC LIMIT 1)\r\n"
//			+ "AND a.`status` = 1 GROUP BY a.fw_uuid HAVING TIMESTAMPDIFF(DAY, MAX(b.insertDate), CURDATE()) > 2;")
//	List<Fieldworker> lastSync();
	
}

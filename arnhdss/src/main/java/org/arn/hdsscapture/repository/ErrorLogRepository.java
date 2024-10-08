package org.arn.hdsscapture.repository;

import java.util.List;

import org.arn.hdsscapture.entity.ErrorLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ErrorLogRepository extends JpaRepository <ErrorLog, Long> {
	
	@Query(nativeQuery = true, value = "SELECT DISTINCT tableName FROM errorLog Where tableName IS NOT NULL ORDER BY tableName")
	List<String> errorentity();
	
//	@Query(nativeQuery = true, value = "SELECT id,CONCAT(firstName,' ',lastName) as dataCollector,errorMessage,recordUuid,stackTrace,tableName,`timestamp` from errorLog a INNER JOIN fieldworker b on a.dataCollector=b.fw_uuid\r\n"
//			+ "WHERE tableName= :error GROUP BY recordUuid ORDER BY `timestamp` DESC")
//	List<ErrorLog> errorlog(@Param("error") String error);
	
	@Query(nativeQuery = true, value = "SELECT a.id, CONCAT(b.firstName,' ',b.lastName) as dataCollector, a.errorMessage, a.recordUuid, a.stackTrace, a.tableName, a.`timestamp` " +
		       "FROM errorLog a " +
		       "INNER JOIN fieldworker b ON a.dataCollector = b.fw_uuid " +
		       "WHERE a.tableName = :error AND a.`timestamp` = (SELECT MAX(a2.`timestamp`) FROM errorLog a2 WHERE a2.recordUuid = a.recordUuid) " +
		       "ORDER BY a.`timestamp` DESC")
		List<ErrorLog> errorlog(@Param("error") String error);
	
	@Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE errorLog", nativeQuery = true)
    void truncateErrorLog();


}

package org.arn.hdsscapture.views;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ViewHohRepository extends JpaRepository <ViewHoh, String> {
	
	@Query(nativeQuery = true, value = "SELECT uuid,Fieldworker,username,count(uuid) as total,max(insertDate)insertDate,max(submitDate)submitDate,name\r\n"
    		+ "FROM hoh WHERE insertDate BETWEEN :startDate AND :endDate GROUP BY Fieldworker,name ORDER BY Fieldworker,total")
	List<ViewHoh> Report(@Param("startDate") Date startDate, @Param("endDate") Date endDate);


}

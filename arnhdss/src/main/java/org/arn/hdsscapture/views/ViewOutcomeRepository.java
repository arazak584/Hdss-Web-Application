package org.arn.hdsscapture.views;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ViewOutcomeRepository extends JpaRepository <ViewOutcome, String> {
	
	@Query(nativeQuery = true, value = "SELECT uuid,Fieldworker,type,count(uuid) as total,max(insertDate)insertDate,max(submitDate)submitDate,name\r\n"
    		+ "FROM pregout WHERE insertDate BETWEEN :startDate AND :endDate GROUP BY Fieldworker,name,type ORDER BY Fieldworker,total")
	List<ViewOutcome> Report(@Param("startDate") Date startDate, @Param("endDate") Date endDate);


}

package org.arn.hdsscapture.views;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ActiveHouseholdRepository extends JpaRepository <ActiveHouseholds, String> {

	
	@Query(nativeQuery = true, value = "SELECT * from acthoh WHERE village= :village  ORDER BY subvillage")
	List<ActiveHouseholds> Report(@Param("village") String village);
	
	@Query(nativeQuery = true, value = "SELECT DISTINCT name as village FROM locationhierarchy Where level_uuid='hierarchyLevelId5'")
	List<String> villages();
	
	@Query(nativeQuery = true, value = "SELECT * from acthoh")
	List<ActiveHouseholds> Reports();


}

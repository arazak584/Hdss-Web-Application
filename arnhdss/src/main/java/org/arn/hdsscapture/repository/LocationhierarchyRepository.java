package org.arn.hdsscapture.repository;

import java.util.List;

import org.arn.hdsscapture.entity.Locationhierarchy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface LocationhierarchyRepository extends JpaRepository <Locationhierarchy, String> {

	@Query(nativeQuery = true, value = "SELECT * FROM locationhierarchy Where level_uuid='hierarchyLevelId6'")
	List<Locationhierarchy> villages();

}

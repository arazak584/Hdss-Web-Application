package org.arn.hdsscapture.repository;

import java.util.List;
import java.util.Optional;

import org.arn.hdsscapture.entity.Locationhierarchy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface LocationhierarchyRepository extends JpaRepository <Locationhierarchy, String> {

	@Query(nativeQuery = true, value = "SELECT * FROM locationhierarchy Where level_uuid='hierarchyLevelId6' ORDER BY name")
	List<Locationhierarchy> villages();
	
	@Query(nativeQuery = true, value = "SELECT * FROM locationhierarchy Where level_uuid='hierarchyLevelId5' ORDER BY name")
	List<Locationhierarchy> community();
	
	@Query(nativeQuery = true, value = "SELECT * from locationhierarchy Where level_uuid='hierarchyLevelId6'"
			+ " extId = :extId")
	List<Locationhierarchy> findByExtIds(@Param("extId") String extId);

	@Query(nativeQuery = true, value = "SELECT * FROM locationhierarchy WHERE level_uuid = 'hierarchyLevelId6' AND extId = :extId")
	Optional<Locationhierarchy> findByExtId(@Param("extId") String extId);
	
	@Query(nativeQuery = true, value = "SELECT * FROM locationhierarchy WHERE level_uuid = 'hierarchyLevelId6' AND extId = :extId")
	List<Locationhierarchy> findByUuid(@Param("extId") String extId);

}

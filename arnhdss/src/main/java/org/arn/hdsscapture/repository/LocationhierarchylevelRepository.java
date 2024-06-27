package org.arn.hdsscapture.repository;

import java.util.List;

import org.arn.hdsscapture.entity.Locationhierarchylevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface LocationhierarchylevelRepository extends JpaRepository <Locationhierarchylevel, String> {

	@Query(nativeQuery = true, value = "SELECT * FROM locationhierarchylevel Where uuid='hierarchyLevelId1'")
	List<Locationhierarchylevel> find1();
	
	@Query(nativeQuery = true, value = "SELECT * FROM locationhierarchylevel Where uuid='hierarchyLevelId2'")
	List<Locationhierarchylevel> find2();
	@Query(nativeQuery = true, value = "SELECT * FROM locationhierarchylevel Where uuid='hierarchyLevelId3'")
	List<Locationhierarchylevel> find3();
	@Query(nativeQuery = true, value = "SELECT * FROM locationhierarchylevel Where uuid='hierarchyLevelId4'")
	List<Locationhierarchylevel> find4();
	@Query(nativeQuery = true, value = "SELECT * FROM locationhierarchylevel Where uuid='hierarchyLevelId5'")
	List<Locationhierarchylevel> find5();
	@Query(nativeQuery = true, value = "SELECT * FROM locationhierarchylevel Where uuid='hierarchyLevelId6'")
	List<Locationhierarchylevel> find6();
}

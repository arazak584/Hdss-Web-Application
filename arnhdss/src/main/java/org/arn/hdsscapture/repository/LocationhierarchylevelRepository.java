package org.arn.hdsscapture.repository;

import java.util.List;

import org.arn.hdsscapture.entity.Locationhierarchy;
import org.arn.hdsscapture.entity.Locationhierarchylevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


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
	
	@Query(nativeQuery = true, value = "SELECT * FROM locationhierarchylevel ORDER BY uuid")
	List<Locationhierarchylevel> hierarchy();
	
	@Query(nativeQuery = true, value ="SELECT COUNT(uuid) FROM locationhierarchylevel")
	Long hierarchycnt();
	
	@Query(nativeQuery = true, value = "SELECT MAX(keyIdentifier) FROM locationhierarchylevel")
	 Integer findMaxNumber();
	
	@Query(nativeQuery = true, value = "SELECT * from locationhierarchylevel WHERE uuid = :uuid")
	List<Locationhierarchylevel> findUuid(@Param("uuid") String uuid);
}

package org.arn.hdsscapture.repository;

import java.util.List;
import java.util.Optional;

import org.arn.hdsscapture.entity.Locationhierarchy;
import org.arn.hdsscapture.views.ActiveHouseholds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface LocationhierarchyRepository extends JpaRepository <Locationhierarchy, String> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM locationhierarchy Where level_uuid IS NOT NULL")
	List<Locationhierarchy> findList();

	@Query(nativeQuery = true, value = "SELECT * FROM locationhierarchy Where level_uuid='hierarchyLevelId6' ORDER BY name")
	List<Locationhierarchy> subvillage();
	
	@Query(nativeQuery = true, value = "SELECT * FROM locationhierarchy Where level_uuid='hierarchyLevelId5' ORDER BY name")
	List<Locationhierarchy> village();
	
	@Query(nativeQuery = true, value = "SELECT * FROM locationhierarchy Where level_uuid='hierarchyLevelId4' ORDER BY name")
	List<Locationhierarchy> subdistrict();
	
	@Query(nativeQuery = true, value = "SELECT * FROM locationhierarchy Where level_uuid='hierarchyLevelId3' ORDER BY name")
	List<Locationhierarchy> district();

	@Query(nativeQuery = true, value = "SELECT * FROM locationhierarchy Where level_uuid='hierarchyLevelId2' ORDER BY name")
	List<Locationhierarchy> region();

	@Query(nativeQuery = true, value = "SELECT * FROM locationhierarchy Where level_uuid='hierarchyLevelId1' ORDER BY name")
	List<Locationhierarchy> country();
	
	@Query(nativeQuery = true, value = "SELECT * FROM locationhierarchy Where uuid='hierarchy_root'")
	List<Locationhierarchy> root();
	
	@Query(nativeQuery = true, value ="SELECT count(DISTINCT v.extId) from locationhierarchy v where v.level_uuid='hierarchyLevelId1' ")
	Long cnt1();
	@Query(nativeQuery = true, value ="SELECT count(DISTINCT v.extId) from locationhierarchy v where v.level_uuid='hierarchyLevelId2' ")
	Long cnt2();
	@Query(nativeQuery = true, value ="SELECT count(DISTINCT v.extId) from locationhierarchy v where v.level_uuid='hierarchyLevelId3' ")
	Long cnt3();
	@Query(nativeQuery = true, value ="SELECT count(DISTINCT v.extId) from locationhierarchy v where v.level_uuid='hierarchyLevelId4' ")
	Long cnt4();
	@Query(nativeQuery = true, value ="SELECT count(DISTINCT v.extId) from locationhierarchy v where v.level_uuid='hierarchyLevelId5' ")
	Long cnt5();
	@Query(nativeQuery = true, value ="SELECT count(DISTINCT v.extId) from locationhierarchy v where v.level_uuid='hierarchyLevelId6' ")
	Long cnt6();
	
	
	
	@Query(nativeQuery = true, value = "SELECT COUNT(*) FROM locationhierarchy WHERE extId = :extId ")
	int codeErrs(@Param("extId") String extId);
	
	@Query(nativeQuery = true, value = "SELECT * from locationhierarchy WHERE uuid = :uuid")
	List<Locationhierarchy> findUuid(@Param("uuid") String uuid);
	
	
	
	@Query(nativeQuery = true, value = "SELECT * from locationhierarchy Where level_uuid='hierarchyLevelId6'"
			+ " extId = :extId")
	List<Locationhierarchy> findByExtIds(@Param("extId") String extId);

	@Query(nativeQuery = true, value = "SELECT * FROM locationhierarchy WHERE level_uuid = 'hierarchyLevelId6' AND extId = :extId")
	Optional<Locationhierarchy> findByExtId(@Param("extId") String extId);
	
	@Query(nativeQuery = true, value = "SELECT * FROM locationhierarchy WHERE level_uuid = 'hierarchyLevelId6' AND extId = :extId")
	List<Locationhierarchy> findByUuid(@Param("extId") String extId);
	
	
	@Query(nativeQuery = true, value = "SELECT a.uuid,a.area,a.extId,a.level_uuid,a.`name`,b.`name` as parent_uuid,a.town,a.fw_name \r\n"
			+ " from locationhierarchy as a INNER JOIN locationhierarchy b ON a.parent_uuid=b.uuid where  a.level_uuid= :level_uuid")
	List<Locationhierarchy> Report(@Param("level_uuid") String level_uuid);
	
	@Query(nativeQuery = true, value = "SELECT a.* from locationhierarchy a where a.level_uuid= :level_uuid")
	List<Locationhierarchy> list(@Param("level_uuid") String level_uuid);

}

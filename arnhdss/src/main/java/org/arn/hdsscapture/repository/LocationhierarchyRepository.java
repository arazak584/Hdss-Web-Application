package org.arn.hdsscapture.repository;

import java.util.List;
import java.util.Optional;

import org.arn.hdsscapture.entity.Locationhierarchy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface LocationhierarchyRepository extends JpaRepository <Locationhierarchy, String> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM locationhierarchy Where level_uuid IS NOT NULL")
	List<Locationhierarchy> findList();

//	@Query(nativeQuery = true, value = "SELECT * FROM locationhierarchy Where level_uuid='hierarchyLevelId6' ORDER BY name")
//	List<Locationhierarchy> subvillage();
	
	@Query("SELECT l FROM Locationhierarchy l WHERE l.level_uuid = (SELECT max(h.level_uuid) FROM Locationhierarchy h)")
	List<Locationhierarchy> subvillage();
	
	@Query(nativeQuery = true, value = "SELECT * FROM locationhierarchy l WHERE l.level_uuid = (SELECT sub.level_uuid "
			+ " FROM (SELECT DISTINCT level_uuid FROM locationhierarchy ORDER BY level_uuid DESC LIMIT 1 OFFSET 1 ) AS sub )" )
	List<Locationhierarchy> village();
	
	@Query("SELECT count(DISTINCT l.extId) FROM Locationhierarchy l WHERE l.level_uuid = (SELECT max(h.level_uuid) FROM Locationhierarchy h)")
	Long cnt1();
	
//	@Query(nativeQuery = true, value ="SELECT count(DISTINCT v.extId) from locationhierarchy v where v.level_uuid='hierarchyLevelId1' ")
//	Long cnt1();

	
//	@Query(nativeQuery = true, value = "SELECT * FROM locationhierarchy WHERE level_uuid = 'hierarchyLevelId6' AND extId = :extId")
//	List<Locationhierarchy> findByUuid(@Param("extId") String extId);
	
	@Query("SELECT l FROM Locationhierarchy l WHERE l.level_uuid = (SELECT h.uuid FROM Locationhierarchylevel h WHERE h.keyIdentifier = (\r\n"
			+ "  SELECT MAX(h2.keyIdentifier) FROM Locationhierarchylevel h2 )) AND l.extId = :extId")
	List<Locationhierarchy> findByUuid(@Param("extId") String extId);
	
	@Query("SELECT l FROM Locationhierarchy l WHERE l.level_uuid = (SELECT h.uuid FROM Locationhierarchylevel h WHERE h.keyIdentifier = (\r\n"
			+ "  SELECT MAX(h2.keyIdentifier) FROM Locationhierarchylevel h2 )) AND l.extId = :extId")
	Optional<Locationhierarchy> findByExtIdz(@Param("extId") String extId);
	
//
//	@Query(nativeQuery = true, value = "SELECT * FROM locationhierarchy WHERE level_uuid = 'hierarchyLevelId6' AND extId = :extId")
//	Optional<Locationhierarchy> findByExtIdz(@Param("extId") String extId);

	
//	@Query(nativeQuery = true, value = "SELECT * FROM locationhierarchy Where level_uuid='hierarchyLevelId5' ORDER BY name")
//	List<Locationhierarchy> village();
//	
//	@Query(nativeQuery = true, value = "SELECT * FROM locationhierarchy Where level_uuid='hierarchyLevelId4' ORDER BY name")
//	List<Locationhierarchy> subdistrict();
//	
//	@Query(nativeQuery = true, value = "SELECT * FROM locationhierarchy Where level_uuid='hierarchyLevelId3' ORDER BY name")
//	List<Locationhierarchy> district();
//
//	@Query(nativeQuery = true, value = "SELECT * FROM locationhierarchy Where level_uuid='hierarchyLevelId2' ORDER BY name")
//	List<Locationhierarchy> region();
//
//	@Query(nativeQuery = true, value = "SELECT * FROM locationhierarchy Where level_uuid='hierarchyLevelId1' ORDER BY name")
//	List<Locationhierarchy> country();
//	
//	@Query(nativeQuery = true, value = "SELECT * FROM locationhierarchy Where uuid='hierarchy_root'")
//	List<Locationhierarchy> root();
//	
//	@Query(nativeQuery = true, value ="SELECT count(DISTINCT v.extId) from locationhierarchy v where v.level_uuid='hierarchyLevelId1' ")
//	Long cnt1();
//	@Query(nativeQuery = true, value ="SELECT count(DISTINCT v.extId) from locationhierarchy v where v.level_uuid='hierarchyLevelId2' ")
//	Long cnt2();
//	@Query(nativeQuery = true, value ="SELECT count(DISTINCT v.extId) from locationhierarchy v where v.level_uuid='hierarchyLevelId3' ")
//	Long cnt3();
//	@Query(nativeQuery = true, value ="SELECT count(DISTINCT v.extId) from locationhierarchy v where v.level_uuid='hierarchyLevelId4' ")
//	Long cnt4();
//	@Query(nativeQuery = true, value ="SELECT count(DISTINCT v.extId) from locationhierarchy v where v.level_uuid='hierarchyLevelId5' ")
//	Long cnt5();
//	@Query(nativeQuery = true, value ="SELECT count(DISTINCT v.extId) from locationhierarchy v where v.level_uuid='hierarchyLevelId6' ")
//	Long cnt6();
	
	
	
	@Query(nativeQuery = true, value = "SELECT COUNT(*) FROM locationhierarchy WHERE extId = :extId ")
	int codeErrs(@Param("extId") String extId);
	
	@Query(nativeQuery = true, value = "SELECT * from locationhierarchy WHERE uuid = :uuid")
	List<Locationhierarchy> findUuid(@Param("uuid") String uuid);
	
	
	
//	@Query(nativeQuery = true, value = "SELECT * from locationhierarchy Where level_uuid='hierarchyLevelId6'"
//			+ " extId = :extId")
//	List<Locationhierarchy> findByExtIds(@Param("extId") String extId);

	
	
	@Query(nativeQuery = true, value = "SELECT a.uuid,a.area,a.extId,a.level_uuid,a.`name`,b.`name` as parent_uuid,a.town,a.fw_name \r\n"
			+ " from locationhierarchy as a INNER JOIN locationhierarchy b ON a.parent_uuid=b.uuid where  a.level_uuid= :level_uuid")
	List<Locationhierarchy> Report(@Param("level_uuid") String level_uuid);
	
//	@Query(nativeQuery = true, value = "SELECT b.* FROM locationhierarchy a INNER JOIN locationhierarchy b on a.parent_uuid=b.uuid \r\n"
//			+ " where  a.level_uuid= :level_uuid AND b.level_uuid=CONCAT(LEFT(a.level_uuid, 16), CAST(RIGHT(a.level_uuid, 1) AS UNSIGNED) - 1)  GROUP BY b.uuid")
//	List<Locationhierarchy> apilist(@Param("level_uuid") String level_uuid);
	
	@Query(nativeQuery = true, value = "SELECT b.* FROM locationhierarchy b LEFT JOIN locationhierarchy a  ON a.parent_uuid = b.uuid \r\n"
			+ "  WHERE b.level_uuid = CONCAT(LEFT(:level_uuid, 16), CAST(RIGHT(:level_uuid, 1) AS UNSIGNED) - 1)  GROUP BY b.uuid")
	List<Locationhierarchy> apilist(@Param("level_uuid") String level_uuid);

	
	@Query(nativeQuery = true, value = "SELECT a.* from locationhierarchy a where a.level_uuid= :level_uuid")
	List<Locationhierarchy> list(@Param("level_uuid") String level_uuid);
	
	@Query("SELECT u FROM Locationhierarchy u WHERE u.extId = :extId")
	List<Locationhierarchy> findByExtId(@Param("extId") String extId);
	
//	@Query("SELECT COUNT(l) FROM Locationhierarchy l WHERE l.level_uuid = :levelUuid")
//	Long countByLevelUuid(@Param("levelUuid") String levelUuid);
	
	@Query(value = "SELECT MAX(CAST(SUBSTRING_INDEX(uuid, '_', -1) AS UNSIGNED)) " +
            "FROM locationhierarchy WHERE level_uuid = :levelUuid", nativeQuery = true)
	Integer findMaxSequenceByLevelUuid(@Param("levelUuid") String levelUuid);



}

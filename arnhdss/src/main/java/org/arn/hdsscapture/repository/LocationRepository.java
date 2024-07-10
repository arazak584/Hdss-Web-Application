package org.arn.hdsscapture.repository;

import java.util.List;

import org.arn.hdsscapture.entity.Location;
import org.arn.hdsscapture.projection.LocationProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LocationRepository extends JpaRepository <Location, String> {

	@Query(nativeQuery = true, value = "SELECT a.*,b.parent_uuid as vill_extId FROM `location` a INNER JOIN "
			+ "locationhierarchy b on a.locationLevel_uuid=b.uuid;")
	List<LocationProjection> findLocations();
	
	@Query(nativeQuery = true, value = "SELECT a.*, b.parent_uuid as vill_extId FROM location a INNER JOIN "
			+ "locationhierarchy b ON a.locationLevel_uuid = b.uuid LIMIT ?1 OFFSET ?2")
	List<LocationProjection> findLocation(int pageSize, int offset);
	
	@Query(nativeQuery = true, value = "SELECT a.* FROM location a INNER JOIN locationhierarchy b on a.locationLevel_uuid=b.uuid\r\n"
			+ "INNER JOIN locationhierarchy c on b.parent_uuid=c.uuid WHERE c.name = :village")
	List<Location> findByVillage(@Param("village") String village);

}

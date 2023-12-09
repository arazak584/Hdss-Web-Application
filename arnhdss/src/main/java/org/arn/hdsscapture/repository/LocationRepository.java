package org.arn.hdsscapture.repository;

import java.util.List;

import org.arn.hdsscapture.entity.Location;
import org.arn.hdsscapture.projection.LocationProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LocationRepository extends JpaRepository <Location, String> {

	@Query(nativeQuery = true, value = "SELECT a.*,b.parent_uuid as vill_extId FROM `location` a INNER JOIN "
			+ "locationhierarchy b on a.locationLevel_uuid=b.uuid;")
	List<Location> findLocations();
	
	@Query(nativeQuery = true, value = "SELECT a.*, b.parent_uuid as vill_extId FROM location a INNER JOIN locationhierarchy b ON a.locationLevel_uuid = b.uuid")
	List<LocationProjection> findLocation();

}

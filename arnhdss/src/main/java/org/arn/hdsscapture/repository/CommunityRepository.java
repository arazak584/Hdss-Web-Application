package org.arn.hdsscapture.repository;

import java.util.List;

import org.arn.hdsscapture.entity.CommunityReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommunityRepository extends JpaRepository <CommunityReport, String> {

	@Query(nativeQuery = true, value = "SELECT * from communityreport WHERE uuid = :uuid")
	List<CommunityReport> findCom(@Param("uuid") String uuid);

}

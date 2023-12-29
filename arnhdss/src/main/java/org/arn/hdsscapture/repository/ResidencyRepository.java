package org.arn.hdsscapture.repository;

import java.util.List;

import org.arn.hdsscapture.entity.Residency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ResidencyRepository extends JpaRepository <Residency, String> {
	
	@Query(nativeQuery = true, value = "SELECT m1.* FROM hdss.residency m1,\r\n"
			+ "(SELECT MAX(startDate) AS startDate, individual_uuid FROM hdss.residency\r\n"
			+ "GROUP BY individual_uuid) residency\r\n"
			+ "WHERE m1.startDate = residency.startDate AND m1.individual_uuid = residency.individual_uuid AND m1.endtype !=3 LIMIT ?1 OFFSET ?2")
		List<Residency> findResidency(int pageSize, int offset);

	
	@Query("SELECT r FROM Residency r WHERE r.individual_uuid = :individual_uuid AND r.uuid <> :uuid AND r.endType = 1")
	List<Residency> findConflictingRecords(@Param("individual_uuid") String individual_uuid, @Param("uuid") String uuid);
	
	@Query("SELECT r FROM Residency r WHERE r.uuid = :uuid")
    Residency findByUuid(@Param("uuid") String uuid);
	
	@Query("SELECT count(r) FROM Residency r WHERE r.endType = 1")
	Long countResidency();



}

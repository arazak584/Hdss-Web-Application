package org.arn.hdsscapture.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.arn.hdsscapture.entity.Residency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
	
	// Bulk update to set startDate to dob if dob greater than start date
    @Modifying
    @Transactional
    @Query(value = "UPDATE residency a, individual b SET a.startDate = b.dob WHERE a.individual_uuid=b.uuid AND b.dob>a.startDate", nativeQuery = true)
    void updateResidency();
    
    //Update Inmigration set recordedDate to start date
    @Modifying
    @Transactional
    @Query(value = "UPDATE inmigration a, residency b SET a.recordedDate = b.startDate WHERE a.residency_uuid=b.uuid", nativeQuery = true)
    void updateInmigration();
    
  //Update Outmigration set recordedDate to start date
    @Modifying
    @Transactional
    @Query(value = "UPDATE outmigration a, residency b SET a.recordedDate = b.startDate WHERE a.residency_uuid=b.uuid", nativeQuery = true)
    void updateOutmigration();


}

package org.arn.hdsscapture.repository;



import java.util.List;
import java.util.Optional;

import org.arn.hdsscapture.entity.Individual;
import org.arn.hdsscapture.projection.IndividualProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IndividualRepository extends JpaRepository <Individual, String> {
	
	@Query(nativeQuery = true, value = "SELECT a.*,m1.endType,compno,c.name as village,d.extId as hohID FROM individual a INNER JOIN hdss.residency m1 on a.uuid=m1.individual_uuid "
	        + "INNER JOIN location b on m1.location_uuid=b.uuid INNER JOIN locationhierarchy c on b.locationLevel_uuid=c.uuid "
	        + "INNER JOIN socialgroup d on m1.socialgroup_uuid=d.uuid, "
	        + "(SELECT MAX(startDate) AS startDate, individual_uuid FROM hdss.residency "
	        + "GROUP BY individual_uuid) residency "
	        + "WHERE m1.startDate = residency.startDate AND m1.individual_uuid = residency.individual_uuid "
	        + "UNION "
	        + "SELECT *, NULL AS endType, NULL AS compno, NULL AS village, "
	        + " NULL AS hohID FROM individual WHERE extId='UNK' LIMIT ?1 OFFSET ?2")
	List<IndividualProjection> findIndividual(int pageSize, int offset);
	
	@Query(nativeQuery = true, value = "SELECT * FROM individual WHERE extId = :extId")
	Optional<Individual> findByExtId(String extId);

}


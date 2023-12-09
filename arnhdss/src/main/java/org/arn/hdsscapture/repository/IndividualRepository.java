package org.arn.hdsscapture.repository;



import java.util.List;

import org.arn.hdsscapture.entity.Individual;
import org.arn.hdsscapture.projection.IndividualProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IndividualRepository extends JpaRepository <Individual, String> {
	
	@Query(nativeQuery = true, value = "SELECT a.*,m1.endType,m1.startDate,m1.endDate,compno,c.name as village,m1.uuid as residency,m1.socialgroup_uuid as socialgroup,d.extId as hohID FROM individual a INNER JOIN hdss.residency m1 on a.uuid=m1.individual_uuid\r\n"
			+ "INNER JOIN location b on m1.location_uuid=b.uuid INNER JOIN locationhierarchy c on b.locationLevel_uuid=c.uuid\r\n"
			+ "INNER JOIN socialgroup d on m1.socialgroup_uuid=d.uuid,\r\n"
			+ "(SELECT MAX(startDate) AS startDate, individual_uuid FROM hdss.residency\r\n"
			+ "GROUP BY individual_uuid) residency\r\n"
			+ "WHERE m1.startDate = residency.startDate AND m1.individual_uuid = residency.individual_uuid\r\n"
			+ "UNION\r\n"
			+ "select *,NULL AS endType,NULL AS startDate,NULL AS endDate,NULL AS compno,NULL AS village,\r\n"
			+ "NULL AS residency, NULL AS socialgroup, NULL AS hohID from individual where extId='UNK';")
	List<IndividualProjection> findIndividual();

}


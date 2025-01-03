package org.arn.hdsscapture.repository;



import java.util.List;
import java.util.Optional;

import org.arn.hdsscapture.entity.Individual;
import org.arn.hdsscapture.projection.IndividualProjection;
import org.arn.hdsscapture.views.IndividualSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface IndividualRepository extends JpaRepository <Individual, String> {
	
	@Query(nativeQuery = true, value = "SELECT a.*,m1.endType,compno,c.name as village,d.extId as hohID,phone1 FROM individual a INNER JOIN hdss.residency m1 on a.uuid=m1.individual_uuid "
	        + "INNER JOIN location b on m1.location_uuid=b.uuid INNER JOIN locationhierarchy c on b.locationLevel_uuid=c.uuid "
	        + "INNER JOIN socialgroup d on m1.socialgroup_uuid=d.uuid "
	        + "LEFT JOIN demographic x on a.uuid=x.individual_uuid, "
	        + "(SELECT MAX(startDate) AS startDate, individual_uuid FROM hdss.residency "
	        + "GROUP BY individual_uuid) residency "
	        + "WHERE m1.startDate = residency.startDate AND m1.individual_uuid = residency.individual_uuid "
	        + "UNION "
	        + "SELECT *, NULL AS endType, NULL AS compno, NULL AS village, "
	        + " NULL AS hohID,NULL AS phone1 FROM individual WHERE extId='UNK' LIMIT ?1 OFFSET ?2")
	List<IndividualProjection> findIndividual(int pageSize, int offset);
	
	@Query(nativeQuery = true, value = "SELECT * FROM individual WHERE extId = :extId")
	Optional<Individual> findByExtId(String extId);
	
	@Query(nativeQuery = true, value = "SELECT DISTINCT a.extId as permid,c.compno,a.dob,concat(a.firstName,' ',a.lastName,' ',COALESCE(a.otherName, ''))as name,a.othername,\r\n"
			+ "CASE when a.gender=1 then 'M' when  a.gender=2 then 'F' else a.gender end as gender,x.extId as motherid,concat(x.firstName,' ',x.lastName)as mothername,"
			+ "b.endType as endType,d.name as village from individual a LEFT JOIN individual x on a.mother_uuid=x.uuid\r\n"
			+ "INNER JOIN residency b on a.uuid=b.individual_uuid inner join location c on b.location_uuid=c.uuid\r\n"
			+ "INNER JOIN locationhierarchy d on c.locationLevel_uuid=d.uuid\r\n"
			+ "where compno like CONCAT('%', :search, '%') OR concat(a.firstName,' ',a.lastName) LIKE CONCAT('%', :search, '%') OR d.name LIKE CONCAT('%', :search, '%')\r\n"
			+ "order by endType;")
	List<IndividualSearch> Report(@Param("search") String search);

}


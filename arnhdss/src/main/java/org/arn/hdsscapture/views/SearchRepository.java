package org.arn.hdsscapture.views;

import java.util.List;

import org.arn.hdsscapture.entity.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SearchRepository extends JpaRepository <IndividualSearch, String> {
	
	@Query(nativeQuery = true, value = "SELECT a.extId as permid,c.compno,a.dob,concat(a.firstName,' ',a.lastName,' ',COALESCE(a.otherName, ''))as name,a.othername,\r\n"
			+ "CASE when a.gender=1 then 'Male' when  a.gender=2 then 'Female' else a.gender end as gender,x.extId as motherid,concat(x.firstName,' ',x.lastName)as mothername,"
			+ "CASE when endType=1 then 'Active' when  endType=3 then 'DTH' when  endType=2 then 'OMG' else endType end as endType,"
			+ "d.name as village from individual a left join individual x on a.mother_uuid=x.uuid\r\n"
			+ "inner join residency b on a.uuid=b.individual_uuid left join location c on b.location_uuid=c.uuid\r\n"
			+ "INNER JOIN locationhierarchy d on c.locationLevel_uuid=d.uuid\r\n"
			+ "where compno like CONCAT('%', :search, '%') OR concat(a.firstName,' ',a.lastName) LIKE CONCAT('%', :search, '%') OR d.name LIKE CONCAT('%', :search, '%')\r\n"
			+ "order by endType;")
	List<IndividualSearch> Report(@Param("search") String search);
	

}

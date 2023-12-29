package org.arn.hdsscapture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.arn.hdsscapture.entity.Visit;

public interface ReportRepository extends JpaRepository <Visit, String> {


	
	@Query(nativeQuery = true, value ="SELECT count(*) FROM residency r WHERE r.endType = 1")
	Long countResidency();
//	
//	@Query(nativeQuery = true, value ="SELECT  round((SELECT count(DISTINCT v.extId) from visit v where v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1))/\r\n"
//			+ "(SELECT count(DISTINCT z.socialgroup_uuid) from residency z where z.endType=1)*100,2) as done")
//    Double countHse();
	
	@Query(nativeQuery = true, value ="SELECT  round((SELECT count(DISTINCT v.uuid) from residency v where v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1))/\r\n"
			+ "(SELECT count(z.uuid) from residency z where z.endType=1)*100,2) as done")
    Double perRES();
	
	@Query(nativeQuery = true, value ="SELECT count(Distinct r.socialgroup_uuid) FROM residency r WHERE r.endType = 1")
	Long countSocialgroup();
	
	@Query(nativeQuery = true, value ="SELECT  round((SELECT count(DISTINCT v.uuid) from socialgroup v where v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1))/\r\n"
			+ "(SELECT count(DISTINCT z.socialgroup_uuid) from residency z where z.endType=1)*100,2) as done")
    Double perHH();
	
	@Query(nativeQuery = true, value ="SELECT count(*) FROM location")
	Long countCompound();
	
	@Query(nativeQuery = true, value ="SELECT  round((SELECT count(DISTINCT v.compno) from listing v where v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1))/\r\n"
			+ "(SELECT count(uuid) from location)*100,2) as done")
    Double perComp();
	
	@Query(nativeQuery = true, value ="SELECT  round((SELECT count(DISTINCT v.uuid) from location v where v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1))/\r\n"
			+ "(SELECT count(uuid) from location)*100,2) as done")
    Double perLoc();
	
	@Query(nativeQuery = true, value ="SELECT  round((SELECT count(DISTINCT v.compno) from listing v where v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)))")
    Long countList();
	
	@Query(nativeQuery = true, value ="SELECT  round((SELECT count(DISTINCT v.uuid) from inmigration v where v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)))")
    Long countImg();
	
	@Query(nativeQuery = true, value ="SELECT  round((SELECT count(DISTINCT v.uuid) from outmigration v where v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)))")
    Long countOmg();
	
	@Query(nativeQuery = true, value ="SELECT  round((SELECT count(DISTINCT v.uuid) from pregnancyoutcome v where v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)))")
    Long countOutcome();
	
	@Query(nativeQuery = true, value ="SELECT  round((SELECT count(DISTINCT v.uuid) from pregnancyobservation v where v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)))")
    Long countPreg();
	
	@Query(nativeQuery = true, value ="SELECT  round((SELECT count(DISTINCT v.uuid) from death v where v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)))")
    Long countDth();
	
	@Query(nativeQuery = true, value ="SELECT  round((SELECT count(DISTINCT v.uuid) from sociodemographic v where v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)))")
    Long countSes();
	
	@Query(nativeQuery = true, value ="SELECT  round((SELECT count(DISTINCT v.uuid) from vaccination v where v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)))")
    Long countVac();
	
	@Query(nativeQuery = true, value ="SELECT count(uuid) from individual")
    Long countInd();

	@Query(nativeQuery = true, value ="SELECT  round((SELECT count(DISTINCT v.uuid) from individual v where v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1))/\r\n"
			+ "(SELECT count(uuid) from individual)*100,2) as done")
    Double perInd();
	
	@Query(nativeQuery = true, value ="SELECT  round((SELECT count(DISTINCT v.extId) from visit v where v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1))/\r\n"
			+ "(SELECT count(DISTINCT socialgroup_uuid) from residency where endType=1)*100,2) as done")
    Double hhVisit();
    
    @Query(nativeQuery = true, value ="SELECT  round((SELECT count(DISTINCT v.extId) from visit v where v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)))")
    Long countVisit();
    
    @Query(nativeQuery = true, value ="SELECT  round((SELECT count(DISTINCT v.uuid) from relationship v where v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)))")
    Long countRel();
    
    @Query(nativeQuery = true, value ="SELECT  round((SELECT count(DISTINCT v.uuid) from socialgroup v where v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)))")
    Long countHH();
    
    @Query(nativeQuery = true, value ="SELECT  round((SELECT count(DISTINCT v.uuid) from individual v where v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)))")
    Long countPerson();
    
    @Query(nativeQuery = true, value ="SELECT  round((SELECT count(DISTINCT v.uuid) from location v where v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)))")
    Long countLoc();
    
    @Query(nativeQuery = true, value ="SELECT COUNT(a.uuid) from individual a LEFT JOIN residency b on a.uuid=b.individual_uuid\r\n"
    		+ "where b.individual_uuid IS NULL AND a.uuid!='UNK'")
    Long noMem();
    
    @Query(nativeQuery = true, value ="SELECT COUNT(*) from socialgroup a INNER JOIN individual b on a.individual_uuid=b.uuid\r\n"
    		+ "where TIMESTAMPDIFF(YEAR,dob,CURDATE())<(select hoh_age from settings)")
    Long Minor();
    
    @Query(nativeQuery = true, value ="SELECT COUNT(*) FROM (SELECT individual_uuid FROM residency WHERE endType = 1 GROUP BY individual_uuid \r\n"
    		+ "HAVING COUNT(individual_uuid) > 1 ) AS duplicates")
    Long Dupres();
    
    @Query(nativeQuery = true, value ="SELECT COUNT(a.uuid) from individual a INNER JOIN residency b on a.uuid=b.individual_uuid\r\n"
    		+ "where a.dob>startDate")
    Long Dobs();
    
    @Query(nativeQuery = true, value ="SELECT COUNT(individual_uuid) FROM (SELECT individual_uuid, startDate, endDate,\r\n"
    		+ "LAG(endDate) OVER (PARTITION BY individual_uuid ORDER BY startDate) AS prev_endDate\r\n"
    		+ "FROM residency) AS subquery	WHERE startDate < prev_endDate")
    Long Lag();
    
    @Query(nativeQuery = true, value ="SELECT COUNT(DISTINCT socialgroup_uuid) FROM individual AS a INNER JOIN residency AS b ON a.uuid = b.individual_uuid\r\n"
    		+ "WHERE b.endType = 1 AND b.socialgroup_uuid IN ( SELECT socialgroup_uuid FROM residency AS b2\r\n"
    		+ "INNER JOIN individual AS a2 ON b2.individual_uuid = a2.uuid WHERE b2.endType = 1\r\n"
    		+ "GROUP BY socialgroup_uuid HAVING MAX(TIMESTAMPDIFF(YEAR, a2.dob, CURDATE())) < (select hoh_age from settings))")
    Long Minors();
    
    @Query(nativeQuery = true, value ="SELECT COUNT(DISTINCT a.uuid) FROM pregnancyoutcome AS a LEFT JOIN outcome AS b ON a.uuid = b.preg_uuid\r\n"
    		+ "WHERE b.preg_uuid is null")
    Long Outcome();
    
    

}

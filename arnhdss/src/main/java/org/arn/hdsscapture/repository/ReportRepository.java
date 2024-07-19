package org.arn.hdsscapture.repository;

import org.arn.hdsscapture.entity.Fieldworker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReportRepository extends JpaRepository <Fieldworker, String> {


	
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
	
	@Query(nativeQuery = true, value ="SELECT count(DISTINCT b.location_uuid) FROM location a INNER JOIN residency b on a.uuid=b.location_uuid Where b.endType = 1 ")
	Long countCompound();
	
	@Query(nativeQuery = true, value ="SELECT count(DISTINCT a.uuid) FROM location a ")
	Long countCompounds();
	
	@Query(nativeQuery = true, value ="SELECT  round((SELECT count(DISTINCT v.compno) from listing v where v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1))/\r\n"
			+ "(SELECT count(uuid) from location)*100,2) as done")
    Double perComp();
	
	@Query(nativeQuery = true, value ="SELECT  round((SELECT count(DISTINCT v.uuid) from location v where v.insertDate > "
			+ "(SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1))/(SELECT count(DISTINCT b.location_uuid) "
			+ "FROM location a INNER JOIN residency b on a.uuid=b.location_uuid Where b.endType = 1)*100,2) as done")
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
	
	@Query(nativeQuery = true, value ="SELECT  round((SELECT count(DISTINCT v.uuid) from sociodemographic v where v.formcompldate >= (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)  and v.insertDate < (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)))")
    Long countoldSes();
	
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
	
	@Query(nativeQuery = true, value ="SELECT count(DISTINCT v.extId) from visit v where v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	Long visit();
	
	@Query(nativeQuery = true, value ="SELECT count(DISTINCT socialgroup_uuid) from residency where endType=1;")
	Long hhAct();
	
	@Query(nativeQuery = true, value ="SELECT count(DISTINCT v.socialgroup_uuid) from visit v JOIN residency r ON v.socialgroup_uuid = r.socialgroup_uuid where v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1) "
			+ " AND NOT EXISTS (SELECT 1 FROM residency r2 WHERE r2.socialgroup_uuid = v.socialgroup_uuid AND r2.endType = 1 );")
	Long hhNot();
    
    @Query(nativeQuery = true, value ="SELECT  round((SELECT count(DISTINCT v.extId) from visit v where v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)))")
    Long countVisit();
    
    @Query(nativeQuery = true, value ="SELECT  round((SELECT count(DISTINCT v.uuid) from relationship v where v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)))")
    Long countRel();
    
    @Query(nativeQuery = true, value ="SELECT  round((SELECT count(DISTINCT v.uuid) from socialgroup v INNER JOIN residency a on v.uuid=a.socialgroup_uuid where v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)))")
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
    
//    //Mysql 5
//    @Query(nativeQuery = true, value ="SELECT COUNT(individual_uuid) FROM ( SELECT a.individual_uuid,a.startDate,a.endDate,\r\n"
//    		+ "(SELECT MAX(b.endDate) FROM residency b WHERE b.individual_uuid = a.individual_uuid \r\n"
//    		+ "AND b.startDate < a.startDate) AS prev_endDate FROM residency a\r\n"
//    		+ ") AS subquery WHERE startDate < prev_endDate")
//    Long Lag();
    
    @Query(nativeQuery = true, value ="SELECT COUNT(DISTINCT socialgroup_uuid) FROM individual AS a INNER JOIN residency AS b ON a.uuid = b.individual_uuid\r\n"
    		+ "LEFT JOIN `duplicate` z on a.uuid=z.dup_uuid LEFT JOIN `duplicate` x on a.uuid=x.dup1_uuid \r\n"
    		+ "LEFT JOIN `duplicate` y on a.uuid=y.dup2_uuid WHERE b.endType = 1 AND z.dup_uuid is null AND x.dup1_uuid is null \r\n"
    		+ "AND y.dup2_uuid is null AND b.socialgroup_uuid IN ( SELECT 	socialgroup_uuid FROM residency AS b2\r\n"
    		+ "INNER JOIN individual AS a2 ON b2.individual_uuid = a2.uuid\r\n"
    		+ "WHERE b2.endType = 1\r\n"
    		+ "GROUP BY socialgroup_uuid HAVING MAX(TIMESTAMPDIFF(YEAR, a2.dob, CURDATE())) < (select hoh_age from settings))")
    Long Minors();
    
    @Query(nativeQuery = true, value ="SELECT COUNT(DISTINCT a.uuid) FROM pregnancyoutcome AS a LEFT JOIN outcome AS b ON a.uuid = b.preg_uuid\r\n"
    		+ "WHERE b.preg_uuid is null")
    Long Outcome();
    
    @Query(nativeQuery = true, value ="SELECT COUNT(DISTINCT a.uuid) FROM socialgroup a INNER JOIN death b\r\n"
    		+ "ON a.individual_uuid=b.individual_uuid  WHERE a.uuid IN (SELECT socialgroup_uuid FROM residency WHERE endType=1)")
    Long dthhoh();
    
    

}

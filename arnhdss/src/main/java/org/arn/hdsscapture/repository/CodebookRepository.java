package org.arn.hdsscapture.repository;

import java.util.List;

import org.arn.hdsscapture.entity.Codebook;
import org.arn.hdsscapture.entity.CommunityReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CodebookRepository extends JpaRepository <Codebook, Integer> {
	
	@Query(nativeQuery = true, value = "SELECT * from codebook WHERE id = :uuid")
	List<Codebook> findCode(@Param("uuid") String uuid);
	
	@Query(nativeQuery = true, value = "SELECT COUNT(*) FROM codebook WHERE codeFeature = :codeFeature AND codeValue = :codeValue")
	int codeErr(@Param("codeFeature") String codeFeature, @Param("codeValue") Integer codeValue);
	
	@Query(nativeQuery = true, value = "SELECT COUNT(*) FROM codebook WHERE codeFeature = :codeFeature ")
	int codeErrs(@Param("codeFeature") String codeFeature);
	
	@Query(nativeQuery = true, value = "SELECT * FROM codebook WHERE codeFeature='odk_gender'")
	List<Codebook> odk_gender();
	
	@Query(nativeQuery = true, value = "SELECT * FROM codebook Where codeFeature='itemlist'")
	List<Codebook> itemlist();
	
	@Query(nativeQuery = true, value = "SELECT * FROM codebook group by codeFeature")
	List<Codebook> codeFeature();
	
	@Query(nativeQuery = true, value = "SELECT * FROM codebook Where codeFeature='enabled'")
	List<Codebook> enabled();
	
	//Inmigration
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='comingfrom' ")
	List<Codebook> origin();
	
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='migType' ")
	List<Codebook> migtype();
	
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='reason' ")
	List<Codebook> reason();
	
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='occupation' ")
	List<Codebook> occupation();
	
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='farm' ")
	List<Codebook> farm();
	
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='livestock' ")
	List<Codebook> livestock();
	
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='cashcrops' ")
	List<Codebook> cashcrops();
	
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='food' ")
	List<Codebook> food();
	
	//Outmigration
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='whereoutside' ")
	List<Codebook> destination();
	
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='reasonForOutMigration' ")
	List<Codebook> reasons();
	
	//Death
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='deathPlace' ")
	List<Codebook> deathPlace();
	
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='deathCause' ")
	List<Codebook> deathCause();
	
	//Yes/No
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='complete' ")
	List<Codebook> yn();
	
	//Pregnancy
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='notdel' ")
	List<Codebook> notdel();
	
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='bnetSou' ")
	List<Codebook> bnetSou();
	
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='yn_anc' ")
	List<Codebook> yn_anc();
	
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='assist' ")
	List<Codebook> assist();
	
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='bnetLoc' ")
	List<Codebook> bnetLoc();
	
	//Pregnancy Outcome
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='outcometype' ")
	List<Codebook> outcometype();
	
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='birthPlace' ")
	List<Codebook> birthPlace();
	
	//Same as pregnancy
//	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='notdel' ")
//	List<Codebook> notdel();
	
//	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='yn_anc' ")
//	List<Codebook> yn_anc();
//	
//	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='assist' ")
//	List<Codebook> assist();
	
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='howdel' ")
	List<Codebook> howdel();
	
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='size' ")
	List<Codebook> size();
	
	//Demographic
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='education' ")
	List<Codebook> education();
	
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='marital' ")
	List<Codebook> marital();
	
//	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='occupation' ")
//	List<Codebook> occupation();
	
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='religion' ")
	List<Codebook> religion();
	
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='tribe' ")
	List<Codebook> tribe();
	
	//Relationship
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='relationshipType' ")
	List<Codebook> start();
	
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='relendType' ")
	List<Codebook> end();
	
}

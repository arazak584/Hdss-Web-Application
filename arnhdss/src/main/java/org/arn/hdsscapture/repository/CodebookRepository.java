package org.arn.hdsscapture.repository;

import java.util.List;

import org.arn.hdsscapture.entity.Codebook;
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
	
	@Query(nativeQuery = true, value = "SELECT * FROM codebook Where codeFeature='complete'")
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
	
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='fam_plan_method' ")
	List<Codebook> fam_plan_method();
	
	
	//Pregnancy Outcome
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='outcometype' ")
	List<Codebook> outcometype();
	
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='birthPlace' ")
	List<Codebook> birthPlace();
	

	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='feed_chd' ")
	List<Codebook> feed_chd();
	
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='more_chd' ")
	List<Codebook> more_chd();
	
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='preg_chd' ")
	List<Codebook> preg_chd();      
	
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='how_lng' ")
	List<Codebook> how_lng();
	
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
	
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='akan' ")
	List<Codebook> akan();
	
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='denomination' ")
	List<Codebook> deno();
	
	//Relationship
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='relationshipType' ")
	List<Codebook> start();
	
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='relendType' ")
	List<Codebook> end();
	
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='H2O_FCORRES' ")
	List<Codebook> h2o_fcorres();
	
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='MARITAL_SCORRES' ")
	List<Codebook> marital_sco();
	
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='rltnhead' ")
	List<Codebook> rltnhead();
	
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='TOILET_FCORRES' ")
	List<Codebook> toilet_fcorres();
	
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='TOILET_LOC_FCORRES' ")
	List<Codebook> toilet_loc_fcorres();
	
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='pet_vac' ")
	List<Codebook> pet_vac();
	
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='TOILET_SHARE_NUM_FCORRES' ")
	List<Codebook> toilet_share_num_fcorres();
	
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='EXT_WALL_FCORRES' ")
	List<Codebook> ext_wall_fcorres();
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='FLOOR_FCORRES' ")
	List<Codebook> floor_fcorres();
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='ROOF_FCORRES' ")
	List<Codebook> roof_fcorres();
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='MOBILE_ACCESS_FCORRES' ")
	List<Codebook> mobile_access_fcorres();
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='OWN_RENT_SCORRES' ")
	List<Codebook> own_rent_scorres();
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='JOB' ")
	List<Codebook> job();
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='STOVE_FCORRES' ")
	List<Codebook> stove_fcorres();
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='COOKING_LOC_FCORRES' ")
	List<Codebook> cooking_loc_fcorres();
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='FRQ' ")
	List<Codebook> frq();
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='HC' ")
	List<Codebook> hcard();
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='reavac' ")
	List<Codebook> reavac();
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='onet' ")
	List<Codebook> onet();
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='rea' ")
	List<Codebook> rea();
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='scar' ")
	List<Codebook> scar();
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='HL' ")
	List<Codebook> hl();
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='nhis_no' ")
	List<Codebook> nhis_no();
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='nhis' ")
	List<Codebook> nhis();
	@Query(nativeQuery = true, value ="SELECT  * from codebook v where v.codeFeature='crop' ")
	List<Codebook> crop();
	
	List<Codebook> findByCodeFeature(String codeFeature);
	
	
}

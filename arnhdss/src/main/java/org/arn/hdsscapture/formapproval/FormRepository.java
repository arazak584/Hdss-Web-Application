package org.arn.hdsscapture.formapproval;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FormRepository extends JpaRepository <FormStatus, Long>{

	//Inmigration
	
	@Query(nativeQuery = true, value ="SELECT  count(DISTINCT v.uuid) from inmigration v where `status`=0 AND v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
    Long Img0();
	
	@Query(nativeQuery = true, value ="SELECT  count(DISTINCT v.uuid) from inmigration v where `status`=1 AND v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
    Long Img1();
	
	@Query(nativeQuery = true, value ="SELECT  count(DISTINCT v.uuid) from inmigration v where `status`=2 AND v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
    Long Img2();
	
	@Query(nativeQuery = true, value ="SELECT  count(DISTINCT v.uuid) from inmigration v where `status`=3 AND v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
    Long Img3();
	
	//Outmigration
	
	@Query(nativeQuery = true, value ="SELECT  count(DISTINCT v.uuid) from outmigration v where `status`=0 AND v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	Long Omg0();
		
	@Query(nativeQuery = true, value ="SELECT  count(DISTINCT v.uuid) from outmigration v where `status`=1 AND v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	Long Omg1();
		
	@Query(nativeQuery = true, value ="SELECT  count(DISTINCT v.uuid) from outmigration v where `status`=2 AND v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	 Long Omg2();
		
	@Query(nativeQuery = true, value ="SELECT  count(DISTINCT v.uuid) from outmigration v where `status`=3 AND v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	Long Omg3();
	

	//Death
	@Query(nativeQuery = true, value ="SELECT  count(DISTINCT v.uuid) from death v where `status`=0 AND v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	Long Dth0();
		
	@Query(nativeQuery = true, value ="SELECT  count(DISTINCT v.uuid) from death v where `status`=1 AND v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	Long Dth1();
		
	@Query(nativeQuery = true, value ="SELECT  count(DISTINCT v.uuid) from death v where `status`=2 AND v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	 Long Dth2();
		
	@Query(nativeQuery = true, value ="SELECT  count(DISTINCT v.uuid) from death v where `status`=3 AND v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	Long Dth3();
	
	//Pregnancy
	@Query(nativeQuery = true, value ="SELECT  count(DISTINCT v.uuid) from pregnancyobservation v where `status`=0 AND v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	Long Preg0();
		
	@Query(nativeQuery = true, value ="SELECT  count(DISTINCT v.uuid) from pregnancyobservation v where `status`=1 AND v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	Long Preg1();
		
	@Query(nativeQuery = true, value ="SELECT  count(DISTINCT v.uuid) from pregnancyobservation v where `status`=2 AND v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	 Long Preg2();
		
	@Query(nativeQuery = true, value ="SELECT  count(DISTINCT v.uuid) from pregnancyobservation v where `status`=3 AND v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	Long Preg3();
	
	//Outcome
	@Query(nativeQuery = true, value ="SELECT  count(DISTINCT v.uuid) from pregnancyoutcome v where `status`=0 AND v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	Long Out0();
		
	@Query(nativeQuery = true, value ="SELECT  count(DISTINCT v.uuid) from pregnancyoutcome v where `status`=1 AND v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	Long Out1();
		
	@Query(nativeQuery = true, value ="SELECT  count(DISTINCT v.uuid) from pregnancyoutcome v where `status`=2 AND v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	Long Out2();
		
	@Query(nativeQuery = true, value ="SELECT  count(DISTINCT v.uuid) from pregnancyoutcome v where `status`=3 AND v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	Long Out3();	
	
	//Demographic
	@Query(nativeQuery = true, value ="SELECT  count(DISTINCT v.individual_uuid) from demographic v where `status`=0 AND v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	Long Demo0();
		
	@Query(nativeQuery = true, value ="SELECT  count(DISTINCT v.individual_uuid) from demographic v where `status`=1 AND v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	Long Demo1();
		
	@Query(nativeQuery = true, value ="SELECT  count(DISTINCT v.individual_uuid) from demographic v where `status`=2 AND v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	Long Demo2();
		
	@Query(nativeQuery = true, value ="SELECT  count(DISTINCT v.individual_uuid) from demographic v where `status`=3 AND v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	Long Demo3();
	
	//Relationship
	@Query(nativeQuery = true, value ="SELECT  count(DISTINCT v.uuid) from relationship v where `status`=0 AND v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	Long Rel0();
		
	@Query(nativeQuery = true, value ="SELECT  count(DISTINCT v.uuid) from relationship v where `status`=1 AND v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	Long Rel1();
		
	@Query(nativeQuery = true, value ="SELECT  count(DISTINCT v.uuid) from relationship v where `status`=2 AND v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	Long Rel2();
		
	@Query(nativeQuery = true, value ="SELECT  count(DISTINCT v.uuid) from relationship v where `status`=3 AND v.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	Long Rel3();
	
	//SES
	@Query(nativeQuery = true, value ="SELECT  count(DISTINCT v.uuid) from sociodemographic v where `status`=0 AND v.formcompldate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	Long Ses0();
		
	@Query(nativeQuery = true, value ="SELECT  count(DISTINCT v.uuid) from sociodemographic v where `status`=1 AND v.formcompldate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	Long Ses1();
		
	@Query(nativeQuery = true, value ="SELECT  count(DISTINCT v.uuid) from sociodemographic v where `status`=2 AND v.formcompldate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	Long Ses2();
		
	@Query(nativeQuery = true, value ="SELECT  count(DISTINCT v.uuid) from sociodemographic v where `status`=3 AND v.formcompldate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	Long Ses3();
	
	//Vaccination
	@Query(nativeQuery = true, value ="SELECT  count(DISTINCT v.uuid) from vaccination v where `status`=0 AND v.editDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	Long Vac0();
		
	@Query(nativeQuery = true, value ="SELECT  count(DISTINCT v.uuid) from vaccination v where `status`=1 AND v.editDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	Long Vac1();
		
	@Query(nativeQuery = true, value ="SELECT  count(DISTINCT v.uuid) from vaccination v where `status`=2 AND v.editDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	Long Vac2();
		
	@Query(nativeQuery = true, value ="SELECT  count(DISTINCT v.uuid) from vaccination v where `status`=3 AND v.editDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	Long Vac3();
	
	
}

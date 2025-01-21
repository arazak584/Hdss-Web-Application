package org.arn.hdsscapture.repository;

import java.util.List;

import org.arn.hdsscapture.entity.Morbidity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MorbidityRepository extends JpaRepository <Morbidity, String> {

	@Query(nativeQuery = true, value = "SELECT a.* FROM morbidity a WHERE a.`status`=2 AND a.insertDate >= (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1) AND a.fw_uuid = :fw")
    List<Morbidity> rejected(@Param("fw")String fw);
	
	@Query(nativeQuery = true, value = "SELECT * FROM morbidity WHERE uuid = :uuid LIMIT 1")
    List<Morbidity> findByUuids(@Param("uuid") String uuid);
	
	@Query(nativeQuery = true, value ="SELECT a.uuid, approveDate, asthma, asthma_dur, asthma_trt, `comment`,compno,diabetes,diabetes_dur,diabetes_trt,\r\n"
			+ "epilepsy,epilepsy_dur,epilepsy_trt,fever,fever_days,fever_treat,fw_name,heart,heart_dur,heart_trt,hypertension,\r\n"
			+ "hypertension_dur,hypertension_trt,ind_name,individual_uuid,location_uuid,sickle,sickle_dur,sickle_trt,socialgroup_uuid,`status`,\r\n"
			+ "stroke,stroke_dur,stroke_trt,supervisor,dob as insertDate,TIMESTAMPDIFF(year,dob,CURDATE()) as fw_uuid\r\n"
			+ "FROM morbidity a INNER JOIN individual b ON a.individual_uuid=b.uuid\r\n"
			+ " where a.insertDate >= (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1) AND a.uuid = :uuid LIMIT 1")
	List<Morbidity> findByUuid(@Param("uuid") String uuid);
	
	@Query(nativeQuery = true, value ="SELECT * from morbidity "
			+ "where fw_uuid= :fw AND insertDate >= (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	List<Morbidity> findItem(@Param("fw") String fw);
	
	@Query(nativeQuery = true, value ="SELECT * from morbidity "
			+ " where `status`=3 AND insertDate >= (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	List<Morbidity> findItems();
	
	@Query(nativeQuery = true, value ="SELECT * from morbidity "
			+ "where `status`=2 AND insertDate >= (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	List<Morbidity> findRej();

}

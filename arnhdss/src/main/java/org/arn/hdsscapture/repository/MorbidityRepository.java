package org.arn.hdsscapture.repository;

import java.util.List;

import org.arn.hdsscapture.entity.Morbidity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MorbidityRepository extends JpaRepository <Morbidity, String> {

	@Query(nativeQuery = true, value = "SELECT a.* FROM morbidity a WHERE a.`status`=2 AND a.insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
    List<Morbidity> rejected();
	
	@Query(nativeQuery = true, value = "SELECT * FROM morbidity WHERE uuid = :uuid LIMIT 1")
    List<Morbidity> findByUuids(@Param("uuid") String uuid);
	
	@Query(nativeQuery = true, value ="SELECT * from morbidity "
			+ " where insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1) AND uuid = :uuid LIMIT 1")
	List<Morbidity> findByUuid(@Param("uuid") String uuid);
	
	@Query(nativeQuery = true, value ="SELECT * from morbidity "
			+ "where fw_uuid= :fw AND insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	List<Morbidity> findItem(@Param("fw") String fw);
	
	@Query(nativeQuery = true, value ="SELECT * from morbidity "
			+ " where `status`=3 AND insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	List<Morbidity> findItems();
	
	@Query(nativeQuery = true, value ="SELECT * from morbidity "
			+ "where `status`=2 AND insertDate > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	List<Morbidity> findRej();

}

package org.arn.hdsscapture.repository;

import java.util.List;

import org.arn.hdsscapture.entity.DataDictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DictionaryRepository extends JpaRepository <DataDictionary, Integer> {

	@Query(nativeQuery = true, value = "SELECT * from datadictionary WHERE id = :uuid")
	List<DataDictionary> findId(@Param("uuid") Integer uuid);
	
	@Query(nativeQuery = true, value = "SELECT * from datadictionary WHERE event = :event")
	List<DataDictionary> findEvent(@Param("event") String event);
	
	@Query(nativeQuery = true, value = "SELECT * from datadictionary GROUP BY event")
	List<DataDictionary> findEid();
}

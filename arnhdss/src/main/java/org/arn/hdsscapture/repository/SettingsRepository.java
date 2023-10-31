package org.arn.hdsscapture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import org.arn.hdsscapture.entity.Settings;

public interface SettingsRepository extends JpaRepository <Settings, Integer> {
	
	@Query(nativeQuery = true, value = "SELECT * from settings WHERE id = :id")
	List<Settings> findBy(@Param("id") Integer id);
	

}

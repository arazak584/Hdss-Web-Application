package org.arn.hdsscapture.repository;

import java.util.List;

import org.arn.hdsscapture.entity.Codebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CodebookRepository extends JpaRepository <Codebook, Integer> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM codebook WHERE codeFeature='odk_gender'")
	List<Codebook> odk_gender();
	
	@Query(nativeQuery = true, value = "SELECT * FROM codebook Where codeFeature='modules'")
	List<Codebook> modules();
	
	@Query(nativeQuery = true, value = "SELECT * FROM codebook Where codeFeature='enabled'")
	List<Codebook> enabled();

}

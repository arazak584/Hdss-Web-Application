package org.arn.hdsscapture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.arn.hdsscapture.entity.Round;

public interface RoundRepository extends JpaRepository <Round, String> {
	
	
	@Query(nativeQuery = true, value = "SELECT * from round ORDER BY roundNumber DESC limit 1")
	List<Round> findAll();
	
	@Query(nativeQuery = true, value = "SELECT * from round ORDER BY roundNumber")
	List<Round> findRound();
	
	@Query(nativeQuery = true, value = "SELECT * from round WHERE roundNumber = :roundNumber ORDER BY roundNumber")
	List<Round> findFieldworkerByroundNumber(@Param("roundNumber") Integer roundNumber);

}

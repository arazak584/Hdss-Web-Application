package com.arn.hdss.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.arn.hdss.entity.Round;

public interface RoundRepository extends JpaRepository <Round, Long> {
	
	
	@Query(nativeQuery = true, value = "SELECT * from round ORDER BY roundNumber DESC limit 1")
	List<Round> findAll();
	
	@Query(nativeQuery = true, value = "SELECT * from round ORDER BY roundNumber")
	List<Round> findRound();
	
	

}

package com.arn.hdss.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.arn.hdss.entity.Residency;

public interface ResidencyRepository extends JpaRepository <Residency, String> {

	@Query(nativeQuery = true, value = "SELECT * from membership where endType!=0")
	List<Residency> findAll();

}

package com.arn.hdss.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arn.hdss.entity.Demographic;

public interface DemographicRepository extends JpaRepository <Demographic, String> {



}

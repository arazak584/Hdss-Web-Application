package com.arn.hdss.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arn.hdss.entity.Individual;

public interface IndividualRepository extends JpaRepository <Individual, String> {



}

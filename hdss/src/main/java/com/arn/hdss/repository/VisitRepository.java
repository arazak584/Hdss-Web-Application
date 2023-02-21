package com.arn.hdss.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.arn.hdss.entity.Visit;

public interface VisitRepository extends JpaRepository <Visit, String> {


}

package com.arn.hdss.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arn.hdss.entity.Death;

public interface DeathRepository extends JpaRepository <Death, String> {


}

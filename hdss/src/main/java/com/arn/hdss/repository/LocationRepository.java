package com.arn.hdss.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arn.hdss.entity.Location;

public interface LocationRepository extends JpaRepository <Location, String> {


}

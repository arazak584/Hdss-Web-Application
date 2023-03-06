package com.arn.hdss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.arn.hdss.entity.Country;

public interface CountryRepository extends JpaRepository <Country, String> {

	
}

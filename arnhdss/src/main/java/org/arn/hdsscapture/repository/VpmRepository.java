package org.arn.hdsscapture.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.arn.hdsscapture.entity.Vpm;

public interface VpmRepository extends JpaRepository <Vpm, String> {


}

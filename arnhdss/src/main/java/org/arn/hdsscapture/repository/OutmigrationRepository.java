package org.arn.hdsscapture.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.arn.hdsscapture.entity.Outmigration;

public interface OutmigrationRepository extends JpaRepository <Outmigration, String> {


}

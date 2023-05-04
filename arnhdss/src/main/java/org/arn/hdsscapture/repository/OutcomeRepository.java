package org.arn.hdsscapture.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.arn.hdsscapture.entity.Outcome;

public interface OutcomeRepository extends JpaRepository <Outcome, String> {


}

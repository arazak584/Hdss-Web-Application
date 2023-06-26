package org.arn.hdsscapture.repository;

import org.arn.hdsscapture.entity.Duplicate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DuplicateRepository extends JpaRepository <Duplicate, String> {



}

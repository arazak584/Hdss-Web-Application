package org.arn.hdsscapture.repository;

import org.arn.hdsscapture.entity.Codebook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodebookRepository extends JpaRepository <Codebook, Integer> {

}

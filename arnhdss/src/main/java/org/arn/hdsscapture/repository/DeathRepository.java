package org.arn.hdsscapture.repository;

import org.arn.hdsscapture.entity.Death;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DeathRepository extends JpaRepository <Death, String> {


}

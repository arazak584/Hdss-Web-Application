package org.arn.hdsscapture.repository;

import org.arn.hdsscapture.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository <Location, String> {



}

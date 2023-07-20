package org.arn.hdsscapture.repository;

import org.arn.hdsscapture.entity.ErrorLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorLogRepository extends JpaRepository <ErrorLog, Long> {

}

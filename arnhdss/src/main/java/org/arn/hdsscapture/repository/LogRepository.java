package org.arn.hdsscapture.repository;

import org.arn.hdsscapture.entity.LogBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository <LogBook, Integer> {
	


}

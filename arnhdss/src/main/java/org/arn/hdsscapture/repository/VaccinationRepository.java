package org.arn.hdsscapture.repository;

import java.util.List;

import org.arn.hdsscapture.entity.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VaccinationRepository extends JpaRepository <Vaccination, String> {

	@Query(nativeQuery = true, value = "SELECT a.* FROM vaccination a LEFT JOIN \r\n"
			+ "death b ON a.individual_uuid=b.individual_uuid where b.individual_uuid is null;")
	List<Vaccination> findVaccination();

}

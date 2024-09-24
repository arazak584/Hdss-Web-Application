package org.arn.hdsscapture.repository;

import java.util.List;

import org.arn.hdsscapture.entity.RegisterBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RegisterRepository extends JpaRepository <RegisterBook, String> {

	@Query(nativeQuery = true, value = "SELECT a.individual_uuid,concat(c.firstName,' ',c.lastName)as fw_uuid,a.insertDate,\r\n"
			+ "a.location_uuid,a.`name`,a.socialgroup_uuid,a.`status` from registry a INNER JOIN location b on a.location_uuid=b.uuid\r\n"
			+ " INNER JOIN fieldworker c on a.fw_uuid=c.fw_uuid WHERE b.compno = :compno AND DATE(a.insertDate) > (SELECT r.startDate from round r ORDER BY r.roundNumber DESC limit 1)")
	List<RegisterBook> Search(@Param("compno") String compno);


}

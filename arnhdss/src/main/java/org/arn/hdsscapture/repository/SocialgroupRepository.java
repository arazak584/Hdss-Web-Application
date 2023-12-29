package org.arn.hdsscapture.repository;

import java.util.List;

import org.arn.hdsscapture.entity.Socialgroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SocialgroupRepository extends JpaRepository <Socialgroup, String> {

	@Query(nativeQuery = true, value = "SELECT * from socialgroup LIMIT ?1 OFFSET ?2")
	List<Socialgroup> findSocialgroup(int pageSize, int offset);
}

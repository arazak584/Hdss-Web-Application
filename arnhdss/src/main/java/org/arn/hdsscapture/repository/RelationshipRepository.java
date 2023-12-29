package org.arn.hdsscapture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.arn.hdsscapture.entity.Relationship;

public interface RelationshipRepository extends JpaRepository <Relationship, String> {

	@Query(nativeQuery = true, value = "SELECT * from relationship WHERE endType=1 LIMIT ?1 OFFSET ?2")
	List<Relationship> findRelationship(int pageSize, int offset);
}

package org.arn.hdsscapture.repository;

import java.util.List;

import org.arn.hdsscapture.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
	
	@Query(nativeQuery = true, value = "SELECT * from user WHERE id = :id ORDER BY id")
	List<User> findUser(@Param("id") Long id);
	
}

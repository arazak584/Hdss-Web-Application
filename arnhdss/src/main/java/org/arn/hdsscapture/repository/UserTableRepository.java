package org.arn.hdsscapture.repository;

import java.util.Optional;

import org.arn.hdsscapture.entity.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserTableRepository extends JpaRepository<UserTable, String> {

	@Query("SELECT u FROM user_table u WHERE u.user_email = ?1")
	Optional<UserTable> findByUserEmail(String user_email);

}

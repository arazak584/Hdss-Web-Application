package org.arn.hdsscapture.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.arn.hdsscapture.entity.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserTableRepository extends JpaRepository<UserTable, String> {

	@Query("SELECT u FROM user_table u WHERE u.user_email = ?1")
	Optional<UserTable> findByUserEmail(String user_email);

	@Query("SELECT u.username, u.user_email, CONCAT(u.user_fname,' ',u.user_lname) AS userName "
			+ "FROM user_table u JOIN u.groups g WHERE g.group_role= :userRole "
			+ "AND (u.user_email LIKE :search OR CONCAT(u.user_fname,' ',u.user_lname) LIKE :search)")
	Set<Object[]> findReviewerUsers(@Param("userRole") String user, @Param("search") String search);

	@Query("SELECT u FROM user_table u WHERE u.user_email = :user_email")
	Optional<UserTable> findUserEmail(@Param("user_email") String user_email);
	
	@Query("SELECT u FROM user_table u WHERE u.username = :username OR u.user_email=:username ")
	Optional<UserTable> findByUsernameIgnoreCase(String username);
	
	@Query(nativeQuery = true, value = "SELECT * FROM user_table WHERE email_enabled=1")
    List<UserTable> ActiveUsers();
	

}

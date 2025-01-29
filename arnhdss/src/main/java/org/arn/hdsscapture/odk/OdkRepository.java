package org.arn.hdsscapture.odk;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OdkRepository extends JpaRepository <ODK, Long> {

	
	@Query(nativeQuery = true, value = "SELECT id,formID,insertDate,formName,formDesc,minAge,maxAge,csv,Case when gender=1 then 'Male' when gender=2 then\r\n"
			+ "'Female' else 'All' end as gender,Case when status=1 then 'Active' when status =3 then 'Dead' else '' end as status,enabled\r\n"
			+ " from odkform ORDER BY insertDate Desc")
	List<ODK> find();
	
	@Query(nativeQuery = true, value = "SELECT * from odkform WHERE enabled=1")
	List<ODK> findEnabled();
	
	@Query(nativeQuery = true, value = "SELECT * FROM odkform WHERE formID = ?1")
	Optional<ODK> findByformID(String formID);
	
	@Query(nativeQuery = true, value = "SELECT * from odkform WHERE id = :id ORDER BY id")
	List<ODK> findID(@Param("id") Long id);
	
	@Query("SELECT COUNT(o) > 0 FROM ODK o WHERE o.formID = :formID AND o.id <> :id")
	boolean existsByFormIDAndNotId(@Param("formID") String formID, @Param("id") Long id);


	

}

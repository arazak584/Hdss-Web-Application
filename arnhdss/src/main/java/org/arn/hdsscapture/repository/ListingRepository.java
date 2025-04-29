package org.arn.hdsscapture.repository;

import java.util.List;

import org.arn.hdsscapture.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ListingRepository extends JpaRepository <Listing, String> {

	@Query(nativeQuery = true, value = "SELECT * FROM listing WHERE edit_compno = 1")
    List<Listing> findEdit();

	

}

package org.arn.hdsscapture.repository;



import org.arn.hdsscapture.entity.Individual;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IndividualRepository extends JpaRepository <Individual, String> {

}

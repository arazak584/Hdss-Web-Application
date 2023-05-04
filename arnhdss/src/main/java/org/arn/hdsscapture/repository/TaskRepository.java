package org.arn.hdsscapture.repository;

import java.util.Optional;

import org.arn.hdsscapture.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository <Task, String> {

    Optional<Task> findByFileName(String fileName);

	Optional<Task> findById(String taskId);
    

}

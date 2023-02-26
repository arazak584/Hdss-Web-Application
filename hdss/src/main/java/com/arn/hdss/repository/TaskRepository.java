package com.arn.hdss.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arn.hdss.entity.Task;

public interface TaskRepository extends JpaRepository <Task, String> {

    Optional<Task> findByFileName(String fileName);

	Optional<Task> findById(Long taskId);
    

}

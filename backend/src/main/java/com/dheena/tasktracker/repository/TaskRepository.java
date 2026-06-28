package com.dheena.tasktracker.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dheena.tasktracker.entity.Task;
import com.dheena.tasktracker.entity.enums.TaskPriority;
import com.dheena.tasktracker.entity.enums.TaskStatus;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findByStatus(TaskStatus status, Pageable pageable);

    Page<Task> findByPriority(TaskPriority priority, Pageable pageable);

    Page<Task> findByStatusAndPriority(
            TaskStatus status,
            TaskPriority priority,
            Pageable pageable
    );
}
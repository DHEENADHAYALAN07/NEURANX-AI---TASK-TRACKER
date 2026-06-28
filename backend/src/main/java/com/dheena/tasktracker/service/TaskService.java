package com.dheena.tasktracker.service;

import org.springframework.data.domain.Page;

import com.dheena.tasktracker.dto.CreateTaskRequest;
import com.dheena.tasktracker.dto.TaskResponse;
import com.dheena.tasktracker.dto.UpdateTaskRequest;
import com.dheena.tasktracker.entity.enums.TaskPriority;
import com.dheena.tasktracker.entity.enums.TaskStatus;

public interface TaskService {

    TaskResponse createTask(CreateTaskRequest request);

    TaskResponse updateTask(Long id, UpdateTaskRequest request);

    void deleteTask(Long id);

    TaskResponse getTaskById(Long id);

    Page<TaskResponse> getAllTasks(
            TaskStatus status,
            TaskPriority priority,
            int page,
            int size,
            String sortBy,
            String direction
    );
}


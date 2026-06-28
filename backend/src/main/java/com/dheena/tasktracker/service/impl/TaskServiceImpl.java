package com.dheena.tasktracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dheena.tasktracker.dto.CreateTaskRequest;
import com.dheena.tasktracker.dto.TaskResponse;
import com.dheena.tasktracker.dto.UpdateTaskRequest;
import com.dheena.tasktracker.entity.Project;
import com.dheena.tasktracker.entity.Task;
import com.dheena.tasktracker.entity.enums.TaskPriority;
import com.dheena.tasktracker.entity.enums.TaskStatus;
import com.dheena.tasktracker.exception.ProjectNotFoundException;
import com.dheena.tasktracker.exception.TaskNotFoundException;
import com.dheena.tasktracker.mapper.TaskMapper;
import com.dheena.tasktracker.repository.ProjectRepository;
import com.dheena.tasktracker.repository.TaskRepository;
import com.dheena.tasktracker.service.TaskService;
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository,
                           ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }


    @Override
    public TaskResponse createTask(CreateTaskRequest request) {
        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() ->
                        new ProjectNotFoundException(request.getProjectId()));

        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(TaskStatus.TODO)
                .priority(request.getPriority())
                .dueDate(request.getDueDate())
                .project(project)
                .build();

        Task savedTask = taskRepository.save(task);
        return TaskMapper.toResponse(savedTask);
    }

    @Override
    public TaskResponse updateTask(Long id, UpdateTaskRequest request) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() ->
                        new ProjectNotFoundException(request.getProjectId()));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPriority());
        task.setDueDate(request.getDueDate());
        task.setProject(project);

        Task updatedTask = taskRepository.save(task);

        return TaskMapper.toResponse(updatedTask);
    }

    @Override
    public void deleteTask(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        taskRepository.delete(task);
    }

    @Override
    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        return TaskMapper.toResponse(task);
    }

    @Override
    public Page<TaskResponse> getAllTasks(TaskStatus status,
            TaskPriority priority,
            int page,
            int size,
            String sortBy,
            String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return taskRepository.findTasks(status, priority, pageable)
                .map(TaskMapper::toResponse);
    }
}
package com.dheena.tasktracker.service;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dheena.tasktracker.entity.Project;
import com.dheena.tasktracker.entity.Task;
import com.dheena.tasktracker.entity.enums.TaskPriority;
import com.dheena.tasktracker.entity.enums.TaskStatus;
import com.dheena.tasktracker.repository.ProjectRepository;
import com.dheena.tasktracker.repository.TaskRepository;

@SpringBootTest
class TaskServiceTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    void contextLoads() {
        assertNotNull(taskRepository);
        assertNotNull(projectRepository);
    }

    @Test
    void shouldCreateProject() {

        Project project = Project.builder()
                .name("JUnit Project")
                .description("Testing Project")
                .build();

        Project savedProject = projectRepository.save(project);

        assertNotNull(savedProject.getId());
        assertEquals("JUnit Project", savedProject.getName());
    }


    @Test
    void shouldCreateTask() {

        // Create Project
        Project project = Project.builder()
                .name("Project 1")
                .description("JUnit Project")
                .build();

        project = projectRepository.save(project);

        // Create Task
        Task task = Task.builder()
                .title("Learn Spring Boot")
                .description("JUnit CRUD Test")
                .status(TaskStatus.TODO)
                .priority(TaskPriority.HIGH)
                .dueDate(LocalDate.now().plusDays(7))
                .project(project)
                .build();

        Task savedTask = taskRepository.save(task);

        assertNotNull(savedTask.getId());
        assertEquals("Learn Spring Boot", savedTask.getTitle());
        assertEquals(TaskStatus.TODO, savedTask.getStatus());
        assertEquals(TaskPriority.HIGH, savedTask.getPriority());
        assertEquals(project.getId(), savedTask.getProject().getId());
    }


    @Test
    void shouldUpdateTask() {

        // Create Project
        Project project = Project.builder()
                .name("Project")
                .description("Testing")
                .build();

        project = projectRepository.save(project);

        // Create Task
        Task task = Task.builder()
                .title("Old Title")
                .description("Old Description")
                .status(TaskStatus.TODO)
                .priority(TaskPriority.MEDIUM)
                .dueDate(LocalDate.now().plusDays(3))
                .project(project)
                .build();

        task = taskRepository.save(task);

        // Update Task
        task.setTitle("Updated Title");
        task.setStatus(TaskStatus.DONE);

        Task updatedTask = taskRepository.save(task);

        assertEquals("Updated Title", updatedTask.getTitle());
        assertEquals(TaskStatus.DONE, updatedTask.getStatus());
    }


    @Test
    void shouldDeleteTask() {

        // Create Project
        Project project = Project.builder()
                .name("Delete Project")
                .description("JUnit Delete Test")
                .build();

        project = projectRepository.save(project);

        // Create Task
        Task task = Task.builder()
                .title("Task To Delete")
                .description("Delete Test")
                .status(TaskStatus.TODO)
                .priority(TaskPriority.LOW)
                .dueDate(LocalDate.now().plusDays(5))
                .project(project)
                .build();

        task = taskRepository.save(task);

        Long taskId = task.getId();

        // Delete Task
        taskRepository.delete(task);

        // Verify
        assertFalse(taskRepository.findById(taskId).isPresent());
    }

}
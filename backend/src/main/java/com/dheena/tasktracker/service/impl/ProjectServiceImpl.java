package com.dheena.tasktracker.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dheena.tasktracker.entity.Project;
import com.dheena.tasktracker.exception.ProjectNotFoundException;
import com.dheena.tasktracker.repository.ProjectRepository;
import com.dheena.tasktracker.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));
    }
}
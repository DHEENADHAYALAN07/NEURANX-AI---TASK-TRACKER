package com.dheena.tasktracker.service;

import java.util.List;

import com.dheena.tasktracker.entity.Project;

public interface ProjectService {

    Project createProject(Project project);

    List<Project> getAllProjects();

    Project getProjectById(Long id);
}
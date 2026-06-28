package com.dheena.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dheena.tasktracker.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
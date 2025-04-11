package com.example.ems.services;

import com.example.ems.DTO.ProjectDTO;

import java.util.List;

public interface ProjectService {
    List<ProjectDTO> getAllProjects();
    List<ProjectDTO> getProjectsByStatus(String status);
    ProjectDTO getProjectById(Long id);
    ProjectDTO createProject(ProjectDTO dto);
    ProjectDTO updateProject(Long id, ProjectDTO dto);
    void deleteProject(Long id);
}
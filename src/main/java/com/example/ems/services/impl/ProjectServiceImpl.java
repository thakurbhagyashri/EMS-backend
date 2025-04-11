package com.example.ems.services.impl;

import com.example.ems.DTO.ProjectDTO;
import com.example.ems.entities.Project;
import com.example.ems.exceptions.InvalidInputException;
import com.example.ems.exceptions.ResourceNotFoundException;
import com.example.ems.mappers.ProjectMapper;
import com.example.ems.repositories.ProjectRepository;
import com.example.ems.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(ProjectMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectDTO> getProjectsByStatus(String status) {
        try {
            Project.ProjectStatus projectStatus = Project.ProjectStatus.valueOf(status.toUpperCase());
            return projectRepository.findByStatus(projectStatus).stream()
                    .map(ProjectMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException("Invalid project status: " + status);
        }
    }

    @Override
    public ProjectDTO getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + id));
        return ProjectMapper.toDTO(project);
    }

    @Override
    public ProjectDTO createProject(ProjectDTO dto) {
        if (dto.getStartDate().isAfter(dto.getEndDate())) {
            throw new InvalidInputException("Start date cannot be after end date");
        }
        Project saved = projectRepository.save(ProjectMapper.toEntity(dto));
        return ProjectMapper.toDTO(saved);
    }

    @Override
    public ProjectDTO updateProject(Long id, ProjectDTO dto) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + id));

        if (dto.getStartDate().isAfter(dto.getEndDate())) {
            throw new InvalidInputException("Start date cannot be after end date");
        }

        project.setName(dto.getName());
        project.setDescription(dto.getDescription());
        project.setStartDate(dto.getStartDate());
        project.setEndDate(dto.getEndDate());
        project.setStatus(dto.getStatus());

        return ProjectMapper.toDTO(projectRepository.save(project));
    }

    @Override
    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new ResourceNotFoundException("Project not found with id " + id);
        }
        projectRepository.deleteById(id);
    }
}

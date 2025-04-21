package com.example.ems.mapper;

import com.example.ems.dto.ProjectDTO;
import com.example.ems.entities.Project;

public class ProjectMapper {

    public static ProjectDTO toDTO(Project project) {
        return ProjectDTO.builder()
                .projectId(project.getProjectId())
                .name(project.getName())
                .description(project.getDescription())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .status(project.getStatus())
                .build();
    }

    public static Project toEntity(ProjectDTO dto) {
        return Project.builder()
                .projectId(dto.getProjectId())
                .name(dto.getName())
                .description(dto.getDescription())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .status(dto.getStatus())
                .build();
    }
}
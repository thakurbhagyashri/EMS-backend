package com.example.ems.service;

import com.example.ems.dto.ProjectAssignmentDTO;

import java.util.List;

public interface ProjectAssignmentService {
    List<ProjectAssignmentDTO> getAssignmentsByEmployeeId(Long employeeId);
    ProjectAssignmentDTO assignProject(Long employeeId, ProjectAssignmentDTO dto);
    ProjectAssignmentDTO updateAssignment(Long employeeId, Long assignmentId, ProjectAssignmentDTO dto);

}

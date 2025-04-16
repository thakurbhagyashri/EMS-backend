package com.example.ems.service;


import com.example.ems.DTO.ProjectAssignmentDTO;
import com.example.ems.entities.Employee;
import com.example.ems.entities.Project;
import com.example.ems.entities.ProjectAssignment;
import com.example.ems.repositories.EmployeeRepository;
import com.example.ems.repositories.ProjectAssignmentRepository;
import com.example.ems.repositories.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectAssignmentServiceImpl implements ProjectAssignmentService {

    private final ProjectAssignmentRepository assignmentRepository;
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public List<ProjectAssignmentDTO> getAssignmentsByEmployeeId(Long employeeId) {
        List<ProjectAssignment> assignments = assignmentRepository.findByEmployee_EmployeeId(employeeId);
        return assignments.stream()
                .map(a -> modelMapper.map(a, ProjectAssignmentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProjectAssignmentDTO assignProject(Long employeeId, ProjectAssignmentDTO dto) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Project project = projectRepository.findById(dto.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        ProjectAssignment assignment = modelMapper.map(dto, ProjectAssignment.class);
        assignment.setEmployee(employee);
        assignment.setProject(project);

        ProjectAssignment saved = assignmentRepository.save(assignment);
        return modelMapper.map(saved, ProjectAssignmentDTO.class);
    }

    @Override
    @Transactional
    public ProjectAssignmentDTO updateAssignment(Long employeeId, Long assignmentId, ProjectAssignmentDTO dto) {
        ProjectAssignment existing = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));

        if (!existing.getEmployee().getEmployeeId().equals(employeeId)) {
            throw new RuntimeException("Assignment does not belong to this employee");
        }

        Project project = projectRepository.findById(dto.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        // Update values
        existing.setProject(project);
        existing.setStartDate(dto.getStartDate());
        existing.setEndDate(dto.getEndDate());
        existing.setAllocationPercentage(dto.getAllocationPercentage());
        existing.setRole(dto.getRole());
        existing.setResponsibilities(dto.getResponsibilities());

        ProjectAssignment updated = assignmentRepository.save(existing);
        return modelMapper.map(updated, ProjectAssignmentDTO.class);
    }
}

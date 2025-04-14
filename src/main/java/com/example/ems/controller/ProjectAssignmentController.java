package com.example.ems.controller;

import com.example.ems.DTO.ProjectAssignmentDTO;
import com.example.ems.service.ProjectAssignmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees/{employeeId}/projects")
@RequiredArgsConstructor
public class ProjectAssignmentController {

   /* private final ProjectAssignmentService projectAssignmentService;

    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ProjectAssignmentDTO>> getProjectsByEmployee(@PathVariable Long employeeId) {
        List<ProjectAssignmentDTO> assignments = projectAssignmentService.getAssignmentsByEmployeeId(employeeId);
        return ResponseEntity.ok(assignments);
    }

    @PostMapping
    public ResponseEntity<ProjectAssignmentDTO> assignProjectToEmployee(
            @PathVariable Long employeeId,
            @RequestBody ProjectAssignmentDTO dto
    ) {
        ProjectAssignmentDTO savedDto = projectAssignmentService.assignProject(employeeId, dto);
        return ResponseEntity.ok(savedDto);
    }

    @PutMapping("/{assignmentId}")
    public ResponseEntity<ProjectAssignmentDTO> updateAssignment(
            @PathVariable Long employeeId,
            @PathVariable Long assignmentId,
            @RequestBody ProjectAssignmentDTO dto
    ) {
        ProjectAssignmentDTO updatedDto = projectAssignmentService.updateAssignment(employeeId, assignmentId, dto);
        return ResponseEntity.ok(updatedDto);
    }*/
}

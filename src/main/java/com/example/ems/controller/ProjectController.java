package com.example.ems.controller;

import com.example.ems.DTO.ProjectDTO;
import com.example.ems.exceptions.ResourceNotFoundException;
import com.example.ems.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAllProjects(@RequestParam(required = false) String status) {
        if (status != null) {
            return ResponseEntity.ok(projectService.getProjectsByStatus(status));
        }
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getById(@PathVariable Long id) {
        ProjectDTO dto = projectService.getProjectById(id);
        if (dto == null) {
            throw new ResourceNotFoundException("Project not found with ID: " + id);
        }
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ProjectDTO> create(@RequestBody ProjectDTO dto) {
        return ResponseEntity.status(201).body(projectService.createProject(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDTO> update(@PathVariable Long id, @RequestBody ProjectDTO dto) {
        return ResponseEntity.ok(projectService.updateProject(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}

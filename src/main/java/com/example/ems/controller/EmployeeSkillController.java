package com.example.ems.controller;

import com.example.ems.DTO.EmployeeSkillDTO;
import com.example.ems.entities.EmployeeSkill;
import com.example.ems.service.EmployeeSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeSkillController {

    private final EmployeeSkillService employeeSkillService;

    @PostMapping("/{employeeId}/skills")
    public ResponseEntity<EmployeeSkillDTO> addEmployeeSkill(
            @PathVariable Long employeeId,
            @RequestBody EmployeeSkillDTO dto) {
        return ResponseEntity.ok(employeeSkillService.addSkill(employeeId, dto));
    }

    @GetMapping("/{employeeId}/skills")
    public ResponseEntity<List<EmployeeSkillDTO>> getEmployeeSkills(@PathVariable Long employeeId) {
        return ResponseEntity.ok(employeeSkillService.getSkill(employeeId));
    }

    @PutMapping("/{employeeId}/skills/{skillId}")
    public ResponseEntity<EmployeeSkillDTO> updateEmployeeSkill(
            @PathVariable Long employeeId,
            @PathVariable Long skillId,
            @RequestBody EmployeeSkillDTO dto) {
        return ResponseEntity.ok(employeeSkillService.updateSkill(employeeId, skillId, dto));
    }
}

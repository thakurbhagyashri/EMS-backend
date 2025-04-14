package com.example.ems.controller;

import com.example.ems.DTO.EmployeeSkillDTO;
import com.example.ems.entities.EmployeeSkill;
import com.example.ems.service.EmployeeSkillService;
import com.example.ems.service.EmployeeSkillServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees/{employeeId}")
public class EmployeeSkillController {
    @Autowired
    private final EmployeeSkillServiceImpl employeeSkillServiceImpl;

    @PostMapping("/addEmployeeSkill")
    public ResponseEntity<EmployeeSkillDTO> addEmployeeSkill(
            @PathVariable Long employeeId,
            @RequestBody EmployeeSkillDTO dto) {
        return ResponseEntity.ok(employeeSkillServiceImpl.addEmployeeSkill(employeeId, dto));
    }





    @GetMapping("/getAllEmployeeSkills")
    public ResponseEntity<List<EmployeeSkillDTO>> getAllEmployeeSkills(@PathVariable Long employeeId) {
        return ResponseEntity.ok(employeeSkillServiceImpl.getAllEmployeeSkills(employeeId));
    }

    @PutMapping("/skills/{skillId}")
    public ResponseEntity<EmployeeSkillDTO> updateEmployeeSkill(
            @PathVariable Long employeeId,
            @PathVariable Long skillId,
            @RequestBody EmployeeSkillDTO dto) {
        return ResponseEntity.ok(employeeSkillServiceImpl.updateSkill(employeeId, skillId, dto));
    }
}

package com.example.ems.controller;

import com.example.ems.entities.EmployeeSkill;
import com.example.ems.service.EmployeeSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeSkillController {

    @Autowired
    private EmployeeSkillService employeeSkillService;

    public EmployeeSkillController(EmployeeSkillService employeeSkillService) {
        this.employeeSkillService = employeeSkillService;
    }

    @GetMapping("/{id}/skill")
    public List<EmployeeSkill> fetchEmployeeSkills(@PathVariable Long id) {
        return employeeSkillService.fetchSkill(id);
    }

    @PostMapping("/{empid}/skill")
    public void addEmployeeSkill(@PathVariable Long empid,@RequestBody EmployeeSkill employeeSkill) {
        employeeSkillService.addSkill(employeeSkill);
    }

    @PutMapping("/{id}/skill/{Skillid}")
    public void updateEmployeeSkill(@PathVariable Long id,@PathVariable Long Skillid, @RequestBody EmployeeSkill employeeSkill) {
        employeeSkillService.updateSkill(id,Skillid,employeeSkill);
    }
}

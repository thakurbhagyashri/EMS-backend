package com.example.ems.repositories;

import com.example.ems.entities.EmployeeSkill;
import com.example.ems.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill, Long> {
    // Fetch EmployeeSkills by Employee ID
    List<EmployeeSkill> findByEmployeeEmployeeId(Long employeeId);

    // Add a new skill for an employee
    EmployeeSkill save(EmployeeSkill employeeSkill);

    // Update skill by Employee ID
    Optional<EmployeeSkill> findByEmployeeEmployeeIdAndSkill_SkillId(Long employeeId, Long skillId);
}



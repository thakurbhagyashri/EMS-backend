package com.example.ems.service;

import com.example.ems.DTO.EmployeeSkillDTO;
import com.example.ems.entities.EmployeeSkill;

import java.util.List;

public interface EmployeeSkillService {

    EmployeeSkillDTO addSkill(Long employeeId, EmployeeSkillDTO employeeSkillDTO);
    List<EmployeeSkillDTO> getSkill(Long employeeId);
    EmployeeSkillDTO updateSkill(Long employeeId, Long skillId, EmployeeSkillDTO dto);
}



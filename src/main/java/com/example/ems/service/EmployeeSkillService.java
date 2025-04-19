package com.example.ems.service;
import com.example.ems.dto.EmployeeSkillDTO;

import java.util.List;


public interface EmployeeSkillService {

       EmployeeSkillDTO addEmployeeSkill(Long employeeId, EmployeeSkillDTO employeeSkillDTO);
        List<EmployeeSkillDTO> getAllEmployeeSkills(Long employeeId);
        EmployeeSkillDTO updateSkill(Long employeeId, Long skillId, EmployeeSkillDTO dto);
    }



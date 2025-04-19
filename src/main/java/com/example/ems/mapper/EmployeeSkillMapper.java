package com.example.ems.mapper;

import com.example.ems.dto.EmployeeSkillDTO;
import com.example.ems.entities.Employee;
import com.example.ems.entities.EmployeeSkill;
import com.example.ems.entities.Skill;


public class EmployeeSkillMapper {


           public static EmployeeSkillDTO toDto(EmployeeSkill employeeSkill){
            return EmployeeSkillDTO.builder()
                    .employeeSkillId(employeeSkill.getEmployeeSkillId())
                    .employee_id(employeeSkill.getEmployee().getEmployeeId())
                    .skill_id(employeeSkill.getSkill().getSkillId())
                    .acquiredDate(employeeSkill.getAcquiredDate())
                    .lastUsedDate(employeeSkill.getLastUsedDate())
                    .proficiencyLevel(employeeSkill.getProficiencyLevel())
                    .build();
        }

        public static EmployeeSkill toEntity(EmployeeSkillDTO dto, Employee employee, Skill skill){
            return EmployeeSkill.builder()
                    .employeeSkillId(dto.getEmployeeSkillId())
                    .employee(employee)
                    .skill(skill)
                    .lastUsedDate(dto.getLastUsedDate())
                    .acquiredDate(dto.getAcquiredDate())
                    .proficiencyLevel(dto.getProficiencyLevel())
                    .build();

        }

}

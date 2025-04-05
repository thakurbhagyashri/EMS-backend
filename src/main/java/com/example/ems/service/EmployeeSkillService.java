package com.example.ems.service;

import com.example.ems.entities.EmployeeSkill;
import com.example.ems.repositories.EmployeeSkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeSkillService {

    private EmployeeSkillRepository  employeeSkillRepository;

    //add skills of an employee
    public EmployeeSkill addSkill(EmployeeSkill employeeSkill){
        return employeeSkillRepository.save(employeeSkill);
    }
    // fetch skills of an employee
        public List<EmployeeSkill> fetchSkill(Long employeeSkillId){
        return employeeSkillRepository.findByEmployeeEmployeeId(employeeSkillId);
    }

    //update skill of an employee
    public Optional<EmployeeSkill> updateSkill(Long employeeId, Long skillId, EmployeeSkill updatedSkill) {
        return employeeSkillRepository.findByEmployeeEmployeeIdAndSkill_SkillId(employeeId, skillId)
                .map(existingSkill -> {
                    existingSkill.setProficiencyLevel(updatedSkill.getProficiencyLevel());
                    existingSkill.setLastUsedDate(updatedSkill.getLastUsedDate());
                    // Update other fields if necessary
                    return employeeSkillRepository.save(existingSkill);
                });
    }
}

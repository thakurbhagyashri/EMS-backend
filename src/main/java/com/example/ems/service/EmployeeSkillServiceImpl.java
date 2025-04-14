package com.example.ems.service;

import com.example.ems.DTO.EmployeeSkillDTO;
import com.example.ems.entities.Employee;
import com.example.ems.entities.EmployeeSkill;
import com.example.ems.entities.Skill;
import com.example.ems.mapper.EmployeeSkillMapper;
import com.example.ems.repositories.EmployeeSkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeSkillServiceImpl implements EmployeeSkillService {

    private final EmployeeSkillRepository employeeSkillRepository;

    @Override
    public EmployeeSkillDTO addSkill(Long employeeId, EmployeeSkillDTO dto) {

       //create Employee and skill entities to associate with EmployeeSkill
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);

        Skill skill = new Skill();
        skill.setSkillId(dto.getSkill_id());

        //Map DTO to Entity
        EmployeeSkill entity = EmployeeSkillMapper.toEntity(dto, employee, skill);

        //Save and return as DTO
        return EmployeeSkillMapper.toDto(employeeSkillRepository.save(entity));
    }

    @Override
    public List<EmployeeSkillDTO> getSkill(Long employeeId) {
        return employeeSkillRepository.findByEmployeeEmployeeId(employeeId)
                .stream()
                .map(EmployeeSkillMapper::toDto)
                .collect(Collectors.toList());
    }



    @Override
    public EmployeeSkillDTO updateSkill(Long employeeId, Long skillId, EmployeeSkillDTO dto) {
        return employeeSkillRepository.findByEmployeeEmployeeIdAndSkillSkillId(employeeId, skillId)
                .map(existingSkill -> {
                    existingSkill.setProficiencyLevel(dto.getProficiencyLevel());
                    existingSkill.setLastUsedDate(dto.getLastUsedDate());
                    existingSkill.setAcquiredDate(dto.getAcquiredDate());
                    return EmployeeSkillMapper.toDto(employeeSkillRepository.save(existingSkill));
                })
                .orElseThrow(() -> new RuntimeException("Skill not found for employee."));
    }
}
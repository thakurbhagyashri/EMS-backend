package com.example.ems.service;

import com.example.ems.DTO.EmployeeSkillDTO;
import com.example.ems.entities.Employee;
import com.example.ems.entities.EmployeeSkill;
import com.example.ems.entities.Skill;
import com.example.ems.exceptions.ResourceNotFoundException;
import com.example.ems.mapper.EmployeeSkillMapper;
import com.example.ems.repositories.EmployeeRepository;
import com.example.ems.repositories.EmployeeSkillRepository;
import com.example.ems.repositories.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeSkillServiceImpl implements EmployeeSkillService {

    @Autowired
    private  final EmployeeRepository employeeRepository;

    @Autowired
    private final SkillRepository skillRepository;

    @Autowired
    private final EmployeeSkillRepository employeeSkillRepository;

    @Override
    public EmployeeSkillDTO addEmployeeSkill(Long employeeId, EmployeeSkillDTO dto) {

        Employee exist =employeeRepository.findById(employeeId).orElseThrow
                (()-> new ResourceNotFoundException("No Employee Exist by this ID"));
        Skill skillExist = skillRepository.findById(dto.getSkill_id()).orElseThrow
                (()->new ResourceNotFoundException("No Skills Found"));
        return EmployeeSkillMapper.toDto(employeeSkillRepository.save(EmployeeSkillMapper.toEntity(dto, exist, skillExist)));
    }

    @Override
    public List<EmployeeSkillDTO> getAllEmployeeSkills(Long employeeId) {
        Employee exist =employeeRepository.findById(employeeId).orElseThrow
                (()-> new ResourceNotFoundException("No Employee Exist by this ID"));
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
                .orElseThrow(() -> new RuntimeException("No Skill not found for employee."));
    }
}
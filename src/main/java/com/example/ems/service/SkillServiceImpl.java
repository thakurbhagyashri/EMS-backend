package com.example.ems.service;

import com.example.ems.DTO.SkillsDTO;
import com.example.ems.entities.Skill;
import com.example.ems.mapper.SkillMapper;
import com.example.ems.repositories.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public List<SkillsDTO> getAllSkills() {
        List<Skill> skills = skillRepository.findAll();

        return skills.stream()
                .map(SkillMapper::toDTO) // use the mapper
                .collect(Collectors.toList());
    }

    // Add other methods like save/update using SkillMapper.toEntity(dto)
}

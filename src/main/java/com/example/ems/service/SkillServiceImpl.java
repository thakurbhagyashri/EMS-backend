package com.example.ems.service;

import com.example.ems.DTO.SkillsDTO;
import com.example.ems.entities.Skill;
import com.example.ems.mapper.SkillMapper;
import com.example.ems.repositories.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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

    @Override
    public SkillsDTO createSkill(SkillsDTO skillsDTO) {
        if (skillRepository.existsByName(skillsDTO.getName())) {
            throw new IllegalArgumentException("Skill with name already exists: " + skillsDTO.getName());
        }

        Skill skill = SkillMapper.toEntity(skillsDTO);
        Skill saved = skillRepository.save(skill);
        return SkillMapper.toDTO(saved);
    }

    @Override
    public SkillsDTO updateSkill(String name, SkillsDTO skillsDTO) {
        Skill existingSkill = skillRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Skill not found: " + name));

        existingSkill.setName(skillsDTO.getName());
        existingSkill.setDescription(skillsDTO.getDescription());
        existingSkill.setCategory(Skill.Category.valueOf(skillsDTO.getCategory().name()));
        existingSkill.setActive(skillsDTO.isActive());

        Skill updated = skillRepository.save(existingSkill);
        return SkillMapper.toDTO(updated);    }

    // Add other methods like save/update using SkillMapper.toEntity(dto)
}

package com.example.ems.service;

import com.example.ems.dto.SkillsDTO;

import java.util.List;

public interface SkillService {
    List<SkillsDTO> getAllSkills();
    SkillsDTO createSkill(SkillsDTO skillsDTO);
    SkillsDTO updateSkill(String name, SkillsDTO skillsDTO);
}
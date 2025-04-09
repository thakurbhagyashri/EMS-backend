package com.example.ems.mapper;

import com.example.ems.DTO.SkillsDTO;
import com.example.ems.entities.Skill;

public class SkillMapper {

    // DTO → Entity
    public static Skill toEntity(SkillsDTO dto) {
        return Skill.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .category(Skill.Category.valueOf(dto.getCategory().name())) //  Enum mapping
                .isActive(dto.isActive())
                .build();
    }

    // Entity → DTO
    public static SkillsDTO toDTO(Skill skill) {
        return SkillsDTO.builder()
                .name(skill.getName())
                .description(skill.getDescription())
                .category(SkillsDTO.Category.valueOf(skill.getCategory().name())) // Enum mapping
                .isActive(skill.isActive())
                .build();
    }
}

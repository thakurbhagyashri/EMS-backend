package com.example.ems.DTO;

import com.example.ems.entities.Skill;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public  class SkillsDTO {

    @NotBlank(message = "Skill name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Category is required")
    private Skill.Category category;

    private boolean isActive;
}

package com.example.ems.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillsDTO {

    @NotBlank(message = "Skill name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Category is required")
    private Category category;

    private boolean isActive;

    public enum Category {
        TECHNICAL, SOFT, DOMAIN, SERVICE
    }
}

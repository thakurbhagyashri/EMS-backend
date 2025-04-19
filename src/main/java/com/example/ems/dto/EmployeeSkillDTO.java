package com.example.ems.dto;
import com.example.ems.entities.EmployeeSkill.ProficiencyLevel;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class EmployeeSkillDTO {

       private Long employeeSkillId; // Optional for POST, required for PUT

        @NotNull(message = "Employee ID is required")
        private Long employee_id;

        @NotNull(message = "Skill ID is required")
        private Long skill_id;

        @NotNull(message = "Proficiency level is required")
        private ProficiencyLevel proficiencyLevel;

        private LocalDate acquiredDate;
        private LocalDate lastUsedDate;
    }



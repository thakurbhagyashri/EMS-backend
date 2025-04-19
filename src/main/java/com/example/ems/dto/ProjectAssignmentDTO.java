package com.example.ems.dto;


import com.example.ems.entities.ProjectAssignment;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectAssignmentDTO {
    private Long assignmentId;

    @NotNull(message = "Employee ID is required")
    private Long employeeId;

    @NotNull(message = "Project ID is required")
    private Long projectId;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    private LocalDate endDate;

    @NotNull(message = "Allocation percentage is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Allocation percentage must be greater than 0")
    private BigDecimal allocationPercentage;

    @NotNull(message = "Role is required")
    private ProjectAssignment.Role role;

    private String responsibilities;
}

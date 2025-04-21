package com.example.ems.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DesignationDTO {
    private Long designationId;
    private Long employeeId;
    private String title;
    private String department;
    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;
    private String changeReason;
    private Long reportingToId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
package com.example.ems.dto;

import com.example.ems.entities.Compensation;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@Builder
public class CompensationDTO {
    private Long compensationId;
    private Long employee_id;
    private BigDecimal salaryAmount;
    private Compensation.SalaryType salaryType;
    private BigDecimal bonusAmount;
    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;
    private String revisionReason;
}

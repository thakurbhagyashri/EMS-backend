package com.example.ems.DTO;

import com.example.ems.entities.Compensation;
import com.example.ems.entities.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

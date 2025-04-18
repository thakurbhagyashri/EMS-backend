package com.example.ems.mapper;

import com.example.ems.DTO.CompensationDTO;

import com.example.ems.entities.Compensation;
import com.example.ems.entities.Employee;

public class CompensationMapper {
    public static CompensationDTO toDTO(Compensation compensation) {
        return CompensationDTO.builder()
                .compensationId(compensation.getCompensationId())
                .employee_id(compensation.getEmployee().getEmployeeId())
                .salaryAmount(compensation.getSalaryAmount())
                .salaryType(compensation.getSalaryType())
                .bonusAmount(compensation.getBonusAmount())
                .effectiveFrom(compensation.getEffectiveFrom())
                .effectiveTo(compensation.getEffectiveTo())
                .revisionReason(compensation.getRevisionReason())
                .build();
    }

    public static Compensation toEntity(CompensationDTO dto, Employee employee) {
        return Compensation.builder()
                .employee(employee)
                .salaryAmount(dto.getSalaryAmount())
                .salaryType(dto.getSalaryType())
                .bonusAmount(dto.getBonusAmount())
                .effectiveFrom(dto.getEffectiveFrom())
                .effectiveTo(dto.getEffectiveTo())
                .revisionReason(dto.getRevisionReason())
                .build();
    }
}

package com.example.ems.mapper;

import com.example.ems.DTO.CompensationDTO;
import com.example.ems.DTO.UserDTO;
import com.example.ems.entities.Compensation;
import com.example.ems.entities.Employee;
import com.example.ems.entities.User;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CompensationMapper {

    public static CompensationDTO toDto(Compensation compensation) {
        return CompensationDTO.builder()
                .compensationId(compensation.getCompensationId())
                .employee(compensation.getEmployee())
                .salaryAmount(compensation.getSalaryAmount())
                .salaryType(compensation.getSalaryType())
                .bonusAmount(compensation.getBonusAmount())
                .effectiveFrom(compensation.getEffectiveFrom())
                .effectiveTo(compensation.getEffectiveTo())
                .revisionReason(compensation.getRevisionReason())
                .build();
    }

    public static Compensation toEntity(CompensationDTO dto) {
        return Compensation.builder()
                .compensationId(dto.getCompensationId())
                .employee(dto.getEmployee())
                .salaryAmount(dto.getSalaryAmount())
                .salaryType(dto.getSalaryType())
                .bonusAmount(dto.getBonusAmount())
                .effectiveFrom(dto.getEffectiveFrom())
                .effectiveTo(dto.getEffectiveTo())
                .revisionReason(dto.getRevisionReason())
                .build();
    }
}

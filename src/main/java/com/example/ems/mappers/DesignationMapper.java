package com.example.ems.mapper;

import com.example.ems.DTO.DesignationDTO;
import com.example.ems.entities.Designation;
import com.example.ems.entities.Employee;
import lombok.Builder;

@Builder
public class DesignationMapper {

    public static DesignationDTO toDTO(Designation designation) {
        return DesignationDTO.builder()
                .designationId(designation.getDesignationId())
                .employeeId(designation.getEmployee() != null ? designation.getEmployee().getEmployeeId() : null)
                .title(designation.getTitle())
                .department(designation.getDepartment())
                .effectiveFrom(designation.getEffectiveFrom())
                .effectiveTo(designation.getEffectiveTo())
                .changeReason(designation.getChangeReason())
                .reportingToId(designation.getReportingTo() != null ? designation.getReportingTo().getEmployeeId() : null)
                .createdAt(designation.getCreatedAt())
                .updatedAt(designation.getUpdatedAt())
                .build();
    }

    public static Designation toEntity(DesignationDTO dto) {
        Employee employee = new Employee();
        employee.setEmployeeId(dto.getEmployeeId());

        Employee reportingTo = null;
        if (dto.getReportingToId() != null) {
            reportingTo = new Employee();
            reportingTo.setEmployeeId(dto.getReportingToId());
        }

        return Designation.builder()
                .designationId(dto.getDesignationId())
                .employee(employee)
                .title(dto.getTitle())
                .department(dto.getDepartment())
                .effectiveFrom(dto.getEffectiveFrom())
                .effectiveTo(dto.getEffectiveTo())
                .changeReason(dto.getChangeReason())
                .reportingTo(reportingTo)
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }
}
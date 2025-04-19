package com.example.ems.mapper;

import com.example.ems.dto.EmployeeDTO;
import com.example.ems.entities.Employee;
import com.example.ems.entities.User;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public EmployeeDTO toDto(Employee employee) {
        return EmployeeDTO.builder()
                .id(employee.getEmployeeId())
                .userId(employee.getUser().getUserId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .phone(employee.getPhone())
                .address(employee.getAddress())
                .dateOfBirth(employee.getDateOfBirth())
                .joiningDate(employee.getJoiningDate())
                .terminationDate(employee.getTerminationDate())
                .employmentStatus(employee.getEmploymentStatus())
                .emergencyContactName(employee.getEmergencyContactName())
                .emergencyContactPhone(employee.getEmergencyContactPhone())
                .managerId(employee.getManager() != null ? employee.getManager().getEmployeeId() : null)
                .build();
    }

    public Employee toEntity(EmployeeDTO dto, User user, Employee manager) {
        return Employee.builder()
                .employeeId(dto.getId())
                .user(user)
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .dateOfBirth(dto.getDateOfBirth())
                .joiningDate(dto.getJoiningDate())
                .terminationDate(dto.getTerminationDate())
                .employmentStatus(dto.getEmploymentStatus())
                .emergencyContactName(dto.getEmergencyContactName())
                .emergencyContactPhone(dto.getEmergencyContactPhone())
                .manager(manager)
                .build();
    }
}
package com.example.ems.dto;

import com.example.ems.entities.Employee;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO {
    private Long employeeId;
    private Long userId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private LocalDate dateOfBirth;
    private LocalDate joiningDate;
    private LocalDate terminationDate;
    private Employee.EmploymentStatus employmentStatus;
    private String emergencyContactName;
    private String emergencyContactPhone;
    private Long managerId;
}
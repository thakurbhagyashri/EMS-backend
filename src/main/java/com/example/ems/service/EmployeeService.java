package com.example.ems.service;

import com.example.ems.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO createEmployee(EmployeeDTO dto);
    EmployeeDTO updateEmployee(Long id, EmployeeDTO dto);
    EmployeeDTO getEmployeeById(Long id);
    void softDeleteEmployee(Long id);
}

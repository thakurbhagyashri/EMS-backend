package com.example.ems.service;

import com.example.ems.DTO.EmployeeDTO;
import com.example.ems.entities.Employee;
import com.example.ems.entities.User;
import com.example.ems.exceptions.ResourceNotFoundException;
import com.example.ems.mapper.EmployeeMapper;
import com.example.ems.repositories.EmployeeRepository;
import com.example.ems.repositories.UserRepository;
import com.example.ems.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final EmployeeMapper mapper;

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Employee manager = dto.getManagerId() != null ? employeeRepository.findById(dto.getManagerId()).orElse(null) : null;
        Employee employee = mapper.toEntity(dto, user, manager);
        return mapper.toDto(employeeRepository.save(employee));
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());
        existing.setPhone(dto.getPhone());
        existing.setAddress(dto.getAddress());
        existing.setDateOfBirth(dto.getDateOfBirth());
        existing.setJoiningDate(dto.getJoiningDate());
        existing.setTerminationDate(dto.getTerminationDate());
        existing.setEmploymentStatus(dto.getEmploymentStatus());
        existing.setEmergencyContactName(dto.getEmergencyContactName());
        existing.setEmergencyContactPhone(dto.getEmergencyContactPhone());
        if (dto.getManagerId() != null) {
            Employee manager = employeeRepository.findById(dto.getManagerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Manager not found"));
            existing.setManager(manager);
        }
        return mapper.toDto(employeeRepository.save(existing));
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        return mapper.toDto(employee);
    }

    @Override
    public void softDeleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        employee.setEmploymentStatus(Employee.EmploymentStatus.TERMINATED);
        employeeRepository.save(employee);
    }
}
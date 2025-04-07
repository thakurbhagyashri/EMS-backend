package com.example.ems.services;

import com.example.ems.dto.EmployeeDTO;
import com.example.ems.entities.Employee;
import com.example.ems.entities.User;
import com.example.ems.mapper.EmployeeMapper;
import com.example.ems.repositories.EmployeeRepository;
import com.example.ems.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final EmployeeMapper employeeMapper;

    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        return employeeRepository.findById(id).map(employeeMapper::toDto);
    }

    public Optional<EmployeeDTO> getEmployeeByUserId(Long userId) {
        return userRepository.findById(userId)
                .flatMap(employeeRepository::findByUser)
                .map(employeeMapper::toDto);
    }

    public EmployeeDTO createOrUpdateEmployee(EmployeeDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Employee manager = dto.getManagerId() != null ? employeeRepository.findById(dto.getManagerId()).orElse(null) : null;

        Employee employee = employeeMapper.toEntity(dto, user, manager);
        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public List<EmployeeDTO> getEmployeesByStatus(Employee.EmploymentStatus status) {
        return employeeRepository.findByEmploymentStatus(status).stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<EmployeeDTO> getEmployeesByManager(Long managerId) {
        return employeeRepository.findByManager(Employee.builder().employeeId(managerId).build()).stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<EmployeeDTO> searchEmployeesByName(String keyword) {
        return employeeRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(keyword, keyword).stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<EmployeeDTO> getEmployeesJoinedAfter(LocalDate date) {
        return employeeRepository.findByJoiningDateAfter(date).stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<EmployeeDTO> getTerminatedEmployees() {
        return employeeRepository.findByTerminationDateIsNotNull().stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<EmployeeDTO> getActiveEmployees() {
        return employeeRepository.findByEmploymentStatusAndTerminationDateIsNull(Employee.EmploymentStatus.ACTIVE).stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }
}

package com.example.ems.controllers;

import com.example.ems.dto.EmployeeDTO;
import com.example.ems.entities.Employee;
import com.example.ems.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAll() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<EmployeeDTO> getByUserId(@PathVariable Long userId) {
        return employeeService.getEmployeeByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createOrUpdate(@RequestBody EmployeeDTO dto) {
        return ResponseEntity.ok(employeeService.createOrUpdateEmployee(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<EmployeeDTO>> getByStatus(@PathVariable Employee.EmploymentStatus status) {
        return ResponseEntity.ok(employeeService.getEmployeesByStatus(status));
    }

    @GetMapping("/manager/{managerId}")
    public ResponseEntity<List<EmployeeDTO>> getByManager(@PathVariable Long managerId) {
        return ResponseEntity.ok(employeeService.getEmployeesByManager(managerId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<EmployeeDTO>> searchByName(@RequestParam String keyword) {
        return ResponseEntity.ok(employeeService.searchEmployeesByName(keyword));
    }

    @GetMapping("/joined-after")
    public ResponseEntity<List<EmployeeDTO>> joinedAfter(@RequestParam LocalDate date) {
        return ResponseEntity.ok(employeeService.getEmployeesJoinedAfter(date));
    }

    @GetMapping("/terminated")
    public ResponseEntity<List<EmployeeDTO>> getTerminated() {
        return ResponseEntity.ok(employeeService.getTerminatedEmployees());
    }

    @GetMapping("/active")
    public ResponseEntity<List<EmployeeDTO>> getActive() {
        return ResponseEntity.ok(employeeService.getActiveEmployees());
    }
}
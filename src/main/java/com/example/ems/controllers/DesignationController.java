package com.example.ems.controllers;

import com.example.ems.entities.Designation;
import com.example.ems.entities.Employee;
import com.example.ems.exceptions.ResourceNotFoundException;
import com.example.ems.services.impl.DesignationService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/designations")
@RequiredArgsConstructor
public class DesignationController {

    private final DesignationService designationService;

    @GetMapping
    public ResponseEntity<List<Designation>> getAllDesignations() {
        return ResponseEntity.ok(designationService.getAllDesignations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Designation> getDesignationById(@PathVariable Long id) {
        return designationService.getDesignationById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Designation not found with ID: " + id));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Designation>> getDesignationsByEmployee(@PathVariable Long employeeId) {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        return ResponseEntity.ok(designationService.getDesignationsByEmployee(employee));
    }

    @GetMapping("/reporting-to/{managerId}")
    public ResponseEntity<List<Designation>> getDesignationsByReportingTo(@PathVariable Long managerId) {
        Employee manager = new Employee();
        manager.setEmployeeId(managerId);
        return ResponseEntity.ok(designationService.getDesignationsByReportingTo(manager));
    }

    @GetMapping("/employee/{employeeId}/current")
    public ResponseEntity<Designation> getCurrentDesignation(@PathVariable Long employeeId) {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        Designation designation = designationService.getCurrentDesignation(employee);
        return designation != null ? ResponseEntity.ok(designation) : ResponseEntity.notFound().build();
    }

    @GetMapping("/active")
    public ResponseEntity<List<Designation>> getActiveDesignations(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(designationService.getActiveDesignations(date));
    }

    @PostMapping
    public ResponseEntity<Designation> createDesignation(@RequestBody Designation designation) {
        return ResponseEntity.ok(designationService.saveDesignation(designation));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Designation> updateDesignation(@PathVariable Long id, @RequestBody Designation updated) {
        return designationService.getDesignationById(id)
                .map(existing -> {
                    updated.setDesignationId(id);
                    return ResponseEntity.ok(designationService.saveDesignation(updated));
                })
                .orElse( ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDesignation(@PathVariable Long id) {
        designationService.deleteDesignation(id);
        return ResponseEntity.noContent().build();
    }
}

package com.example.ems.services;

import com.example.ems.entities.Designation;
import com.example.ems.entities.Employee;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DesignationService {

    List<Designation> getAllDesignations();

    Optional<Designation> getDesignationById(Long id);

    List<Designation> getDesignationsByEmployee(Employee employee);

    List<Designation> getDesignationsByReportingTo(Employee reportingTo);

    Designation getCurrentDesignation(Employee employee);

    List<Designation> getActiveDesignations(LocalDate currentDate);

    Designation saveDesignation(Designation designation);

    void deleteDesignation(Long id);
}

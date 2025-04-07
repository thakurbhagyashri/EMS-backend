package com.example.ems.service;
import com.example.ems.entities.Designation;
import com.example.ems.entities.Employee;
import com.example.ems.repositories.DesignationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DesignationService {

    private final DesignationRepository designationRepository;

    public List<Designation> getAllDesignations() {
        return designationRepository.findAll();
    }

    public Optional<Designation> getDesignationById(Long id) {
        return designationRepository.findById(id);
    }

    public List<Designation> getDesignationsByEmployee(Employee employee) {
        return designationRepository.findByEmployee(employee);
    }

    public List<Designation> getDesignationsByReportingTo(Employee reportingTo) {
        return designationRepository.findByReportingTo(reportingTo);
    }

    public Designation getCurrentDesignation(Employee employee) {
        return designationRepository.findTopByEmployeeAndEffectiveToIsNullOrderByEffectiveFromDesc(employee);
    }

    public List<Designation> getActiveDesignations(LocalDate currentDate) {
        return designationRepository.findByEffectiveToIsNullOrEffectiveToAfter(currentDate);
    }

    public Designation saveDesignation(Designation designation) {
        return designationRepository.save(designation);
    }

    public void deleteDesignation(Long id) {
        designationRepository.deleteById(id);
    }
}



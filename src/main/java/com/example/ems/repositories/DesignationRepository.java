package com.example.ems.repositories;

import com.example.ems.entities.Designation;
import com.example.ems.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DesignationRepository extends JpaRepository<Designation,Long> {

    List<Designation> findByEmployee(Employee employee);

    List<Designation> findByReportingTo(Employee reportingTo);

    Designation findTopByEmployeeAndEffectiveToIsNullOrderByEffectiveFromDesc(Employee employee);

    List<Designation> findByEffectiveToIsNullOrEffectiveToAfter(LocalDate date);
}
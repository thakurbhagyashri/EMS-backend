package com.example.ems.repositories;

import com.example.ems.entities.ProjectAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectAssignmentRepository extends JpaRepository<ProjectAssignment, Long> {
    List<ProjectAssignment> findByEmployee_EmployeeId(Long employeeId);


}

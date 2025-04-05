package com.example.ems.repositories;

import com.example.ems.entities.Employee;
import com.example.ems.entities.User;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Entity
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Optional<Employee> findByUser(User user);

    List<Employee> findByEmploymentStatus(Employee.EmploymentStatus status);

    List<Employee> findByManager(Employee manager);

    Optional<Employee> findByPhone(String phone);

    List<Employee> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);

    List<Employee> findByJoiningDateAfter(java.time.LocalDate date);

    List<Employee> findByTerminationDateIsNotNull();

    List<Employee> findByEmploymentStatusAndTerminationDateIsNull(Employee.EmploymentStatus status);
}

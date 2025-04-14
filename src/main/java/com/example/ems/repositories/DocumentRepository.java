package com.example.ems.repositories;

import com.example.ems.entities.Document;
import com.example.ems.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByEmployee(Employee employee);
}

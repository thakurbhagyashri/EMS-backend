package com.example.ems.repositories;

import com.example.ems.entities.Compensation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompensationRepository extends JpaRepository<Compensation, Long> {

    //List<Compensation> findByEmployeeId(long employeeId);
}

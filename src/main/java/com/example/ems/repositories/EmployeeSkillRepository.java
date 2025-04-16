package com.example.ems.repositories;
import com.example.ems.entities.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill, Long> {

        List<EmployeeSkill> findByEmployeeEmployeeId(Long employeeId);
        Optional<EmployeeSkill> findByEmployeeEmployeeIdAndSkillSkillId(Long employeeId, Long skillId);
    }



package com.example.ems.repositories;

import com.example.ems.entities.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<Skill,Long> {
    Optional<Skill> findByName(String name); // Find a skill by its name

    boolean existsByName(String name); // check if a skills exits by name

}


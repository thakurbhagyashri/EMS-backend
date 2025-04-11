package com.example.ems.controller;

import com.example.ems.DTO.SkillsDTO;
import com.example.ems.service.SkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;

    // Correct Constructor
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }
    @GetMapping
    public ResponseEntity<List<SkillsDTO>> getAllSkills() {
        return ResponseEntity.ok(skillService.getAllSkills());
    }
}

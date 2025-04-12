package com.example.ems.controller;

import com.example.ems.DTO.SkillsDTO;
import com.example.ems.service.SkillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    //add method(extra)
    @PreAuthorize("hasRole('HR')")
    @PostMapping
    public ResponseEntity<SkillsDTO> createSkill(@RequestBody SkillsDTO skillsDTO) {
        SkillsDTO created = skillService.createSkill(skillsDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    //update methoda (extra)
    @PreAuthorize("hasRole('HR')")
    @PutMapping("/{name}")
    public ResponseEntity<SkillsDTO> updateSkill(@PathVariable String name, @RequestBody SkillsDTO skillsDTO) {
        SkillsDTO updated = skillService.updateSkill(name, skillsDTO);
        return ResponseEntity.ok(updated);
    }
}

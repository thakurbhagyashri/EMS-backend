package com.example.ems.controller;

import com.example.ems.dto.PerformanceReviewDto;
import com.example.ems.service.PerformanceReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class PerformanceReviewController {

    private final PerformanceReviewService service;

    @Autowired
    public PerformanceReviewController(PerformanceReviewService service) {
        this.service = service;
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<PerformanceReviewDto>> getReviewsByEmployeeId(@PathVariable Long employeeId) {
        List<PerformanceReviewDto> reviews = service.getReviewsByEmployeeId(employeeId);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping
    public ResponseEntity<PerformanceReviewDto> createReview(@RequestBody PerformanceReviewDto dto) {
        PerformanceReviewDto createdReview = service.createReview(dto);
        return ResponseEntity.ok(createdReview);
    }

}


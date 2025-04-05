package com.example.ems.service;


import com.example.ems.DTO.PerformanceReviewDto;
import com.example.ems.entities.PerformanceReview;
import com.example.ems.exceptions.ResourceNotFoundException;
import com.example.ems.mapper.PerformanceReviewMapper;
import com.example.ems.repositories.PerformanceReviewRepository;
import com.example.ems.service.PerformanceReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PerformanceReviewServiceImpl implements PerformanceReviewService {

    private final PerformanceReviewRepository repository;

    @Autowired
    public PerformanceReviewServiceImpl(PerformanceReviewRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PerformanceReviewDto> getReviewsByEmployeeId(Long employeeId) {
        List<PerformanceReview> reviews = repository.findByEmployee_EmployeeId(employeeId);
        if (reviews.isEmpty()) {
            throw new ResourceNotFoundException("No reviews found for employee ID: " + employeeId);
        }
        return reviews.stream()
                .map(PerformanceReviewMapper::toDto)
                .collect(Collectors.toList());
    }
}

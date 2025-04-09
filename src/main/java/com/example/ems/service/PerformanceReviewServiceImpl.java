package com.example.ems.service;


import com.example.ems.DTO.PerformanceReviewDto;
import com.example.ems.entities.Employee;
import com.example.ems.entities.PerformanceReview;
import com.example.ems.exceptions.ResourceNotFoundException;
import com.example.ems.mapper.PerformanceReviewMapper;
import com.example.ems.repositories.EmployeeRepository;
import com.example.ems.repositories.PerformanceReviewRepository;
import com.example.ems.service.PerformanceReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PerformanceReviewServiceImpl implements PerformanceReviewService {

    private final PerformanceReviewRepository reviewRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public PerformanceReviewServiceImpl(PerformanceReviewRepository reviewRepository,
                                        EmployeeRepository employeeRepository) {
        this.reviewRepository = reviewRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<PerformanceReviewDto> getReviewsByEmployeeId(Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isEmpty()) {
            System.out.println("Employee not found for ID: " + employeeId);  // Or use logger
            return List.of(); // Return empty list if employee doesn't exist
        }

        return reviewRepository.findByEmployee_EmployeeId(employeeId)
                .stream()
                .map(PerformanceReviewMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PerformanceReviewDto createReview(PerformanceReviewDto dto) {
        // Fetch employee and reviewer
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + dto.getEmployeeId()));

        Employee reviewer = null;
        if (dto.getReviewerId() != null) {
            reviewer = employeeRepository.findById(dto.getReviewerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Reviewer not found with ID: " + dto.getReviewerId()));
        }

        PerformanceReview review = new PerformanceReview();
        review.setEmployee(employee);
        review.setReviewer(reviewer);
        review.setReviewPeriodStart(dto.getReviewPeriodStart());
        review.setReviewPeriodEnd(dto.getReviewPeriodEnd());
        review.setReviewDate(dto.getReviewDate());
        review.setReviewType(dto.getReviewType());
        review.setOverallRating(dto.getOverallRating());
        review.setStrengths(dto.getStrengths());
        review.setAreasForImprovement(dto.getAreasForImprovement());
        review.setGoalsForNextPeriod(dto.getGoalsForNextPeriod());
        review.setStatus(dto.getStatus());

        PerformanceReview saved = reviewRepository.save(review);
        return PerformanceReviewMapper.toDto(saved);
    }

}

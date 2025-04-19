package com.example.ems.service;


import com.example.ems.dto.PerformanceReviewDto;

import java.util.List;

public interface PerformanceReviewService {
    List<PerformanceReviewDto> getReviewsByEmployeeId(Long employeeId);
    PerformanceReviewDto createReview(PerformanceReviewDto dto);
}

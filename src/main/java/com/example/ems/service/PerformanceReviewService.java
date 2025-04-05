package com.example.ems.service;


import com.example.ems.DTO.PerformanceReviewDto;

import java.util.List;

public interface PerformanceReviewService {
    List<PerformanceReviewDto> getReviewsByEmployeeId(Long employeeId);
}

package com.example.ems.mapper;

import com.example.ems.DTO.PerformanceReviewDto;
import com.example.ems.entities.PerformanceReview;

public class PerformanceReviewMapper {

    public static PerformanceReviewDto toDto(PerformanceReview review) {
        PerformanceReviewDto dto = new PerformanceReviewDto();
        dto.setReviewId(review.getReviewId());
        dto.setEmployeeId(review.getEmployee().getEmployeeId());
        dto.setReviewerId(
                review.getReviewer() != null ? review.getReviewer().getEmployeeId() : null
        );
        dto.setReviewPeriodStart(review.getReviewPeriodStart());
        dto.setReviewPeriodEnd(review.getReviewPeriodEnd());
        dto.setReviewDate(review.getReviewDate());
        dto.setReviewType(review.getReviewType());
        dto.setOverallRating(review.getOverallRating());
        dto.setStrengths(review.getStrengths());
        dto.setAreasForImprovement(review.getAreasForImprovement());
        dto.setGoalsForNextPeriod(review.getGoalsForNextPeriod());
        dto.setStatus(review.getStatus());
        return dto;
    }
}

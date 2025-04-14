package com.example.ems.DTO;

import com.example.ems.entities.PerformanceReview.Status;
import com.example.ems.entities.PerformanceReview.ReviewType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PerformanceReviewDto {
    private Long reviewId;
    private Long employeeId;
    private Long reviewerId;
    private LocalDate reviewPeriodStart;
    private LocalDate reviewPeriodEnd;
    private LocalDate reviewDate;
    private ReviewType reviewType;
    private BigDecimal overallRating;
    private String strengths;
    private String areasForImprovement;
    private String goalsForNextPeriod;
    private Status status;
}

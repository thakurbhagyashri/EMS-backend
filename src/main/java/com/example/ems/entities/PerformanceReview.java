package com.example.ems.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "performance_reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerformanceReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    private Employee reviewer;

    private LocalDate reviewPeriodStart;
    private LocalDate reviewPeriodEnd;
    private LocalDate reviewDate;

    @Enumerated(EnumType.STRING)
    private ReviewType reviewType;

    private BigDecimal overallRating;
    private String strengths;
    private String areasForImprovement;
    private String goalsForNextPeriod;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public enum ReviewType {
        QUARTERLY, SEMI_ANNUAL, ANNUAL, PROBATION
    }

    public enum Status {
        DRAFT, SUBMITTED, ACKNOWLEDGED, FINALIZED
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

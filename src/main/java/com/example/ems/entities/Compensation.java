package com.example.ems.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "compensations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Compensation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long compensationId;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private BigDecimal salaryAmount;

    @Enumerated(EnumType.STRING)
    private SalaryType salaryType;

    private BigDecimal bonusAmount;
    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;
    private String revisionReason;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public enum SalaryType {
        MONTHLY, ANNUAL
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
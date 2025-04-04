package com.example.ems.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "designations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Designation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long designationId;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private String title;
    private String department;
    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;
    private String changeReason;

    @ManyToOne
    @JoinColumn(name = "reporting_to")
    private Employee reportingTo;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

package com.example.ems.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private LocalDate dateOfBirth;
    private LocalDate joiningDate;
    private LocalDate terminationDate;

    @Enumerated(EnumType.STRING)
    private EmploymentStatus employmentStatus;

    private String emergencyContactName;
    private String emergencyContactPhone;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public enum EmploymentStatus {
        ACTIVE, TERMINATED, ON_LEAVE, PROBATION
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
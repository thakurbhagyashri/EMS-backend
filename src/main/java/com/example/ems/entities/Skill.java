package com.example.ems.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "skills")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skillId;

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;

    private boolean isActive;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public enum Category {
        TECHNICAL, SOFT, DOMAIN, SERVICE
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

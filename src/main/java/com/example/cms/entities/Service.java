package com.example.cms.entities;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceId;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private double baseCost;

    @Column(nullable = false)
    private boolean isActive;
}

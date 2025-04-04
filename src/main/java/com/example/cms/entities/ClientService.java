package com.example.cms.entities;
import com.example.cms.entities.Client;
import com.example.cms.entities.Service;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "client_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientServiceId;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    private LocalDate startDate;
    private LocalDate endDate;

    @Column(nullable = false)
    private boolean isOngoing;

    @Column(nullable = false) // Updation of double datatype to BigDecimal due to some issues
    private BigDecimal agreedCost;

    private String serviceNotes;

    @Enumerated(EnumType.STRING)
    private Status status; // ACTIVE, COMPLETED, PENDING, CANCELLED

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

    public enum Status {  // we can modify the enums separately
        ACTIVE, COMPLETED, PENDING, CANCELLED
    }
}

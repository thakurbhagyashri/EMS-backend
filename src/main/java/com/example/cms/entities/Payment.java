package com.example.cms.entities;

import com.example.cms.entities.ClientService;
import com.example.cms.entities.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "client_service_id", nullable = false)
    private ClientService clientService;

    @Column(nullable = false)
    private BigDecimal amount;

    private LocalDate paymentDate;

    @ManyToOne
    @JoinColumn(name = "received_by", nullable = false)
    private User receivedBy;

    private String paymentNotes;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;  // Payment status tracking

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public enum PaymentStatus {
        PAID, PENDING, OVERDUE, PARTIALLY_PAID
    }
}

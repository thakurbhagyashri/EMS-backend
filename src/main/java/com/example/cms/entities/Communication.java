package com.example.cms.entities;
import com.example.cms.entities.Client;
import com.example.cms.entities.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "communications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Communication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long communicationId;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDateTime communicationDate;

    @Enumerated(EnumType.STRING)
    private CommunicationType communicationType; // CALL, EMAIL, MEETING, OTHER

    private String discussionNotes;
    private String followUpActions;
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public enum CommunicationType { // we can modify the enums separately
        CALL, EMAIL, MEETING, OTHER
    }
}
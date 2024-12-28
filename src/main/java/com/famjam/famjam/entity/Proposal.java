package com.famjam.famjam.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "proposals")
@Data
public class Proposal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_family_id")
    private Family fromFamily;

    @ManyToOne
    @JoinColumn(name = "to_family_id")
    private Family toFamily;

    @Column(columnDefinition = "TEXT")
    private String message;
    private LocalDateTime preferredTime;

    @Enumerated(EnumType.STRING)
    private ProposalStatus status;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        status = ProposalStatus.PENDING;
    }
}
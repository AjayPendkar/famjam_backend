package com.famjam.famjam.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "families")
@Data
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String familyName;

    @Column(unique = true)
    private String uniqueIdentifier; // familyName + timestamp

    @OneToOne
    @JoinColumn(name = "family_head_id", unique = true)
    private User familyHead;

    private String description;
    private String location;
    private String religion;
    private String caste;
    private String motherTongue;
    private boolean isPremium;

    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL)
    private List<FamilyMember> members;

    @OneToOne(mappedBy = "family", cascade = CascadeType.ALL)
    private FamilyHistory familyHistory;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        uniqueIdentifier = generateUniqueIdentifier();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    private String generateUniqueIdentifier() {
        return familyName.toLowerCase().replaceAll("\\s+", "-") +
                "-" + System.currentTimeMillis();
    }
}
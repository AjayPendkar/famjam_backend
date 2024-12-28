package com.famjam.famjam.dto.response;

import com.famjam.famjam.entity.ProposalStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ProposalResponse {
    private Long id;
    private String message;
    private ProposalStatus status;
    private LocalDateTime createdAt;
    private String preferredTime;
    private String additionalNotes;
    private FamilyProfileResponse requestingFamily;
    private FamilyProfileResponse targetFamily;
}
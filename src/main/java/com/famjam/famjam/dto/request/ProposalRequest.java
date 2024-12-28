package com.famjam.famjam.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ProposalRequest {
    @NotBlank(message = "Message is required")
    private String message;

    @NotNull(message = "Preferred time is required")
    private LocalDateTime preferredTime;
}
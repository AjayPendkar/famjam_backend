package com.famjam.famjam.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class SubscriptionRequest {
    @NotBlank(message = "Plan type is required")
    private String planType;

    @NotBlank(message = "Plan name is required")
    private String planName;

    @Positive(message = "Amount must be positive")
    private double amount;
}
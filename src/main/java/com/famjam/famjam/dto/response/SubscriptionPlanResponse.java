package com.famjam.famjam.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionPlanResponse {
    private String planType;
    private String planName;
    private double amount;
    private String description;
}
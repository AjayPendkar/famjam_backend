package com.famjam.famjam.dto.response;

import com.famjam.famjam.entity.SubscriptionType;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SubscriptionDetails {
    private SubscriptionType type;
    private LocalDateTime startDate;
    private LocalDateTime expiryDate;
    private boolean isActive;
    private double amount;
    private String planName;
}
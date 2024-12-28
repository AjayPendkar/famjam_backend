package com.famjam.famjam.dto.response;

import com.famjam.famjam.entity.SubscriptionType;
import lombok.Data;

@Data
public class UserProfileDTO {
    private Long id;
    private String fullName;
    private String mobileNumber;
    private String email;
    private boolean isVerified;
    private SubscriptionType subscriptionType;
    private String profilePicture;
}
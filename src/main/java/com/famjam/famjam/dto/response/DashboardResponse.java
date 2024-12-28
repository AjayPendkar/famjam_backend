package com.famjam.famjam.dto.response;

import lombok.Data;
import java.util.List;

@Data
public class DashboardResponse {
    private UserProfileDTO userProfile;
    private List<NotificationResponse> recentNotifications;
    private List<FamilyProfileResponse> recentMatches;
    private SubscriptionDetails subscription;
}
package com.famjam.famjam.service;

import com.famjam.famjam.dto.response.DashboardResponse;
import com.famjam.famjam.dto.response.NotificationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private final UserService userService;
    private final FamilyService familyService;
    private final NotificationService notificationService;

    public DashboardResponse getDashboardData(String mobileNumber) {
        // Implementation
        return new DashboardResponse();
    }

    public List<NotificationResponse> getNotifications(String mobileNumber) {
        return notificationService.getUserNotifications(mobileNumber);
    }
}
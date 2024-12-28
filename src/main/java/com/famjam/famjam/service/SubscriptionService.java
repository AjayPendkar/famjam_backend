package com.famjam.famjam.service;

import com.famjam.famjam.dto.request.SubscriptionRequest;
import com.famjam.famjam.dto.response.SubscriptionPlanResponse;
import com.famjam.famjam.entity.User;
import com.famjam.famjam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final UserRepository userRepository;

    public List<SubscriptionPlanResponse> getSubscriptionPlans() {
        // Return predefined subscription plans
        return Arrays.asList(
                new SubscriptionPlanResponse("BASIC", "Free", 0.0, "Basic features"),
                new SubscriptionPlanResponse("PREMIUM", "Monthly Premium", 299.0, "All premium features"),
                new SubscriptionPlanResponse("PREMIUM_YEARLY", "Yearly Premium", 2999.0,
                        "All premium features with yearly discount"));
    }

    public SubscriptionPlanResponse subscribe(String mobileNumber, SubscriptionRequest request) {
        User user = userRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Implement subscription logic here
        // Update user's subscription status
        // Process payment
        // Return subscription details

        return new SubscriptionPlanResponse(
                request.getPlanType(),
                request.getPlanName(),
                request.getAmount(),
                "Subscription activated successfully");
    }
}
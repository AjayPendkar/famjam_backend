package com.famjam.famjam.controller;

import com.famjam.famjam.dto.request.SubscriptionRequest;
import com.famjam.famjam.service.SubscriptionService;
import com.famjam.famjam.service.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subscription")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    private final JwtService jwtService;

    @GetMapping("/plans")
    public ResponseEntity<?> getSubscriptionPlans() {
        return ResponseEntity.ok(subscriptionService.getSubscriptionPlans());
    }

    @PostMapping("/subscribe")
    public ResponseEntity<?> subscribe(
            @RequestHeader("Authorization") String token,
            @Valid @RequestBody SubscriptionRequest request) {
        String mobileNumber = jwtService.extractMobileNumber(token.substring(7));
        return ResponseEntity.ok(subscriptionService.subscribe(mobileNumber, request));
    }
}
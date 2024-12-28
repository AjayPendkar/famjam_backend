package com.famjam.famjam.controller;

import com.famjam.famjam.service.DashboardService;
import com.famjam.famjam.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;
    private final JwtService jwtService;

    @GetMapping
    public ResponseEntity<?> getDashboardData(@RequestHeader("Authorization") String token) {
        String mobileNumber = jwtService.extractMobileNumber(token.substring(7));
        return ResponseEntity.ok(dashboardService.getDashboardData(mobileNumber));
    }

    @GetMapping("/notifications")
    public ResponseEntity<?> getNotifications(@RequestHeader("Authorization") String token) {
        String mobileNumber = jwtService.extractMobileNumber(token.substring(7));
        return ResponseEntity.ok(dashboardService.getNotifications(mobileNumber));
    }
}
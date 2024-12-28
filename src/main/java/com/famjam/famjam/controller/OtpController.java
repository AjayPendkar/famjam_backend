package com.famjam.famjam.controller;

import com.famjam.famjam.dto.request.OtpRequest;
import com.famjam.famjam.dto.request.OtpVerificationRequest;
import com.famjam.famjam.dto.response.OtpResponse;
import com.famjam.famjam.service.OtpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/otp")
@RequiredArgsConstructor
public class OtpController {
    private final OtpService otpService;

    @PostMapping("/generate")
    public ResponseEntity<OtpResponse> generateOtp(@Valid @RequestBody OtpRequest request) {
        return ResponseEntity.ok(otpService.generateOtp(request));
    }

    @PostMapping("/verify")
    public ResponseEntity<OtpResponse> verifyOtp(@Valid @RequestBody OtpVerificationRequest request) {
        return ResponseEntity.ok(otpService.verifyOtp(request));
    }
}
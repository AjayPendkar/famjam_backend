package com.famjam.famjam.service;

import com.famjam.famjam.dto.request.OtpRequest;
import com.famjam.famjam.dto.request.OtpVerificationRequest;
import com.famjam.famjam.dto.response.OtpResponse;
import com.famjam.famjam.entity.OtpEntity;
import com.famjam.famjam.entity.User;
import com.famjam.famjam.repository.OtpRepository;
import com.famjam.famjam.repository.UserRepository;
import com.famjam.famjam.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class OtpService {
    private final OtpRepository otpRepository;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public OtpResponse generateOtp(OtpRequest request) {
        String otp = generateRandomOtp();
        log.info("Generated OTP for mobile {}: {}", request.getMobileNumber(), otp);

        OtpEntity otpEntity = new OtpEntity();
        otpEntity.setMobileNumber(request.getMobileNumber());
        otpEntity.setOtp(otp);
        otpEntity.setExpiryTime(LocalDateTime.now().plusMinutes(5));
        otpEntity.setUsed(false);

        otpRepository.save(otpEntity);

        return new OtpResponse("OTP sent successfully", true);
    }

    public OtpResponse verifyOtp(OtpVerificationRequest request) {
        OtpEntity otpEntity = otpRepository
                .findTopByMobileNumberOrderByExpiryTimeDesc(request.getMobileNumber())
                .orElseThrow(() -> new RuntimeException("No OTP found for this mobile number"));

        log.info("Verifying OTP for mobile {}: stored={}, received={}",
                request.getMobileNumber(), otpEntity.getOtp(), request.getOtp());

        if (otpEntity.isUsed()) {
            throw new RuntimeException("OTP already used");
        }

        if (otpEntity.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP has expired");
        }

        if (!otpEntity.getOtp().equals(request.getOtp())) {
            throw new RuntimeException("Invalid OTP");
        }

        otpEntity.setUsed(true);
        otpRepository.save(otpEntity);

        User user = userRepository.findByMobileNumber(request.getMobileNumber())
                .orElseThrow(() -> new RuntimeException("User not found"));
        String token = jwtService.generateToken(user);
        return new OtpResponse("OTP verified successfully", true, token);
    }

    private String generateRandomOtp() {
        return "1234";
    }

    private void logOtpGeneration(String mobileNumber) {
        log.info("OTP generated for mobile number ending with: ...{}",
                mobileNumber.substring(Math.max(0, mobileNumber.length() - 4)));
    }
}
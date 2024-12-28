package com.famjam.famjam.service;

import com.famjam.famjam.dto.request.SignupRequest;
import com.famjam.famjam.dto.request.LoginRequest;
import com.famjam.famjam.dto.request.ForgotPasswordRequest;
import com.famjam.famjam.dto.request.ResetPasswordRequest;
import com.famjam.famjam.dto.response.AuthResponse;
import com.famjam.famjam.entity.Role;
import com.famjam.famjam.entity.RoleType;
import com.famjam.famjam.entity.User;
import com.famjam.famjam.repository.RoleRepository;
import com.famjam.famjam.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.famjam.famjam.dto.request.OtpRequest;
import com.famjam.famjam.dto.request.OtpVerificationRequest;
import java.time.LocalDateTime;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;
    private final OtpService otpService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public AuthResponse signup(SignupRequest request) {
        if (userRepository.existsByMobileNumber(request.getMobileNumber())) {
            throw new RuntimeException("Mobile number already registered");
        }

        User user = new User();
        user.setMobileNumber(request.getMobileNumber());
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(RoleType.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Default role not found"));
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        // Generate OTP for verification
        OtpRequest otpRequest = new OtpRequest();
        otpRequest.setMobileNumber(request.getMobileNumber());
        otpService.generateOtp(otpRequest);
        return new AuthResponse("OTP sent successfully", true, null);
    }

    public AuthResponse verifyAndLogin(String mobileNumber, String otp) {
        // Verify OTP
        OtpVerificationRequest verificationRequest = new OtpVerificationRequest();
        verificationRequest.setMobileNumber(mobileNumber);
        verificationRequest.setOtp(otp);
        otpService.verifyOtp(verificationRequest);

        User user = userRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setVerified(true);
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        String token = jwtService.generateToken(user);
        return new AuthResponse("Login successful", true, token);
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByMobileNumber(request.getMobileNumber())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(user);
        return new AuthResponse("Login successful", true, token);
    }

    public AuthResponse initiatePasswordReset(ForgotPasswordRequest request) {
        User user = userRepository.findByMobileNumber(request.getMobileNumber())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Generate and send OTP
        OtpRequest otpRequest = new OtpRequest();
        otpRequest.setMobileNumber(request.getMobileNumber());
        otpService.generateOtp(otpRequest);

        return new AuthResponse("Password reset OTP sent successfully", true, null);
    }

    public AuthResponse resetPassword(ResetPasswordRequest request) {
        // Verify OTP first
        OtpVerificationRequest verificationRequest = new OtpVerificationRequest();
        verificationRequest.setMobileNumber(request.getMobileNumber());
        verificationRequest.setOtp(request.getOtp());
        otpService.verifyOtp(verificationRequest);

        // Update password
        User user = userRepository.findByMobileNumber(request.getMobileNumber())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        return new AuthResponse("Password reset successful", true, null);
    }

    public AuthResponse sendOtp(OtpRequest request) {
        User user = userRepository.findByMobileNumber(request.getMobileNumber())
                .orElseGet(() -> createNewUser(request));

        // Generate and send OTP
        otpService.generateOtp(request);

        return new AuthResponse("OTP sent successfully", true, null);
    }

    private User createNewUser(OtpRequest request) {
        User user = new User();
        user.setMobileNumber(request.getMobileNumber());
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(RoleType.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Default role not found"));
        roles.add(userRole);
        user.setRoles(roles);

        return userRepository.save(user);
    }
}
package com.famjam.famjam.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class OtpVerificationRequest {
    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number format")
    private String mobileNumber;

    @NotBlank(message = "OTP is required")
    @Pattern(regexp = "^[0-9]{4}$", message = "Invalid OTP format")
    private String otp;
}
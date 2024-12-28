package com.famjam.famjam.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ResetPasswordRequest {
    @NotBlank
    private String mobileNumber;

    @NotBlank
    private String otp;

    @NotBlank
    private String newPassword;
}
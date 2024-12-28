package com.famjam.famjam.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OtpRequest {
    @NotBlank(message = "Mobile number is required")
    private String mobileNumber;

    private String fullName;
    private String email;
}
package com.famjam.famjam.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SignupRequest {
    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number format")
    private String mobileNumber;

    @NotBlank
    @Size(min = 3, max = 50)
    private String fullName;

    @Email
    private String email;
}
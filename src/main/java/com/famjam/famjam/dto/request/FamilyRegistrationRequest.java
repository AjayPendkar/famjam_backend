package com.famjam.famjam.dto.request;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class FamilyRegistrationRequest {
    @NotBlank(message = "Family name is required")
    private String familyName;

    private String description;
    private String location;
    private String religion;
    private String caste;
    private String motherTongue;
}
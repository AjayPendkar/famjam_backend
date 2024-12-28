package com.famjam.famjam.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FamilyUpdateRequest {
    @NotBlank(message = "Family name is required")
    private String familyName;
    private String description;
    private String location;
    private String religion;
    private String caste;
    private String motherTongue;
}
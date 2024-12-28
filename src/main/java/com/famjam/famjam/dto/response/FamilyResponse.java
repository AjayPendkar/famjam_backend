package com.famjam.famjam.dto.response;

import lombok.Data;

@Data
public class FamilyResponse {
    private Long id;
    private String familyName;
    private String description;
    private String location;
    private String religion;
    private String caste;
    private String motherTongue;
    private String familyHeadName;
    private boolean isPremium;
}
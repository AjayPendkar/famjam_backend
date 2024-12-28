package com.famjam.famjam.dto.response;

import lombok.Data;
import java.util.List;

@Data
public class FamilyProfileResponse {
    private Long id;
    private String familyName;
    private String headName;
    private String location;
    private String description;
    private boolean isPremium;
    private List<FamilyMemberDTO> members;
    private String profilePicture;
}
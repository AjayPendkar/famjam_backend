package com.famjam.famjam.dto.response;

import lombok.Data;

@Data
public class FamilyMemberDTO {
    private String fullName;
    private String relation;
    private String gender;
    private String maritalStatus;
}
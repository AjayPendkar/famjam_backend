package com.famjam.famjam.controller;

import com.famjam.famjam.dto.request.FamilyRegistrationRequest;
import com.famjam.famjam.dto.request.FamilyUpdateRequest;
import com.famjam.famjam.service.FamilyService;
import com.famjam.famjam.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/family")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Family", description = "Family management APIs")
public class FamilyController {
    private final FamilyService familyService;
    private final JwtService jwtService;

    @PostMapping("/create")
    @Operation(summary = "Create a new family profile")
    public ResponseEntity<?> createFamily(
            @RequestHeader("Authorization") String token,
            @Valid @RequestBody FamilyRegistrationRequest request) {
        String mobileNumber = jwtService.extractMobileNumber(token.substring(7));
        return ResponseEntity.ok(familyService.createFamily(mobileNumber, request));
    }

    @GetMapping("/{familyId}")
    @Operation(summary = "Get family details by ID")
    public ResponseEntity<?> getFamilyDetails(
            @RequestHeader("Authorization") String token,
            @PathVariable Long familyId) {
        return ResponseEntity.ok(familyService.getFamilyDetails(familyId));
    }

    @GetMapping("/search")
    @Operation(summary = "Search families by criteria")
    public ResponseEntity<?> searchFamilies(
            @RequestHeader("Authorization") String token,
            @RequestParam String location,
            @RequestParam(required = false) String religion) {
        return ResponseEntity.ok(familyService.findMatchingFamilies(location, religion));
    }
}
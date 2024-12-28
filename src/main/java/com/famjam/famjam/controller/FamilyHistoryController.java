package com.famjam.famjam.controller;

import com.famjam.famjam.dto.request.FamilyHistoryRequest;
import com.famjam.famjam.service.FamilyHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/families/{familyId}/history")
@RequiredArgsConstructor
public class FamilyHistoryController {
    private final FamilyHistoryService familyHistoryService;

    @PostMapping
    @Operation(summary = "Add family history")
    public ResponseEntity<?> addFamilyHistory(
            @PathVariable Long familyId,
            @RequestBody FamilyHistoryRequest request) {
        return ResponseEntity.ok(familyHistoryService.addFamilyHistory(familyId, request));
    }
}
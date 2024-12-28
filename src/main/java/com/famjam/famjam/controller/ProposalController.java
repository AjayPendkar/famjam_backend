package com.famjam.famjam.controller;

import com.famjam.famjam.dto.request.ProposalRequest;
import com.famjam.famjam.service.ProposalService;
import com.famjam.famjam.service.JwtService;
import com.famjam.famjam.service.FamilyService;
import com.famjam.famjam.entity.Family;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/proposals")
@RequiredArgsConstructor
public class ProposalController {
    private final ProposalService proposalService;
    private final JwtService jwtService;
    private final FamilyService familyService;

    @PostMapping("/send/{toFamilyId}")
    public ResponseEntity<?> sendProposal(
            @RequestHeader("Authorization") String token,
            @PathVariable Long toFamilyId,
            @Valid @RequestBody ProposalRequest request) {
        Family fromFamily = familyService.getFamilyByMobileNumber(token);
        return ResponseEntity.ok(proposalService.sendProposal(fromFamily.getId(), toFamilyId, request));
    }

    @GetMapping("/received")
    public ResponseEntity<?> getReceivedProposals(@RequestHeader("Authorization") String token) {
        Family family = familyService.getFamilyByMobileNumber(token);
        return ResponseEntity.ok(proposalService.getReceivedProposals(family.getId()));
    }

    @GetMapping("/sent")
    public ResponseEntity<?> getSentProposals(@RequestHeader("Authorization") String token) {
        Family family = familyService.getFamilyByMobileNumber(token);
        return ResponseEntity.ok(proposalService.getSentProposals(family.getId()));
    }
}
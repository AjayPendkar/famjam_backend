package com.famjam.famjam.controller;

import com.famjam.famjam.service.ConnectionService;
import com.famjam.famjam.service.FamilyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/connections")
@RequiredArgsConstructor
public class ConnectionController {
    private final ConnectionService connectionService;
    private final FamilyService familyService;

    @PostMapping("/{connectionId}/accept")
    public ResponseEntity<?> acceptConnection(
            @PathVariable Long connectionId) {
        return ResponseEntity.ok(connectionService.acceptRequest(connectionId));
    }

    @PostMapping("/{connectionId}/reject")
    public ResponseEntity<?> rejectConnection(
            @PathVariable Long connectionId) {
        return ResponseEntity.ok(connectionService.rejectRequest(connectionId));
    }
}
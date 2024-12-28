package com.famjam.famjam.dto.response;

import lombok.Data;

@Data
public class OtpResponse {
    private String message;
    private boolean success;
    private String token;

    public OtpResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public OtpResponse(String message, boolean success, String token) {
        this.message = message;
        this.success = success;
        this.token = token;
    }
}
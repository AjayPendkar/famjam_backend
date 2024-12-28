package com.famjam.famjam.dto.response;

import com.famjam.famjam.entity.NotificationType;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class NotificationResponse {
    private Long id;
    private String message;
    private NotificationType type;
    private boolean read;
    private LocalDateTime createdAt;
}
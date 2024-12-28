package com.famjam.famjam.service;

import com.famjam.famjam.dto.response.NotificationResponse;
import com.famjam.famjam.entity.Family;
import com.famjam.famjam.entity.Notification;
import com.famjam.famjam.entity.NotificationType;
import com.famjam.famjam.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public List<NotificationResponse> getUserNotifications(String mobileNumber) {
        return notificationRepository.findByUserMobileNumberOrderByCreatedAtDesc(mobileNumber)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    private NotificationResponse convertToResponse(Notification notification) {
        return NotificationResponse.builder()
                .id(notification.getId())
                .message(notification.getMessage())
                .type(notification.getType())
                .read(notification.isRead())
                .createdAt(notification.getCreatedAt())
                .build();
    }

    public void sendConnectionRequest(Family fromFamily, Family toFamily) {
        Notification notification = new Notification();
        notification.setUser(toFamily.getFamilyHead());
        notification.setType(NotificationType.CONNECTION_REQUEST);
        notification.setMessage(String.format("New connection request from %s", fromFamily.getFamilyName()));
        notification.setRead(false);

        notificationRepository.save(notification);
    }

    public void sendProposalNotification(Family fromFamily, Family toFamily) {
        Notification notification = new Notification();
        notification.setUser(toFamily.getFamilyHead());
        notification.setType(NotificationType.PROPOSAL);
        notification.setMessage(String.format("New proposal from %s", fromFamily.getFamilyName()));
        notification.setRead(false);

        notificationRepository.save(notification);
    }
}
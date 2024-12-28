package com.famjam.famjam.repository;

import com.famjam.famjam.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserMobileNumberOrderByCreatedAtDesc(String mobileNumber);
}
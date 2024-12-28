package com.famjam.famjam.repository;

import com.famjam.famjam.entity.OtpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OtpRepository extends JpaRepository<OtpEntity, Long> {
    Optional<OtpEntity> findTopByMobileNumberOrderByExpiryTimeDesc(String mobileNumber);
}
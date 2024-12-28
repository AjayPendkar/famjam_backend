package com.famjam.famjam.repository;

import com.famjam.famjam.entity.FamilyHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyHistoryRepository extends JpaRepository<FamilyHistory, Long> {
}
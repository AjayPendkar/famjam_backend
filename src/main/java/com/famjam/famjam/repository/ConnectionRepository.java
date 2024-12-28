package com.famjam.famjam.repository;

import com.famjam.famjam.entity.Connection;
import com.famjam.famjam.entity.ConnectionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Long> {
    List<Connection> findByToFamilyIdAndStatus(Long toFamilyId, ConnectionStatus status);

    List<Connection> findByFromFamilyIdAndStatus(Long fromFamilyId, ConnectionStatus status);
}
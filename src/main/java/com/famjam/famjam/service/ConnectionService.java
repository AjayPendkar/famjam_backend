package com.famjam.famjam.service;

import com.famjam.famjam.entity.Connection;
import com.famjam.famjam.entity.ConnectionStatus;
import com.famjam.famjam.entity.Family;
import com.famjam.famjam.exception.ResourceNotFoundException;
import com.famjam.famjam.repository.ConnectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConnectionService {
    private final ConnectionRepository connectionRepository;
    private final FamilyService familyService;

    public Connection createConnection(Long fromFamilyId, Long toFamilyId) {
        Family fromFamily = familyService.getFamilyById(fromFamilyId);
        Family toFamily = familyService.getFamilyById(toFamilyId);

        Connection connection = new Connection();
        connection.setFromFamily(fromFamily);
        connection.setToFamily(toFamily);

        return connectionRepository.save(connection);
    }

    public List<Connection> getReceivedConnections(Long familyId) {
        return connectionRepository.findByToFamilyIdAndStatus(familyId, ConnectionStatus.PENDING);
    }

    public List<Connection> getSentConnections(Long familyId) {
        return connectionRepository.findByFromFamilyIdAndStatus(familyId, ConnectionStatus.PENDING);
    }

    public Connection acceptRequest(Long connectionId) {
        Connection connection = connectionRepository.findById(connectionId)
                .orElseThrow(() -> new ResourceNotFoundException("Connection not found"));
        connection.setStatus(ConnectionStatus.ACCEPTED);
        return connectionRepository.save(connection);
    }

    public Connection rejectRequest(Long connectionId) {
        Connection connection = connectionRepository.findById(connectionId)
                .orElseThrow(() -> new ResourceNotFoundException("Connection not found"));
        connection.setStatus(ConnectionStatus.REJECTED);
        return connectionRepository.save(connection);
    }
}
package com.famjam.famjam.service;

import com.famjam.famjam.entity.Connection;
import com.famjam.famjam.entity.Family;
import com.famjam.famjam.repository.ConnectionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ConnectionServiceTest {

    @MockBean
    private ConnectionRepository connectionRepository;

    @MockBean
    private FamilyService familyService;

    @Autowired
    private ConnectionService connectionService;

    @Test
    void whenCreateConnection_thenReturnConnection() {
        // Arrange
        Family fromFamily = new Family();
        fromFamily.setId(1L);
        fromFamily.setFamilyName("Family 1");

        Family toFamily = new Family();
        toFamily.setId(2L);
        toFamily.setFamilyName("Family 2");

        Connection connection = new Connection();
        connection.setFromFamily(fromFamily);
        connection.setToFamily(toFamily);

        when(familyService.getFamilyById(1L)).thenReturn(fromFamily);
        when(familyService.getFamilyById(2L)).thenReturn(toFamily);
        when(connectionRepository.save(any(Connection.class))).thenReturn(connection);

        // Act
        Connection created = connectionService.createConnection(1L, 2L);

        // Assert
        assertNotNull(created);
        assertEquals(fromFamily, created.getFromFamily());
        assertEquals(toFamily, created.getToFamily());
    }
}
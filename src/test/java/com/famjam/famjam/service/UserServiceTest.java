package com.famjam.famjam.service;

import com.famjam.famjam.entity.User;
import com.famjam.famjam.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void whenFindByMobileNumber_thenReturnUser() {
        // Arrange
        User user = new User();
        user.setMobileNumber("1234567890");
        user.setFullName("Test User");

        when(userRepository.findByMobileNumber("1234567890"))
                .thenReturn(Optional.of(user));

        // Act
        Optional<User> found = userService.findByMobileNumber("1234567890");

        // Assert
        assertTrue(found.isPresent());
        assertEquals("Test User", found.get().getFullName());
    }
}
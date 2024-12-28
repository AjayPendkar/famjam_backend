package com.famjam.famjam.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.famjam.famjam.repository.ProposalRepository;

@TestConfiguration
public class TestConfig {
    @MockBean
    private ProposalRepository proposalRepository; // Mock this to avoid the actual repository

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
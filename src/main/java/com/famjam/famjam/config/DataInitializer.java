package com.famjam.famjam.config;

import com.famjam.famjam.entity.Role;
import com.famjam.famjam.entity.RoleType;
import com.famjam.famjam.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        if (roleRepository.count() == 0) {
            for (RoleType roleType : RoleType.values()) {
                Role role = new Role();
                role.setName(roleType);
                roleRepository.save(role);
            }
        }
    }
}
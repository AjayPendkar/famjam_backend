package com.famjam.famjam.service;

import com.famjam.famjam.entity.Family;
import com.famjam.famjam.repository.FamilyRepository;
import com.famjam.famjam.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FamilyServiceTest extends BaseTest {

    @MockBean
    private FamilyRepository familyRepository;

    @Autowired
    private FamilyService familyService;

    @Test
    void whenFindById_thenReturnFamily() {
        // Arrange
        Family family = new Family();
        family.setId(1L);
        family.setFamilyName("Test Family");

        when(familyRepository.findById(1L))
                .thenReturn(Optional.of(family));

        // Act
        Optional<Family> found = familyService.findById(1L);

        // Assert
        assertTrue(found.isPresent());
        assertEquals("Test Family", found.get().getFamilyName());
    }
}
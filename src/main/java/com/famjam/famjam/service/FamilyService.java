package com.famjam.famjam.service;

import com.famjam.famjam.dto.request.FamilyRegistrationRequest;
import com.famjam.famjam.dto.request.FamilyUpdateRequest;
import com.famjam.famjam.dto.response.FamilyResponse;
import com.famjam.famjam.entity.Family;
import com.famjam.famjam.entity.User;
import com.famjam.famjam.exception.DuplicateResourceException;
import com.famjam.famjam.exception.ResourceNotFoundException;
import com.famjam.famjam.repository.FamilyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FamilyService {
    private static final String FAMILY_NOT_FOUND = "Family not found";

    private final FamilyRepository familyRepository;
    private final UserService userService;
    private final JwtService jwtService;

    @Transactional
    public FamilyResponse createFamily(String mobileNumber, FamilyRegistrationRequest request) {
        User user = userService.getCurrentUser(mobileNumber);

        // Check if user already has a family
        if (familyRepository.existsByFamilyHead(user)) {
            throw new DuplicateResourceException("User already has a family profile");
        }

        // Validate family name uniqueness
        String uniqueIdentifier = generateUniqueIdentifier(request.getFamilyName());
        if (familyRepository.existsByUniqueIdentifier(uniqueIdentifier)) {
            throw new DuplicateResourceException("Family with this name already exists");
        }

        Family family = new Family();
        family.setFamilyName(request.getFamilyName());
        family.setDescription(request.getDescription());
        family.setLocation(request.getLocation());
        family.setReligion(request.getReligion());
        family.setCaste(request.getCaste());
        family.setMotherTongue(request.getMotherTongue());
        family.setFamilyHead(user);
        family.setPremium(false);

        Family savedFamily = familyRepository.save(family);
        return convertToResponse(savedFamily);
    }

    private String generateUniqueIdentifier(String familyName) {
        return familyName.toLowerCase().replaceAll("\\s+", "-") +
                "-" + System.currentTimeMillis();
    }

    private FamilyResponse convertToResponse(Family family) {
        FamilyResponse response = new FamilyResponse();
        response.setId(family.getId());
        response.setFamilyName(family.getFamilyName());
        response.setDescription(family.getDescription());
        response.setLocation(family.getLocation());
        response.setReligion(family.getReligion());
        response.setCaste(family.getCaste());
        response.setMotherTongue(family.getMotherTongue());
        response.setFamilyHeadName(family.getFamilyHead().getFullName());
        response.setPremium(family.isPremium());
        return response;
    }

    public Family updateFamily(String mobileNumber, FamilyUpdateRequest request) {
        User user = userService.getCurrentUser(mobileNumber);
        Family family = familyRepository.findByFamilyHead(user)
                .orElseThrow(() -> new RuntimeException(FAMILY_NOT_FOUND));

        family.setFamilyName(request.getFamilyName());
        family.setDescription(request.getDescription());
        family.setLocation(request.getLocation());
        // Update other fields as needed

        return familyRepository.save(family);
    }

    public Family getFamilyDetails(Long familyId) {
        return familyRepository.findById(familyId)
                .orElseThrow(() -> new RuntimeException(FAMILY_NOT_FOUND));
    }

    public Optional<Family> findById(Long id) {
        return familyRepository.findById(id);
    }

    public List<FamilyResponse> findMatchingFamilies(String location, String religion) {
        List<Family> families = familyRepository.findMatchingFamilies(location, religion);
        return families.stream()
                .map(this::convertToResponse)
                .toList();
    }

    public Family getFamilyByMobileNumber(String token) {
        String mobileNumber = jwtService.extractMobileNumber(token.substring(7));
        User user = userService.getCurrentUser(mobileNumber);
        return familyRepository.findByFamilyHead(user)
                .orElseThrow(() -> new ResourceNotFoundException(FAMILY_NOT_FOUND));
    }

    public Family getFamilyById(Long id) {
        return familyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(FAMILY_NOT_FOUND));
    }
}
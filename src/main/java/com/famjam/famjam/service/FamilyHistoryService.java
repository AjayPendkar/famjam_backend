package com.famjam.famjam.service;

import com.famjam.famjam.dto.request.FamilyHistoryRequest;
import com.famjam.famjam.entity.Family;
import com.famjam.famjam.entity.FamilyHistory;
import com.famjam.famjam.repository.FamilyHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FamilyHistoryService {
    private final FamilyHistoryRepository familyHistoryRepository;
    private final FamilyService familyService;

    public FamilyHistory addFamilyHistory(Long familyId, FamilyHistoryRequest request) {
        Family family = familyService.getFamilyById(familyId);

        FamilyHistory history = new FamilyHistory();
        history.setFamily(family);
        history.setDescription(request.getDescription());
        history.setLineage(request.getLineage());

        return familyHistoryRepository.save(history);
    }
}
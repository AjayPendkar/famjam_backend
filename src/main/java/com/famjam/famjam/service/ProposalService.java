package com.famjam.famjam.service;

import com.famjam.famjam.dto.request.ProposalRequest;
import com.famjam.famjam.entity.Family;
import com.famjam.famjam.entity.Proposal;
import com.famjam.famjam.entity.ProposalStatus;
import com.famjam.famjam.repository.ProposalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProposalService {
    private final ProposalRepository proposalRepository;
    private final FamilyService familyService;
    private final NotificationService notificationService;

    public Proposal sendProposal(Long fromFamilyId, Long toFamilyId, ProposalRequest request) {
        Family fromFamily = familyService.getFamilyById(fromFamilyId);
        Family toFamily = familyService.getFamilyById(toFamilyId);

        Proposal proposal = new Proposal();
        proposal.setFromFamily(fromFamily);
        proposal.setToFamily(toFamily);
        proposal.setMessage(request.getMessage());
        proposal.setPreferredTime(request.getPreferredTime());
        proposal.setStatus(ProposalStatus.PENDING);

        Proposal savedProposal = proposalRepository.save(proposal);
        notificationService.sendProposalNotification(fromFamily, toFamily);

        return savedProposal;
    }

    public List<Proposal> getReceivedProposals(Long familyId) {
        return proposalRepository.findByToFamilyId(familyId);
    }

    public List<Proposal> getSentProposals(Long familyId) {
        return proposalRepository.findByFromFamilyId(familyId);
    }
}
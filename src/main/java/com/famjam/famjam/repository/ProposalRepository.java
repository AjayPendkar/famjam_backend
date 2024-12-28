package com.famjam.famjam.repository;

import com.famjam.famjam.entity.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.repository.query.Param;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long> {
    @Query("SELECT p FROM Proposal p WHERE p.targetFamily.familyHead.mobileNumber = :mobileNumber")
    List<Proposal> findByTargetFamilyFamilyHeadMobileNumber(@Param("mobileNumber") String mobileNumber);

    List<Proposal> findByRequestingFamilyFamilyHeadMobileNumber(String mobileNumber);

    List<Proposal> findByFromFamilyId(Long fromFamilyId);

    List<Proposal> findByToFamilyId(Long toFamilyId);
}
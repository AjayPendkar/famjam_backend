package com.famjam.famjam.repository;

import com.famjam.famjam.entity.Family;
import com.famjam.famjam.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Long> {
    Optional<Family> findByFamilyHead(User familyHead);

    boolean existsByFamilyHead(User familyHead);

    boolean existsByUniqueIdentifier(String uniqueIdentifier);

    @Query("SELECT f FROM Family f WHERE " +
            "LOWER(f.location) = LOWER(:location) AND " +
            "(:religion IS NULL OR LOWER(f.religion) = LOWER(:religion))")
    List<Family> findMatchingFamilies(String location, String religion);
}
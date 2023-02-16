package com.example.pbl.repositories;

import com.example.pbl.entity.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequirementRepository extends JpaRepository<Requirement, Long> {
    List<Requirement>findByAuthorName(String name);
    List<Requirement>findByRecipientPoliticianId(Long id);
}

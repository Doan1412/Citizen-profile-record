package com.example.pbl.repositories;

import com.example.pbl.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FamilyRepository extends JpaRepository<Family,Long> {
    Optional<Family>findById(Long id);
}

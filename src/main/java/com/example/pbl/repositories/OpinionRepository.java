package com.example.pbl.repositories;

import com.example.pbl.entity.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpinionRepository extends JpaRepository<Opinion, Long> {
    void deleteByCitizenCitizenId(Long id);
}

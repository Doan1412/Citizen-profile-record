package com.example.pbl.repositories;

import com.example.pbl.entity.Notification;
import com.example.pbl.entity.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpinionRepository extends JpaRepository<Opinion, Long> {
    void deleteByCitizenCitizenId(Long id);
    List<Opinion> findByCitizenCitizenId(Long id);
}

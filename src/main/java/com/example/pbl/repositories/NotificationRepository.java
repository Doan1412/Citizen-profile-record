package com.example.pbl.repositories;

import com.example.pbl.entity.Citizen;
import com.example.pbl.entity.Notification;
import com.example.pbl.entity.Politician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {
    List<Notification> findByPoliticianPoliticianId(Long id);
    List<Notification> findByCitizensCitizenId(Long id);
}

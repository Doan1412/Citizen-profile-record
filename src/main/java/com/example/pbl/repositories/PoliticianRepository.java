package com.example.pbl.repositories;

import com.example.pbl.entity.Citizen;
import com.example.pbl.entity.Politician;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PoliticianRepository extends JpaRepository<Politician,Long> {
    List<Politician>findByCitizenLocationCity(String name);
}

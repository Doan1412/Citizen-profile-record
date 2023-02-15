package com.example.pbl.repositories;

import com.example.pbl.entity.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen,Long> {
    List<Citizen>findByLocationCity(String city);
    List<Citizen>findByLocationQuarter(String quarter);
    List<Citizen>findByLocationTown(String town);
    List<Citizen>findByLocationDistrict(String district);
    Optional<Citizen> findById(Long id);
    List<Citizen>findByName(String name);
    //List<Citizen>findByFamilyFamily_id(Long idFamily);
}

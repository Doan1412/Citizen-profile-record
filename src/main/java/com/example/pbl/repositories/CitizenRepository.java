package com.example.pbl.repositories;

import com.example.pbl.entity.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen,Long> {
    List<Citizen>findByLocationCityContainingIgnoreCase (String city);
    List<Citizen>findByLocationQuarterContainingIgnoreCase(String quarter);
    List<Citizen>findByLocationTownContainingIgnoreCase(String town);
    List<Citizen>findByLocationDistrictContainingIgnoreCase(String district);
    Optional<Citizen> findById(Long id);
    List<Citizen>findByFamilyId (Long id);
    List<Citizen>findByName(String name);
    List<Citizen>findByMarried(boolean isMarried);
    long countByMilitaryServiceFalseAndBirthBetween(Date date1,Date date2);
    long countByMarriedTrue();
    long countByBirthBetween(Date date1,Date date2);
    long countByCriminalRecordIsNotNull();
    List<Citizen> findByCriminalRecordIsNotNullAndLocationCity(String city);
    List<Citizen> findByCriminalRecordIsNotNullAndLocationTown(String town);
    List<Citizen> findByCriminalRecordIsNotNullAndLocationDistrict(String district);
    List<Citizen> findByCriminalRecordIsNotNullAndLocationQuarter(String quarter);
    long countByCriminalRecordIsNotNullAndLocationCity(String city);
    long countByCriminalRecordIsNotNullAndLocationTown(String town);
    long countByCriminalRecordIsNotNullAndLocationDistrict(String district);
    long countByCriminalRecordIsNotNullAndLocationQuarter(String quarter);
}

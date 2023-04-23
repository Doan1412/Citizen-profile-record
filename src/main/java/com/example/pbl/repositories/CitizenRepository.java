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
    List<Citizen>findByName(String name);
    List<Citizen>findByMarriedAndLocationDistrictContainingIgnoreCase(boolean isMarried,String d);
    List<Citizen>findByMarriedAndLocationTownContainingIgnoreCase(boolean isMarried,String d);
    List<Citizen>findByMarriedAndLocationQuarterContainingIgnoreCase(boolean isMarried,String d);
    List<Citizen>findByMarriedAndLocationCityContainingIgnoreCase(boolean isMarried,String d);
    long countByMarriedAndLocationCityContainingIgnoreCase(boolean isMarried,String d);
    long countByMarriedAndLocationDistrictContainingIgnoreCase(boolean isMarried,String d);
    long countByMarriedAndLocationTownContainingIgnoreCase(boolean isMarried,String d);
    long countByMarriedAndLocationQuarterContainingIgnoreCase(boolean isMarried,String d);
    List<Citizen>findByMarried(boolean isMarried);
    long countByMarried(boolean isMarried);
    long countByMilitaryServiceAndBirthBetween(boolean f,Date date1,Date date2);
    List<Citizen>findByMilitaryServiceAndBirthBetween(boolean f,Date date1,Date date2);

    long countByMilitaryServiceAndBirthBetweenAndLocationDistrictContainingIgnoreCase(boolean f,Date date1,Date date2,String district);
    List<Citizen>findByMilitaryServiceAndBirthBetweenAndLocationDistrictContainingIgnoreCase(boolean f,Date date1,Date date2,String district);
    long countByMilitaryServiceFalseAndBirthBetweenAndLocationTownContainingIgnoreCase(Date date1,Date date2,String town);
    List<Citizen>findByMilitaryServiceFalseAndBirthBetweenAndLocationTownContainingIgnoreCase(Date date1,Date date2,String town);
    long countByMilitaryServiceFalseAndBirthBetweenAndLocationCityContainingIgnoreCase(Date date1,Date date2,String city);
    List<Citizen>findByMilitaryServiceAndBirthBetweenAndLocationCityContainingIgnoreCase(boolean f,Date date1,Date date2,String city);
    long countByMilitaryServiceFalseAndBirthBetweenAndLocationQuarterContainingIgnoreCase(Date date1,Date date2,String district);
    List<Citizen>findByMilitaryServiceFalseAndBirthBetweenAndLocationQuarterContainingIgnoreCase(Date date1,Date date2,String district);
    long countByBirthBetween(Date date1,Date date2);
    long countByBirthBetweenAndLocationCityContainingIgnoreCase(Date date1,Date date2,String s);
    long countByBirthBetweenAndLocationQuarterContainingIgnoreCase(Date date1,Date date2,String s);
    long countByBirthBetweenAndLocationDistrictContainingIgnoreCase(Date date1,Date date2,String s);
    long countByBirthBetweenAndLocationTownContainingIgnoreCase(Date date1,Date date2,String s);
    long countByBirthBeforeAndLocationCityContainingIgnoreCase(Date date,String s);
    long countByBirthBeforeAndLocationQuarterContainingIgnoreCase(Date date,String s);
    long countByBirthBeforeAndLocationDistrictContainingIgnoreCase(Date date,String s);
    long countByBirthBeforeAndLocationTownContainingIgnoreCase(Date date,String s);

//    long countByCriminalRecordIsNotNull();
    long countByLocationCity(String s);
    long countByLocationTown(String s);
    long countByLocationQuarter(String s);
    long countByLocationDistrict(String s);
    List<Citizen> findByCriminalRecordIsNotAndLocationCityContainingIgnoreCase(String s,String city) ;

    List<Citizen> findByCriminalRecordIsNotAndLocationTownContainingIgnoreCase(String s,String town);
    List<Citizen> findByCriminalRecordIsNotAndLocationDistrictContainingIgnoreCase(String s,String district);
    List<Citizen> findByCriminalRecordIsNotAndLocationQuarterContainingIgnoreCase(String s,String quarter);
    List<Citizen> findByCriminalRecordIsNot(String s);
    long countByCriminalRecordIsNot(String s);
    long countByCriminalRecordIsNotAndLocationCityContainingIgnoreCase(String s,String city);
    long countByCriminalRecordIsNotAndLocationTownContainingIgnoreCase(String s,String town);
    long countByCriminalRecordIsNotAndLocationDistrictContainingIgnoreCase(String s,String district);
    long countByCriminalRecordIsNotAndLocationQuarterContainingIgnoreCase(String s,String quarter);
    long countByGenderAndLocationDistrictContainingIgnoreCase(boolean f,String d);
    long countByGenderAndLocationCityContainingIgnoreCase(boolean f,String d);
    long countByGenderAndLocationQuarterContainingIgnoreCase(boolean f,String d);
    long countByGenderAndLocationTownContainingIgnoreCase(boolean f,String d);
    long countByGender(boolean f);
    long countByBirthBefore(Date date);
    List<Citizen> findByBirthBefore(Date date);
    List<Citizen> findByBirthBetween(Date d1,Date d2);
    void deleteByCitizenId(Long id);
    List<Citizen>findByFamilyId (Long id);
}

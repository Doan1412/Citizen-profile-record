package com.example.pbl.repositories;

import com.example.pbl.entity.Citizen;
import com.example.pbl.entity.Politician;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PoliticianRepository extends JpaRepository<Politician,Long> {
    List<Politician>findByCitizenNameContaining(String name);
    List<Politician>findByLevelManagerAndAreaManage(String levelManage,String areaManage);
//    @Query("SELECT t FROM TenBang t WHERE t.tenCot LIKE %?1% COLLATE utf8_general_ci")
//    List<TenBang> findByTenCotWithoutDau(String tenCot);
}

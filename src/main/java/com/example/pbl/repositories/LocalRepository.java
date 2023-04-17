package com.example.pbl.repositories;

import com.example.pbl.entity.Appointment;
import com.example.pbl.entity.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocalRepository extends JpaRepository<Appointment, Local> {
    @Query("SELECT DISTINCT l.province FROM Local l")
    List<String> listProvinces();
    @Query("SELECT DISTINCT l.district FROM Local l WHERE l.province = :province")
    List<String> listDistrictsByProvince(@Param("province") String province);
    @Query("SELECT DISTINCT l.ward FROM Local l WHERE l.province = :province AND l.district = :district")
    List<String> listWard(@Param("province") String province,@Param("district") String district);
}

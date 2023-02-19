package com.example.pbl.repositories;

import com.example.pbl.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    List<Appointment> findByAppointmentDateAndPoliticianPoliticianId(Date date,Long id);
    List<Appointment> findByPoliticianPoliticianId(Long id);
    List<Appointment> findByCitizenCitizenId(Long id);
}

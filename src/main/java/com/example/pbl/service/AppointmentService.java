package com.example.pbl.service;

import com.example.pbl.DTO.AppointmentDto;
import com.example.pbl.entity.Appointment;
import com.example.pbl.entity.Politician;
import com.example.pbl.repositories.AppointmentRepository;
import com.example.pbl.repositories.CitizenRepository;
import com.example.pbl.repositories.PoliticianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private final AppointmentRepository appointmentRepository;
    private final CitizenRepository citizenRepository;
    private final PoliticianRepository politicianRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              CitizenRepository citizenRepository,
                              PoliticianRepository politicianRepository) {
        this.appointmentRepository = appointmentRepository;
        this.citizenRepository = citizenRepository;
        this.politicianRepository = politicianRepository;
    }

    private boolean isOverlapping(Appointment newAppointment, Long politician_id) {
        List<Appointment> existingAppointments = appointmentRepository.findByAppointmentDateAndPoliticianPoliticianId(newAppointment.getAppointmentDate(),politician_id);
        for (Appointment existingAppointment : existingAppointments) {
            if (existingAppointment.getEndTime().compareTo(newAppointment.getStartTime()) > 0 &&
                    existingAppointment.getStartTime().compareTo(newAppointment.getEndTime()) < 0) {
                return true;
            }
        }
        return false;
    }
    public ResponseEntity<Appointment> addAppointment(AppointmentDto appointmentDto){
        var appointment=Appointment.builder()
                .citizen(citizenRepository.findById(appointmentDto.getCitizen_id()).get())
                .politician(politicianRepository.findById(appointmentDto.getPolitician_id()).get())
                .appointmentDate(appointmentDto.getAppointmentDate())
                .startTime(appointmentDto.getStartTime())
                .endTime(appointmentDto.getEndTime())
                .build();
        //Appointment appointment=new Appointment(citizenRepository.findById(appointmentDto.getCitizen_id()).get(),politicianRepository.findById(appointmentDto.getPolitician_id()).get(),appointmentDto.getAppointmentDate(), appointmentDto.getStartTime(), appointmentDto.getEndTime());
        if(isOverlapping(appointment, appointmentDto.getPolitician_id())){
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        appointmentRepository.save(appointment);
        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }
    public void deleteAppointment(Long id){
        appointmentRepository.deleteById(id);
    }
    public List<Appointment>getCitizenAppointment(Long citizen_id){
        return appointmentRepository.findByCitizenCitizenId(citizen_id);
    }
    public List<Appointment>getPoliticianAppointment(Long politician_id){
        return appointmentRepository.findByPoliticianPoliticianId(politician_id);
    }
    public List<Appointment>getPoliticianAppointmentByDate(Long politician_id, Date date){
        return appointmentRepository.findByAppointmentDateAndPoliticianPoliticianId(date,politician_id);
    }
}

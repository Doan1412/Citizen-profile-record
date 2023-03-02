package com.example.pbl.service;

import com.example.pbl.DTO.AppointmentDto;
import com.example.pbl.entity.Appointment;
import com.example.pbl.entity.Citizen;
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
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    private final AppointmentRepository appointmentRepository;
    @Autowired
    private final CitizenRepository citizenRepository;
    @Autowired
    private final PoliticianRepository politicianRepository;
    @Autowired
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
        Citizen citizen=new Citizen();
        Optional<Citizen> citizenData= citizenRepository.findById(appointmentDto.getCitizen_id());
        if(citizenData.isPresent()){
            citizen=citizenData.get();
        }
        else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        Politician politician=new Politician();
        Optional<Politician> politicianData= politicianRepository.findById(appointmentDto.getPolitician_id());
        if(politicianData.isPresent()){
            politician=politicianData.get();
        }
        else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        var appointment=Appointment.builder()
                .citizen(citizen)
                .politician(politician)
                .appointmentDate(appointmentDto.getAppointmentDate())
                .startTime(appointmentDto.getStartTime())
                .endTime(appointmentDto.getEndTime())
                .status("Đang xử lý")
                .build();
        if(isOverlapping(appointment, appointmentDto.getPolitician_id())){
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        appointmentRepository.save(appointment);
        return new ResponseEntity<>(appointment, HttpStatus.CREATED);
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
    public ResponseEntity<Appointment> updateAppointment(Long id,AppointmentDto appointmentDto){
        var appointment=Appointment.builder()
                .id(id)
                .citizen(citizenRepository.findById(appointmentDto.getCitizen_id()).get())
                .politician(politicianRepository.findById(appointmentDto.getPolitician_id()).get())
                .appointmentDate(appointmentDto.getAppointmentDate())
                .startTime(appointmentDto.getStartTime())
                .endTime(appointmentDto.getEndTime())
                .build();
        if(isOverlapping(appointment, appointmentDto.getPolitician_id())){
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        appointmentRepository.save(appointment);
        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }
    public List<Appointment>getPoliticianAppointmentByDateAndStatus(Long politician_id, Date date,String status){
        return appointmentRepository.findByAppointmentDateAndStatusIgnoreCaseAndPoliticianPoliticianId(date,status,politician_id);
    }

    public ResponseEntity<Appointment> updateAppointmentStatus(long id,String status) {
        Optional<Appointment> appointmentData=appointmentRepository.findById(id);
        if(appointmentData.isPresent()){
            appointmentData.get().setStatus(status);
            appointmentRepository.save(appointmentData.get());
            return new ResponseEntity<>(appointmentData.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
}

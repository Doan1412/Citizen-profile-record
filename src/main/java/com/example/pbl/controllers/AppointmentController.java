package com.example.pbl.controllers;

import com.example.pbl.DTO.AppointmentDto;
import com.example.pbl.DTO.RequirementRequest;
import com.example.pbl.entity.Appointment;
import com.example.pbl.entity.Requirement;
import com.example.pbl.service.AppointmentService;
import com.example.pbl.service.RequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path="api/appointment")
public class AppointmentController {
    @Autowired
    private final AppointmentService appointmentService;
    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/new")
    @PreAuthorize("hasAuthority('CITIZEN')")
    public ResponseEntity<Appointment> addAppointment(
            @RequestBody AppointmentDto appointmentDto
            ) {
        return appointmentService.addAppointment(appointmentDto);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/citizen_id={id}")
    public ResponseEntity<List<Appointment>>getCitizenAppointment(@PathVariable Long id){
        try{
            List<Appointment>appointmentList= appointmentService.getCitizenAppointment(id);
            return new ResponseEntity<>(appointmentList, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/politician_id={id}")
    public ResponseEntity<List<Appointment>>getPoliticianAppointment(@PathVariable Long id){
        try{
            List<Appointment>appointmentList= appointmentService.getPoliticianAppointment(id);
            return new ResponseEntity<>(appointmentList, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/update/id={id}")
    public ResponseEntity<Appointment>updateAppointment(@PathVariable("id") long id,@RequestBody AppointmentDto appointmentDto){
        return appointmentService.updateAppointment(id,appointmentDto);
    }
//    @GetMapping("/politicianDateAppointment")
//    public ResponseEntity<List<Appointment>>getPoliticianAppointment(
//            @RequestBody Date date,
//
//            ){
//        try{
//            List<Appointment>appointmentList= appointmentService.getPoliticianAppointment(id);
//            return new ResponseEntity<>(appointmentList, HttpStatus.OK);
//        }
//        catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
}

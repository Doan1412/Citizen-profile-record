package com.example.pbl.controllers;

import com.example.pbl.DTO.AppointmentDto;
import com.example.pbl.DTO.RequestString;
import com.example.pbl.entity.Appointment;
import com.example.pbl.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    @PreAuthorize("hasAuthority('CITIZEN')")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/citizen_id={id}")
    @PreAuthorize("hasAuthority('CITIZEN')")
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
    @PreAuthorize("hasAuthority('POLITICIAN')")
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
    @PreAuthorize("hasAuthority('CITIZEN')")
    public ResponseEntity<Appointment>updateAppointment(@PathVariable("id") long id,@RequestBody AppointmentDto appointmentDto){
        return appointmentService.updateAppointment(id,appointmentDto);
    }
    @GetMapping("/listAppointment/")
    @PreAuthorize("hasAuthority('POLITICIAN')")
    public ResponseEntity<List<Appointment>>getPoliticianAppointmentByDate(
            @RequestParam long poliId,@RequestParam String dateString
            )throws ParseException {
        {
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = format.parse(dateString);
                List<Appointment> appointmentList = appointmentService.getPoliticianAppointmentByDate(poliId, date);
                return new ResponseEntity<>(appointmentList, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }
    @GetMapping("/politicianDateAppointmentStatus")
    @PreAuthorize("hasAuthority('POLITICIAN')")
    public ResponseEntity<List<Appointment>> getPoliticianAppointmentByDateAndStatus (
            @RequestParam Long poliId, @RequestParam String dateString, @RequestParam String status
    ){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(dateString);
            List<Appointment> appointmentList = appointmentService.getPoliticianAppointmentByDateAndStatus(poliId, date, status);
                return new ResponseEntity<>(appointmentList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PatchMapping("/updateStatus/id={id}")
    @PreAuthorize("hasAuthority('POLITICIAN')")
    public ResponseEntity<Appointment>updateAppointmentStatus(@PathVariable long id,@RequestBody RequestString requestString){
        return appointmentService.updateAppointmentStatus(id,requestString.getString());
    }
}

package com.example.pbl.controllers;

import com.example.pbl.DTO.RequestString;
import com.example.pbl.entity.Citizen;
import com.example.pbl.service.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/citizen")
public class CitizenController {
    private final CitizenService citizenService;
    @Autowired
    public CitizenController(CitizenService citizenService) {
        this.citizenService = citizenService;
    }
    @GetMapping("/listCitizen/country")
    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('POLITICIAN') or hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<Citizen>> getAllCitizen(){
        try {
            List<Citizen> citizenList=citizenService.getAllCitizen();
            return new ResponseEntity<>(citizenList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/listCitizen/city")
    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('POLITICIAN')")
    public ResponseEntity<List<Citizen>> getCityCitizen(@RequestBody RequestString requestString){
        try {
            List<Citizen> citizenList=citizenService.getCityCitizen(requestString.getString());
            return new ResponseEntity<>(citizenList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/listCitizen/town")
    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('POLITICIAN')")
    public ResponseEntity<List<Citizen>> getTownCitizen(@RequestBody RequestString requestString){
        try {
            List<Citizen> citizenList=citizenService.getTownCitizen(requestString.getString());
            return new ResponseEntity<>(citizenList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/listCitizen/district")
    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('POLITICIAN')")
    public ResponseEntity<List<Citizen>> getDistrictCitizen(@RequestBody RequestString requestString){
        try {
            List<Citizen> citizenList=citizenService.getDistrictCitizen(requestString.getString());
            return new ResponseEntity<>(citizenList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/listCitizen/quarter")
    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('POLITICIAN')")
    public ResponseEntity<List<Citizen>> getQuarterCitizen(@RequestBody RequestString requestString){
        try {
            List<Citizen> citizenList=citizenService.getQuarterCitizen(requestString.getString());
            return new ResponseEntity<>(citizenList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/listCitizen/id={id}")
    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('CITIZEN')")
    public ResponseEntity<Citizen> getCitizenById(@PathVariable("id") long id){
        return citizenService.getCitizenById(id);
    }
}

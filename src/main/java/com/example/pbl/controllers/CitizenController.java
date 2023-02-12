package com.example.pbl.controllers;

import com.example.pbl.entity.Citizen;
import com.example.pbl.service.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/politician")
public class CitizenController {
    private final CitizenService citizenService;
    @Autowired
    public CitizenController(CitizenService citizenService) {
        this.citizenService = citizenService;
    }
    @GetMapping("/listCitizen/country")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Citizen>> getAllCitizen(){
        try {
            List<Citizen> citizenList=citizenService.getAllCitizen();
            return new ResponseEntity<>(citizenList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/listCitizen/city={name}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Citizen>> getCityCitizen(@PathVariable("name") String name){
        try {
            List<Citizen> citizenList=citizenService.getCityCitizen(name);
            return new ResponseEntity<>(citizenList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/listCitizen/town={name}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Citizen>> getTownCitizen(@PathVariable("name") String name){
        try {
            List<Citizen> citizenList=citizenService.getTownCitizen(name);
            return new ResponseEntity<>(citizenList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/listCitizen/district={name}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Citizen>> getDistrictCitizen(@PathVariable("name") String name){
        try {
            List<Citizen> citizenList=citizenService.getDistrictCitizen(name);
            return new ResponseEntity<>(citizenList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/listCitizen/quarter={name}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Citizen>> getQuarterCitizen(@PathVariable("name") String name){
        try {
            List<Citizen> citizenList=citizenService.getQuarterCitizen(name);
            return new ResponseEntity<>(citizenList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/listCitizen/id={id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Citizen> getCitizenById(@PathVariable("id") long id){
        return citizenService.getCitizenById(id);
    }
}

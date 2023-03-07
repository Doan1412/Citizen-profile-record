package com.example.pbl.controllers;

import com.example.pbl.DTO.RegisterRequest;
import com.example.pbl.DTO.RequestString;
import com.example.pbl.DTO.UpdateCitizen;
import com.example.pbl.entity.Citizen;
import com.example.pbl.repositories.CitizenRepository;
import com.example.pbl.service.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping(path="api/citizen")
public class CitizenController {
    private final CitizenService citizenService;
    private final CitizenRepository citizenRepository;

    @Autowired
    public CitizenController(CitizenService citizenService,
                             CitizenRepository citizenRepository) {
        this.citizenService = citizenService;
        this.citizenRepository = citizenRepository;
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
    @GetMapping("/listCitizen/city={nameCode}")
    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('POLITICIAN')")
    public ResponseEntity<List<Citizen>> getCityCitizen(@PathVariable String nameCode){
        try {
            String name= URLDecoder.decode(nameCode, StandardCharsets.UTF_8);
            List<Citizen> citizenList=citizenService.getCityCitizen(name);
            return new ResponseEntity<>(citizenList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/listCitizen/town={nameCode}")
    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('POLITICIAN')")
    public ResponseEntity<List<Citizen>> getTownCitizen(@PathVariable String nameCode){
        try {
            String name= URLDecoder.decode(nameCode, StandardCharsets.UTF_8);
            List<Citizen> citizenList=citizenService.getTownCitizen(name);
            return new ResponseEntity<>(citizenList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/listCitizen/district={nameCode}")
    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('POLITICIAN')")
    public ResponseEntity<List<Citizen>> getDistrictCitizen(@PathVariable String nameCode){
        try {
            String name= URLDecoder.decode(nameCode, StandardCharsets.UTF_8);
            List<Citizen> citizenList=citizenService.getDistrictCitizen(name);
            return new ResponseEntity<>(citizenList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/listCitizen/quarter={nameCode}")
    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('POLITICIAN')")
    public ResponseEntity<List<Citizen>> getQuarterCitizen(@PathVariable String nameCode){
        try {
            String name= URLDecoder.decode(nameCode, StandardCharsets.UTF_8);
            List<Citizen> citizenList=citizenService.getQuarterCitizen(name);
            return new ResponseEntity<>(citizenList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/listCitizen/id={id}")
    @PreAuthorize("hasAuthority('CITIZEN')")
    public ResponseEntity<Citizen> getCitizenById(@PathVariable("id") long id){
        return citizenService.getCitizenById(id);
    }
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('POLITICIAN')")
    public ResponseEntity<Citizen> updateCitizen(@RequestBody UpdateCitizen request){
        return citizenService.updateCitizen(request);
    }
    @DeleteMapping("/delete/id={id}")
    @PreAuthorize("hasAuthority('POLITICIAN') or hasAnyAuthority('ADMIN')")
    public ResponseEntity<Void> deleteCitizen(@PathVariable("id") long id){
        citizenService.deleteCitizen(id);
        return new ResponseEntity<>(null,HttpStatus.OK);
    }
}

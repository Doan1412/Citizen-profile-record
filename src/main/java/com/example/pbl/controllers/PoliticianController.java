package com.example.pbl.controllers;

import com.example.pbl.DTO.PoliList;
import com.example.pbl.entity.Politician;
import com.example.pbl.repositories.PoliticianRepository;
import com.example.pbl.service.PoliticianService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path="api/politician")
public class PoliticianController {
    private final PoliticianService politicianService;
    private final PoliticianRepository politicianRepository;

    @Autowired
    public PoliticianController(PoliticianService politicianService,
                                PoliticianRepository politicianRepository) {
        this.politicianService = politicianService;
        this.politicianRepository = politicianRepository;
    }
    @GetMapping("/listPolitician/country")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Politician>> getAllPolitician(){
        try {
            List<Politician> politicianList=politicianService.getAllPolitician();
            return new ResponseEntity<>(politicianList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/listPolitician")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<Politician>> getPoliticianByLevelManagerAndAndAreaManage(@RequestBody PoliList poliList){
        try {
            List<Politician> PoliticianList=politicianService.getPoliticianByLevelManagerAndAreaManage(poliList);
            return new ResponseEntity<>(PoliticianList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/id={id}")
    public ResponseEntity<Politician>getPoliticianById(@PathVariable long id){
        return politicianService.getPoliticianById(id);
    }
    @GetMapping("/citizenId={id}")
    public ResponseEntity<Politician>getPoliticianByCitizenId(@PathVariable long id){
        return politicianService.getPoliticianByCitizenId(id);
    }
}

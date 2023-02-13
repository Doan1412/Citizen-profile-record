package com.example.pbl.controllers;

import com.example.pbl.entity.Politician;
import com.example.pbl.service.PoliticianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Service
public class PoliticianController {
    private final PoliticianService politicianService;
    @Autowired
    public PoliticianController(PoliticianService politicianService) {
        this.politicianService = politicianService;
    }
    @GetMapping("/listPolitician/country")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Politician>> getAllCitizen(){
        try {
            List<Politician> politicianList=politicianService.getAllPolitician();
            return new ResponseEntity<>(politicianList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/listPolitician/")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Politician>> getPoliticianByLevelManagerAndAndAreaManage(@RequestParam(value="levelManage", required=true) String levelManage, @RequestParam(value="areaManage", required=true) String areaManage){
        try {
            areaManage=areaManage.replace('-',' ');
            levelManage=levelManage.replace('-',' ');
            List<Politician> PoliticianList=politicianService.getPoliticianByLevelManagerAndAreaManage(levelManage, areaManage);
            return new ResponseEntity<>(PoliticianList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

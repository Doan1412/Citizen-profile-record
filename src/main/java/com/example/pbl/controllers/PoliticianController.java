package com.example.pbl.controllers;

import com.example.pbl.DTO.PoliList;
import com.example.pbl.entity.Politician;
import com.example.pbl.repositories.PoliticianRepository;
import com.example.pbl.service.PoliticianService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
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
    @PreAuthorize("hasAuthority('POLITICIAN') or hasAnyAuthority('ADMIN')")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Politician>> getAllPolitician(){
        try {
            List<Politician> politicianList=politicianService.getAllPolitician();
            return new ResponseEntity<>(politicianList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/listPolitician/")
    @PreAuthorize("hasAuthority('POLITICIAN') or hasAnyAuthority('ADMIN') or hasAnyAuthority('CITIZEN')")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<Politician>> getPoliticianByLevelManagerAndAndAreaManage(@RequestParam String levelManageEncode,@RequestParam String areaManageEncode){
        try {
            String levelManage= URLDecoder.decode(levelManageEncode, StandardCharsets.UTF_8);
            String areaManage=URLDecoder.decode(areaManageEncode, StandardCharsets.UTF_8);
            List<Politician> PoliticianList=politicianService.getPoliticianByLevelManagerAndAreaManage(levelManage, areaManage);
            return new ResponseEntity<>(PoliticianList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/id={id}")
    @PreAuthorize("hasAuthority('POLITICIAN') or hasAnyAuthority('ADMIN') or hasAnyAuthority('CITIZEN')")
    public ResponseEntity<Politician>getPoliticianById(@PathVariable long id){
        return politicianService.getPoliticianById(id);
    }
    @GetMapping("/citizenId={id}")
    @PreAuthorize("hasAuthority('POLITICIAN') or hasAnyAuthority('ADMIN') or hasAnyAuthority('CITIZEN')")
    public ResponseEntity<Politician>getPoliticianByCitizenId(@PathVariable long id){
        return politicianService.getPoliticianByCitizenId(id);
    }
    @PutMapping("/update/politicianId={id}/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Politician>updatePolitician(@PathVariable long id,@RequestParam String levelManageEncode,@RequestParam String areaManageEncode,@RequestParam String positionEncode){
        try {
            String levelManage= URLDecoder.decode(levelManageEncode, StandardCharsets.UTF_8);
            String areaManage=URLDecoder.decode(areaManageEncode, StandardCharsets.UTF_8);
            String position=URLDecoder.decode(positionEncode, StandardCharsets.UTF_8);
            return politicianService.updatePolitician(id,levelManage,areaManage,position);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/politicianId={id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Politician>deletePolitician(@PathVariable long id){
        politicianService.deletePolitician(id);
        return new ResponseEntity<>(null,HttpStatus.OK);
    }
}

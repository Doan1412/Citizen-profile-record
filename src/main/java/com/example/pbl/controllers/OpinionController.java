package com.example.pbl.controllers;

import com.example.pbl.DTO.RequirementRequest;
import com.example.pbl.entity.Opinion;
import com.example.pbl.repositories.OpinionRepository;
import com.example.pbl.service.OpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/opinions")
public class OpinionController {
    @Autowired
    private OpinionService opinionService;
    @PostMapping("/new")
    @PreAuthorize("hasAuthority('CITIZEN')")
    public ResponseEntity<Opinion> createOpinion(@RequestBody RequirementRequest opinion) {
        return opinionService.createOpinion(opinion);
    }
    @GetMapping("/get")
    @PreAuthorize("hasAuthority('POLITICIAN')")
    public ResponseEntity<List<Opinion>> getAllOpinions() {
        return new ResponseEntity<>(opinionService.getOpinion(), HttpStatus.OK);
    }
    @GetMapping("/get/citizenId={id}")
    @PreAuthorize("hasAuthority('CITIZEN')")
    public ResponseEntity<List<Opinion>>getOpinionByCitizen(@PathVariable Long id){
        return new ResponseEntity<>(opinionService.getOpinionByCitizen(id), HttpStatus.OK);
    }
}

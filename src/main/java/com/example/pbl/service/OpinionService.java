package com.example.pbl.service;

import com.example.pbl.DTO.RequirementRequest;
import com.example.pbl.entity.Citizen;
import com.example.pbl.entity.Opinion;
import com.example.pbl.repositories.CitizenRepository;
import com.example.pbl.repositories.OpinionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpinionService {
    @Autowired
    private final OpinionRepository opinionRepository;
    private final CitizenRepository citizenRepository;

    @Autowired
    public OpinionService(OpinionRepository opinionRepository,
                          CitizenRepository citizenRepository) {
        this.opinionRepository = opinionRepository;
        this.citizenRepository = citizenRepository;
    }
    public ResponseEntity <Opinion> createOpinion(RequirementRequest request){
        Citizen citizen=citizenRepository.findById(request.getAuthor_id()).orElseThrow();
        Opinion opinion=new Opinion(citizen,request.getDescription());
        return new ResponseEntity<>(opinionRepository.save(opinion), HttpStatus.CREATED);
    }
    public List<Opinion> getOpinion(){
        return opinionRepository.findAll();
    }
}

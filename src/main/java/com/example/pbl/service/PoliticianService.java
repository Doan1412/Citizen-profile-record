package com.example.pbl.service;

import com.example.pbl.DTO.PoliList;
import com.example.pbl.entity.Citizen;
import com.example.pbl.entity.Politician;
import com.example.pbl.entity.Role;
import com.example.pbl.repositories.CitizenRepository;
import com.example.pbl.repositories.PoliticianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class PoliticianService {
    @Autowired
    private PoliticianRepository politicianRepository;
    @Autowired
    public PoliticianService(PoliticianRepository politicianRepository) {
        this.politicianRepository = politicianRepository;
    }
    public List<Politician> getAllPolitician(){
        return politicianRepository.findAll();
    }

    public ResponseEntity<Politician> getPoliticianById(Long id){
        Optional<Politician> politicianData= politicianRepository.findById(id);
        if(politicianData.isPresent()){
            return new ResponseEntity<>(politicianData.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
    public List<Politician> getPoliticianByLevelManagerAndAreaManage(String levelManage,String areaManage){
        System.out.println(levelManage+" "+areaManage);
        return politicianRepository.findByLevelManagerContainingIgnoreCaseAndAreaManageContainingIgnoreCase(levelManage,areaManage);
    }
    public ResponseEntity<Politician> getPoliticianByCitizenId(Long id){
        Optional<Politician> politicianData= politicianRepository.findByCitizenCitizenId(id);
        if(politicianData.isPresent()){
            return new ResponseEntity<>(politicianData.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity<Politician>updatePolitician(long id,String levelManage,String areaManage){
        Optional<Politician> politicianData= politicianRepository.findByPoliticianId(id);
        if(politicianData.isPresent()){
            System.out.println("politicianData.get()");
            politicianData.get().setLevelManager(levelManage);
            politicianData.get().setAreaManage(areaManage);
            return new ResponseEntity<>(politicianRepository.save(politicianData.get()), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    public void deletePolitician(long id) {
        Optional<Politician> politicianData= politicianRepository.findByPoliticianId(id);
        if(politicianData.isPresent()){
            politicianData.get().getCitizen().getRole().remove(Role.POLITICIAN);
            politicianRepository.deleteById(id);
        }
    }
}

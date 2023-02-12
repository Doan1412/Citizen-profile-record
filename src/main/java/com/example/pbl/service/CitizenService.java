package com.example.pbl.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.example.pbl.entity.Citizen;
import com.example.pbl.repositories.CitizenRepository;
import com.example.pbl.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitizenService {
    @Autowired
    private CitizenRepository citizenRepository;
    @Autowired
    public CitizenService(CitizenRepository citizenRepository) {
        this.citizenRepository = citizenRepository;
    }
    public List<Citizen> getAllCitizen(){
        return citizenRepository.findAll();
    }
    public ResponseEntity<Citizen> getCitizenById(Long id){
        Optional<Citizen> citizenData= citizenRepository.findById(id);
        if(citizenData.isPresent()){
            return new ResponseEntity<>(citizenData.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
    public List<Citizen> getCityCitizen(String name){
        return citizenRepository.findByLocationCity(name);
    }
    public List<Citizen> getTownCitizen(String name){
        return citizenRepository.findByLocationTown(name);
    }
    public List<Citizen> getQuarterCitizen(String name){
        return citizenRepository.findByLocationQuarter(name);
    }
    public List<Citizen> getDistrictCitizen(String name){
        return citizenRepository.findByLocationDistrict(name);
    }

    public Citizen addCitizen(Citizen movie){
        return citizenRepository.save(movie);
    }
    public void deleteCitizen(Long id){
        citizenRepository.deleteById(id);
    }
    public Citizen updateCitizen(Citizen citizen){
        citizenRepository.deleteById(citizen.getId());
        return citizenRepository.save(citizen);
    }

    // tao tk citizen moi
    public Citizen createUser(Citizen citizen) {
        citizen.setPassword(PasswordUtil.encode(citizen.getPassword()));
        return citizenRepository.save(citizen);
    }
}

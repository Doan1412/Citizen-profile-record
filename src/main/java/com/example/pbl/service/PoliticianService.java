package com.example.pbl.service;

import com.example.pbl.DTO.PoliList;
import com.example.pbl.entity.Citizen;
import com.example.pbl.entity.Politician;
import com.example.pbl.entity.Role;
import com.example.pbl.repositories.*;
import jakarta.transaction.Transactional;
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
    private final AppointmentRepository appointmentRepository;
    @Autowired
    private final RequirementRepository requirementRepository;
    @Autowired
    private final NotificationRepository notificationRepository;

    public PoliticianService(PoliticianRepository politicianRepository, AppointmentRepository appointmentRepository, RequirementRepository requirementRepository, NotificationRepository notificationRepository) {
        this.politicianRepository = politicianRepository;
        this.appointmentRepository = appointmentRepository;
        this.requirementRepository = requirementRepository;
        this.notificationRepository = notificationRepository;
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
    public ResponseEntity<Politician>updatePolitician(long id,String levelManage,String areaManage, String position){
        Optional<Politician> politicianData= politicianRepository.findByPoliticianId(id);
        if(politicianData.isPresent()){
            politicianData.get().setLevelManager(levelManage);
            politicianData.get().setAreaManage(areaManage);
            politicianData.get().setPosition(position);
            return new ResponseEntity<>(politicianRepository.save(politicianData.get()), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
    @Transactional
    public void deletePolitician(long id) {
        Optional<Politician> politicianData= politicianRepository.findByPoliticianId(id);
        if(politicianData.isPresent()){
            appointmentRepository.deleteByPoliticianPoliticianId(politicianData.get().getPolitician_id());
            requirementRepository.deleteByRecipientPoliticianId(politicianData.get().getPolitician_id());
            notificationRepository.deleteByPoliticianPoliticianId(politicianData.get().getPoliticianId());
            politicianData.get().getCitizen().getRole().remove(Role.POLITICIAN);
            politicianRepository.delete(politicianData.get());
        }
    }
}

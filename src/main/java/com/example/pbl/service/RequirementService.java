package com.example.pbl.service;

import com.example.pbl.DTO.RequirementRequest;
import com.example.pbl.entity.Citizen;
import com.example.pbl.entity.Politician;
import com.example.pbl.entity.Requirement;
import com.example.pbl.repositories.CitizenRepository;
import com.example.pbl.repositories.PoliticianRepository;
import com.example.pbl.repositories.RequirementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

@Service
public class RequirementService {
    @Autowired
    private final RequirementRepository requirementRepository;
    @Autowired
    private final CitizenRepository citizenRepository;
    @Autowired
    private final PoliticianRepository politicianRepository;
    @Autowired
    public RequirementService(RequirementRepository requirementRepository, CitizenRepository citizenRepository, PoliticianRepository politicianRepository) {
        this.requirementRepository = requirementRepository;
        this.citizenRepository = citizenRepository;
        this.politicianRepository = politicianRepository;
    }


    public List<Requirement> getByAuthorName(String name){
        return requirementRepository.findByAuthorName(name);
    }
//    public Requirement addRequirement(Requirement requirement){
//        return requirementRepository.save(requirement);
//    }
    public void deleteRequirement(Long id){
        requirementRepository.deleteById(id);
    }
    public List<Requirement> getByPoliticianId(Long id){
        return requirementRepository.findByRecipientPoliticianId(id);
    }
    public ResponseEntity<Requirement>addRequirement(RequirementRequest request){
        Requirement requirement=new Requirement();
        Optional<Citizen> citizenData= citizenRepository.findById(request.getAuthor_id());
        if(!citizenData.isPresent()){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        } else {
            requirement.setAuthor(citizenData.get());
        }
        ListIterator<Long>list=request.getRecipient_id().listIterator();
        while(list.hasNext()){
            Optional<Politician> politicianData= politicianRepository.findById(list.next());
            if(!politicianData.isPresent()){
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }
            else {
                requirement.addRecipient(politicianData.get());
            }
        }
        requirement.setDate(request.getDate());
        requirement.setDescription(request.getDescription());
        requirement.setStatus("Đang xử lý");
        requirementRepository.save(requirement);
        return new ResponseEntity<>(requirement,HttpStatus.OK);
    }
}

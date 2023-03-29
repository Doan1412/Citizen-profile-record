package com.example.pbl.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.example.pbl.DTO.RegisterRequest;
import com.example.pbl.DTO.UpdateCitizen;
import com.example.pbl.entity.Citizen;
import com.example.pbl.entity.Family;
import com.example.pbl.entity.Location;
import com.example.pbl.repositories.CitizenRepository;
import com.example.pbl.repositories.FamilyRepository;
import com.example.pbl.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CitizenService {
    @Autowired
    private final CitizenRepository citizenRepository;
    @Autowired
    private final FamilyRepository familyRepository;

    public CitizenService(CitizenRepository citizenRepository, FamilyRepository familyRepository) {
        this.citizenRepository = citizenRepository;
        this.familyRepository = familyRepository;
    }

    @Autowired

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
        return citizenRepository.findByLocationCityContainingIgnoreCase(name);
    }
    public List<Citizen> getTownCitizen(String name){
        return citizenRepository.findByLocationTownContainingIgnoreCase(name);
    }
    public List<Citizen> getQuarterCitizen(String name){
        return citizenRepository.findByLocationQuarterContainingIgnoreCase(name);
    }
    public List<Citizen> getDistrictCitizen(String name){
        return citizenRepository.findByLocationDistrictContainingIgnoreCase(name);
    }

    public Citizen addCitizen(Citizen citizen){
        return citizenRepository.save(citizen);
    }
    public void deleteCitizen(Long id){
        Citizen citizen = citizenRepository.findById(id).orElseThrow();
        Family family = citizen.getFamily();
        if (citizen.getFamily() != null) {
            family.getFamilyMemberList().remove(citizen);
            familyRepository.save(family);
        }
        citizen.setFamily(null);
        citizenRepository.deleteById(id);
    }
    public ResponseEntity<Citizen> updateCitizen(UpdateCitizen request){
        Optional<Citizen> citizenData= citizenRepository.findById(request.getCitizenId());
        if(citizenData.isPresent()) {
            Location location = new Location(request.getQuarter(), request.getTown(), request.getDistrict(), request.getCity());
            Optional<Family> familyData = familyRepository.findById(request.getIdFamily());
            Family family = new Family();
            if (familyData.isPresent()) {
                family = familyData.get();
            }

            var citizen = Citizen.builder()
                    .citizenId(request.getCitizenId())
                    .name(request.getName())
                    .role(citizenData.get().getRole())
                    .password(citizenData.get().getPassword())
                    .birth(request.getBirth())
                    .family(family)
                    .gender(request.isGender())
                    .ethnic(request.getEthnic())
                    .religion(request.getReligion())
                    .nationality(request.getNationality())
                    .address(request.getAddress())
                    .location(location)
                    .profession(request.getProfession())
                    .phone(request.getPhone())
                    .email(request.getEmail())
                    .married(request.isMarried())
                    .imgUrl(request.getImgUrl())
                    .criminalRecord(request.getCriminalRecord())
                    .militaryService(request.isMilitaryService())
                    .build();
            return new ResponseEntity<>(citizenRepository.save(citizen),HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    public List<Citizen> getListMilitaryService() {
        return citizenRepository.findByMilitaryServiceFalseAndBirthBetween(new Date(System.currentTimeMillis()-18*12*30*24*60*60*1000),new Date(System.currentTimeMillis()-26*12*30*24*60*60*1000));
    }
}

package com.example.pbl.authentication;

import com.example.pbl.Request.PoliticianRegisterRequest;
import com.example.pbl.Request.RegisterRequest;
import com.example.pbl.entity.*;
import com.example.pbl.repositories.CitizenRepository;
import com.example.pbl.repositories.FamilyRepository;
import com.example.pbl.repositories.PoliticianRepository;
import com.example.pbl.service.JwtService;
import com.example.pbl.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final CitizenRepository citizenRepository;
    private final FamilyRepository familyRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PoliticianRepository politicianRepository;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getCitizen_id(),
                        request.getPassword()
                )
        );
        var citizen = citizenRepository.findById(Long.valueOf(request.getCitizen_id()))
                .orElseThrow();
        var jwtToken = jwtService.generateToken(citizen);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse registerCitizen(RegisterRequest request) {
        Location location =new Location(request.getQuarter(), request.getTown(), request.getDistrict(), request.getCity());
        Optional<Family> familyData= familyRepository.findById(request.getIdFamily());
        Family family=new Family();
        if(familyData.isPresent()){
            family=familyData.get();
        } else {
//            familyRepository.save(family);
//            familyRepository.flush();
        }
        Set<Role> roleSet=new HashSet<>();
        roleSet.add(Role.CITIZEN);
        var citizen= Citizen.builder()
                .name(request.getName())
                .password(PasswordUtil.encode(request.getPassword()))
                .role(roleSet)
                //.birth(request.getBirth())
                //.family(familyRepository.findById(family.getId_Family()).get())
                .gender(request.isGender())
                .ethnic(request.getEthnic())
                .religion(request.getReligion())
                .nationality(request.getNationality())
                .address(request.getAddress())
                .location(location)
                .profession(request.getProfession())
                .phone(request.getPhone())
                .email(request.getEmail())
                .build();
        citizenRepository.save(citizen);
        family.addFamilyMenber(citizen);
        familyRepository.save(family);
        var jwtToken = jwtService.generateToken(citizen);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    public AuthenticationResponse registerPolitician(PoliticianRegisterRequest politicianRegisterRequest){
        Optional<Citizen> citizenData= citizenRepository.findById(politicianRegisterRequest.getCitizen_id());
        if(citizenData.isPresent()){
            Politician politician=new Politician(citizenData.get(),politicianRegisterRequest.getPosition(), politicianRegisterRequest.getAreaManage(), politicianRegisterRequest.getLevelManager());
            citizenData.get().getRole().add(Role.POLITICIAN);
            citizenRepository.save(citizenData.get());
            politicianRepository.save(politician);
            var jwtToken = jwtService.generateToken(citizenData.get());
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
        return AuthenticationResponse.builder().build();
    }
}

package com.example.pbl.authentication;

import com.example.pbl.DTO.ChangePassword;
import com.example.pbl.DTO.PoliticianRegisterRequest;
import com.example.pbl.DTO.RegisterRequest;
import com.example.pbl.entity.*;
import com.example.pbl.repositories.CitizenRepository;
import com.example.pbl.repositories.FamilyRepository;
import com.example.pbl.repositories.PoliticianRepository;
import com.example.pbl.repositories.TokenRepository;
import com.example.pbl.service.JwtService;
import com.example.pbl.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
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
    private final TokenRepository tokenRepository;

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
        revokeAllUserTokens(citizen);
        saveUserToken(citizen, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .role(citizen.getRole())
                .build();
    }

    public AuthenticationResponse registerCitizen(RegisterRequest request) {
        Location location =new Location(request.getQuarter(), request.getTown(), request.getDistrict(), request.getCity());
        Optional<Family> familyData= familyRepository.findById(request.getIdFamily());
        Family family=new Family();
        if(familyData.isPresent()){
            family=familyData.get();
        }
        Set<Role> roleSet=new HashSet<>();
        roleSet.add(Role.CITIZEN);
        var citizen= Citizen.builder()
                .name(request.getName())
                .password(PasswordUtil.encode(request.getPassword()))
                .role(roleSet)
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
                .homeOwnerRelationship(request.getHomeOwnerRelationship())
                .build();
        Citizen p=citizenRepository.save(citizen);
        familyRepository.save(family);
        var jwtToken = jwtService.generateToken(citizen);
        saveUserToken(p, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .role(citizen.getRole())
                .build();
    }
    public AuthenticationResponse registerPolitician(PoliticianRegisterRequest politicianRegisterRequest){
        Optional<Citizen> citizenData= citizenRepository.findById(politicianRegisterRequest.getCitizen_id());
        if(citizenData.isPresent()){
            //Politician politician=new Politician(citizenData.get(),politicianRegisterRequest.getPosition(), politicianRegisterRequest.getAreaManage(), politicianRegisterRequest.getLevelManager());
            citizenData.get().getRole().add(Role.POLITICIAN);
            citizenRepository.save(citizenData.get());
            var politician=Politician.builder()
                    .citizen(citizenData.get())
                    .levelManager(politicianRegisterRequest.getLevelManager())
                    .areaManage(politicianRegisterRequest.getAreaManage())
                    .position(politicianRegisterRequest.getPosition())
                    .build();
            politicianRepository.save(politician);
            var jwtToken = jwtService.generateToken(citizenData.get());
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .role(citizenData.get().getRole())
                    .build();
        }
        return AuthenticationResponse.builder().build();
    }

    public AuthenticationResponse changePassword(ChangePassword request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getCitizen_id(),
                        request.getOldPassword()
                )
        );
        var citizen = citizenRepository.findById(Long.valueOf(request.getCitizen_id()))
                .orElseThrow();
        citizen.setPassword(PasswordUtil.encode(request.getNewPassword()));
        citizenRepository.save(citizen);
        var jwtToken = jwtService.generateToken(citizen);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .role(citizen.getRole())
                .build();
    }
    private void saveUserToken(Citizen citizen, String jwtToken) {
        var token = Token.builder()
                .citizen(citizen)
                .token(jwtToken)
                .tokenType("BEARER")
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(Citizen citizen) {
        var validUserTokens = tokenRepository.findByRevokedFalseAndExpiredFalseAndCitizenId (citizen.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}

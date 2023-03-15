package com.example.pbl.authentication;

import com.example.pbl.DTO.*;
import com.example.pbl.entity.*;
import com.example.pbl.exception.TokenRefreshException;
import com.example.pbl.repositories.CitizenRepository;
import com.example.pbl.repositories.FamilyRepository;
import com.example.pbl.repositories.PoliticianRepository;
import com.example.pbl.service.JwtService;
import com.example.pbl.service.RefreshTokenService;
import com.example.pbl.util.PasswordUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final CitizenRepository citizenRepository;
    @Autowired
    private final FamilyRepository familyRepository;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final PoliticianRepository politicianRepository;
    @Autowired
    RefreshTokenService refreshTokenService;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getCitizen_id(),
                        request.getPassword()
                )
        );
        var citizen = citizenRepository.findById(Long.valueOf(request.getCitizen_id()))
                .orElseThrow();
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(Long.valueOf(request.getCitizen_id()));
        var jwtToken = jwtService.generateToken(citizen);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .role(citizen.getRole())
                .refreshToken(refreshToken.getToken())
                .expiryDuration(Long.valueOf(86400000))
                .build();
    }

    public Citizen registerCitizen(RegisterRequest request) {
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
                .build();
        citizenRepository.save(citizen);
        familyRepository.save(family);
        return citizen;
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
                    .accessToken(jwtToken)
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
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(Long.valueOf(request.getCitizen_id()));
        var jwtToken = jwtService.generateToken(citizen);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .role(citizen.getRole())
                .refreshToken(refreshToken.getToken())
                .expiryDuration(Long.valueOf(86400000))
                .build();
    }
    public ResponseEntity<?> refreshtoken(@Valid TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getCitizen)
                .map(user -> {
                    var jwtToken = jwtService.generateToken(citizenRepository.findById(request.getCitizenId()).get());
                    return ResponseEntity.ok(new TokenRefreshResponse(jwtToken, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }
    public ResponseEntity<?> logoutUser() {
        Citizen citizenDetails = (Citizen) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long citizen_id = citizenDetails.getId();
        refreshTokenService.deleteByUserId(citizen_id);
        return ResponseEntity.ok("Log out successful!");
    }
}

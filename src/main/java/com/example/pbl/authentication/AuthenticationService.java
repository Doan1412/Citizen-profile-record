package com.example.pbl.authentication;

import com.example.pbl.entity.Citizen;
import com.example.pbl.entity.Location;
import com.example.pbl.entity.Role;
import com.example.pbl.repositories.CitizenRepository;
import com.example.pbl.service.JwtService;
import com.example.pbl.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final CitizenRepository citizenRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse registerCitizen(RegisterRequest request) {
        Location location =new Location(request.getQuarter(), request.getTown(), request.getDistrict(), request.getCity());
        var citizen= Citizen.builder()
                .name(request.getName())
                .password(PasswordUtil.encode(request.getPassword()))
                .role(Role.CITIZEN)
                //.birth(request.getBirth())
                .idFamily(request.getIdFamily())
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
        var jwtToken = jwtService.generateToken(citizen);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

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

    public AuthenticationResponse register(RegisterRequest request) {
        Location location =new Location(request.getQuarter(), request.getTown(), request.getDistrict(), request.getCity());
        var citizen= Citizen.builder()
                .name(request.getName())
                .password(PasswordUtil.encode(request.getPassword()))
                .role(Role.CITIZEN)
                //.birth(request.getBirth())
                .idFamily(request.getIdFamily())
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
        var jwtToken = jwtService.generateToken(citizen);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}

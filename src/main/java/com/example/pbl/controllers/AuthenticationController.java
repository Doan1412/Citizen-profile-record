package com.example.pbl.controllers;

import com.example.pbl.DTO.PoliticianRegisterRequest;
import com.example.pbl.authentication.AuthenticationRequest;
import com.example.pbl.authentication.AuthenticationResponse;
import com.example.pbl.authentication.AuthenticationService;
import com.example.pbl.DTO.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.registerCitizen(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
    @PostMapping("/registerPolitician")
    public ResponseEntity<AuthenticationResponse>registerPolitician(
            @RequestBody PoliticianRegisterRequest request
    ) {
        return ResponseEntity.ok(service.registerPolitician(request));
    }

}

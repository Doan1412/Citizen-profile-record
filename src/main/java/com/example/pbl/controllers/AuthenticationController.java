package com.example.pbl.controllers;

import com.example.pbl.DTO.ChangePassword;
import com.example.pbl.DTO.PoliticianRegisterRequest;
import com.example.pbl.authentication.AuthenticationRequest;
import com.example.pbl.authentication.AuthenticationResponse;
import com.example.pbl.authentication.AuthenticationService;
import com.example.pbl.DTO.RegisterRequest;
import com.example.pbl.authentication.LogoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {

    private final AuthenticationService service;
    private final LogoutService logoutService;
    @PostMapping("/register")
    //@PreAuthorize("hasAuthority('POLITICIAN')")
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
    //@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<AuthenticationResponse>registerPolitician(
            @RequestBody PoliticianRegisterRequest request
    ) {
        return ResponseEntity.ok(service.registerPolitician(request));
    }
    @GetMapping("/user")
    public  ResponseEntity<Void>user(){
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('CITIZEN')")
    @PutMapping("/changePassword")
    public ResponseEntity<AuthenticationResponse>changePassword (
            @RequestBody ChangePassword request
    ){
        return ResponseEntity.ok(service.changePassword(request));
    }
//    @GetMapping("/logout")
//    public ResponseEntity<Void>logout(){
//        return ResponseEntity.ok(logoutService.logout());
//    }
}

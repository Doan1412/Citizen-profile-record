package com.example.pbl.service;

import com.example.pbl.repositories.CitizenRepository;
import com.example.pbl.repositories.PoliticianRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginService {
    @Autowired
    private CitizenRepository citizenRepository;
//    @Autowired
//    private PoliticianRepository politicianRepository;
//    public APIResponse login(String username, String password) {
//        Citizen citizen = citizenRepository.findByUsername(username);
//        if (citizen != null && BCrypt.checkpw(password, citizen.getPassword())) {
//            return citizen;
//        }
//        Officer officer = officerRepository.findByUsername(username);
//        if (officer != null && BCrypt.checkpw(password, officer.getPassword())) {
//            return officer;
//        }
//        return null;
//    }
}

package com.example.pbl.service;

import com.example.pbl.entity.Requirement;
import com.example.pbl.repositories.RequirementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class RequirementService {
    @Autowired
    private final RequirementRepository requirementRepository;
    @Autowired
    public RequirementService(RequirementRepository requirementRepository) {
        this.requirementRepository = requirementRepository;
    }
    public List<Requirement> getByAuthorName(String name){
        return requirementRepository.findByAuthorName(name);
    }

}

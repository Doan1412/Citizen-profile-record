package com.example.pbl.controllers;

import com.example.pbl.DTO.RequirementRequest;
import com.example.pbl.entity.Requirement;
import com.example.pbl.service.RequirementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/requirement")
@RequiredArgsConstructor
public class RequirementController {
    private final RequirementService requirementService;
    @PostMapping("/new")
    @PreAuthorize("hasAuthority('CITIZEN')")
    public ResponseEntity<Requirement> addRequirement(
            @RequestBody RequirementRequest request
            ) {
        return requirementService.addRequirement(request);
    }
}

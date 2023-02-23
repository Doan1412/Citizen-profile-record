package com.example.pbl.controllers;

import com.example.pbl.DTO.RequirementRequest;
import com.example.pbl.entity.Citizen;
import com.example.pbl.entity.Requirement;
import com.example.pbl.service.RequirementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PutMapping("/forwardRequest")
    @PreAuthorize("hasAuthority('POLITICIAN')")
    public ResponseEntity<Requirement>forwardRequest(@RequestParam(value="idReq", required=true) String requirementId, @RequestParam(value="idPoli", required=true) String nextPliticianId){
        return requirementService.forwardRequest(Long.valueOf(requirementId),Long.valueOf(nextPliticianId));
    }
//    @GetMapping("/politicianId={id}")
//    @PreAuthorize("hasAuthority('POLITICIAN')")
//    public ResponseEntity<List<Requirement>>getRequirementByPoliId(@PathVariable long id){
//        try {
//            List<Citizen> citizenList=citizenService.getQuarterCitizen(requestString.getString());
//            return new ResponseEntity<>(citizenList, HttpStatus.OK);
//        } catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
}

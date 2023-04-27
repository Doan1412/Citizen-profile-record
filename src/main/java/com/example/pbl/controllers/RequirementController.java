package com.example.pbl.controllers;

import com.example.pbl.DTO.RequestString;
import com.example.pbl.DTO.RequirementRequest;
import com.example.pbl.entity.Appointment;
import com.example.pbl.entity.Citizen;
import com.example.pbl.entity.Requirement;
import com.example.pbl.service.RequirementService;
import com.example.pbl.util.PasswordUtil;
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
    @PreAuthorize("hasAuthority('POLITICIAN') or hasAnyAuthority('ADMIN') or hasAuthority('CITIZEN') ")
    public ResponseEntity<Requirement> addRequirement(
            @RequestBody RequirementRequest request
            ) {
        System.out.println("test1");
        return requirementService.addRequirement(request);
    }
    @PutMapping("/forwardRequest")
    @PreAuthorize("hasAuthority('POLITICIAN')")
    public ResponseEntity<Requirement>forwardRequest(@RequestParam(value="idReq", required=true) String requirementId, @RequestParam(value="idPoli", required=true) String nextPliticianId){
        return requirementService.forwardRequest(Long.valueOf(requirementId),Long.valueOf(nextPliticianId));
    }
    @GetMapping("/politicianId={id}")
    @PreAuthorize("hasAuthority('POLITICIAN')")
    public ResponseEntity<List<Requirement>>getRequirementByPoliId(@PathVariable long id){
        try {
            List<Requirement> requirementList=requirementService.getByPoliticianId(id);
            return new ResponseEntity<>(requirementList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PatchMapping("/updateStatus/id={id}")
    @PreAuthorize("hasAuthority('POLITICIAN') or hasAnyAuthority('ADMIN') or hasAuthority('CITIZEN') ")
    public ResponseEntity<Requirement>updateStatus(@PathVariable long id, @RequestBody RequestString requestString){
        return requirementService.updateStatus(id,requestString.getString());
    }
    @GetMapping("/citizenId={id}")
    @PreAuthorize("hasAuthority('POLITICIAN') or hasAnyAuthority('ADMIN') or hasAuthority('CITIZEN') ")
    public ResponseEntity<List<Requirement>>getByCitizenId(@PathVariable long id){
        return new ResponseEntity<>(requirementService.getByCitizenId(id),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('POLITICIAN') or hasAnyAuthority('ADMIN') or hasAuthority('CITIZEN') ")
    public void deleteRequirement(@PathVariable long id){
        requirementService.deleteRequirement(id);
    }
}

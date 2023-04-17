package com.example.pbl.controllers;

import com.example.pbl.entity.Citizen;
import com.example.pbl.service.LocalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping(path="api/local")
public class LocalController {
    private final LocalService localService;

    public LocalController(LocalService localService) {
        this.localService = localService;
    }
    @GetMapping("/province")
    public ResponseEntity<List<String>> getProvince(){
        return new ResponseEntity<>(localService.getProvince(), HttpStatus.OK);
    }
    @GetMapping("/district/province={proCode}")
    public ResponseEntity<List<String>> getDistrict(@PathVariable String proCode){
        String pro= URLDecoder.decode(proCode, StandardCharsets.UTF_8);
        return new ResponseEntity<>(localService.getDistrict(pro), HttpStatus.OK);
    }
    @GetMapping("/ward/")
    public ResponseEntity<List<String>> getWard(@RequestParam String proCode, @RequestParam String disCode){
        String dis= URLDecoder.decode(disCode, StandardCharsets.UTF_8);
        String pro= URLDecoder.decode(proCode, StandardCharsets.UTF_8);
        return new ResponseEntity<>(localService.getWard(pro,dis), HttpStatus.OK);
    }
}

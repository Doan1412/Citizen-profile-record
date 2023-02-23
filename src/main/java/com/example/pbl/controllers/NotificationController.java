package com.example.pbl.controllers;

import com.example.pbl.DTO.RequirementRequest;
import com.example.pbl.entity.Notification;
import com.example.pbl.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/notification")
public class NotificationController {
    @Autowired
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
    @GetMapping("/citizenId={id}")
    public ResponseEntity<List<Notification>> getNotificationByCitizenId(@PathVariable long id){
        try {
            return new ResponseEntity<>(notificationService.getByCitizenId(id), HttpStatus.OK);
        } catch (Exception e)
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/newNotification")
    public ResponseEntity<Notification>addNotification(@RequestBody RequirementRequest request){
        return notificationService.addNotification(request);
    }
}

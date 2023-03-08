package com.example.pbl.service;

import com.example.pbl.DTO.RequirementRequest;
import com.example.pbl.entity.Citizen;
import com.example.pbl.entity.Notification;
import com.example.pbl.entity.Politician;
import com.example.pbl.entity.Requirement;
import com.example.pbl.repositories.CitizenRepository;
import com.example.pbl.repositories.NotificationRepository;
import com.example.pbl.repositories.PoliticianRepository;
import com.example.pbl.repositories.RequirementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

@Service
public class NotificationService {
    @Autowired
    private final NotificationRepository notificationRepository;
    @Autowired
    private final CitizenRepository citizenRepository;
    @Autowired
    private final PoliticianRepository politicianRepository;

    public NotificationService(NotificationRepository notificationRepository, CitizenRepository citizenRepository,
                               PoliticianRepository politicianRepository) {
        this.notificationRepository = notificationRepository;
        this.citizenRepository = citizenRepository;
        this.politicianRepository = politicianRepository;
    }

    public List<Notification> getByCitizenId(Long id){
        return notificationRepository.findByCitizensCitizenId(id);
    }
    public ResponseEntity<Notification>addNotification(RequirementRequest request){
        Notification notification=new Notification();
        Optional<Politician> politicianData= politicianRepository.findById(request.getAuthor_id());
        if(!politicianData.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            notification.setPolitician(politicianData.get());
        }
        ListIterator<Long> list=request.getRecipient_id().listIterator();
        while(list.hasNext()){
            Optional<Citizen> citizenData= citizenRepository.findById(list.next());
            if(!citizenData.isPresent()){
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }
            else {
                notification.addCitizens(citizenData.get());
            }
        }
        notification.setMessage(request.getDescription());
        return new ResponseEntity<>(notificationRepository.save(notification),HttpStatus.CREATED);
    }
    public List<Notification>getByPoliticianId(Long id){
        return notificationRepository.findByPoliticianPoliticianId(id);
    }
}

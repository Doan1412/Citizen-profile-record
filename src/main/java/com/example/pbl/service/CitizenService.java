package com.example.pbl.service;

import com.example.pbl.DTO.ReportForm;
import com.example.pbl.DTO.UpdateCitizen;
import com.example.pbl.entity.*;
import com.example.pbl.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CitizenService {
    @Autowired
    private final CitizenRepository citizenRepository;
    @Autowired
    private final FamilyRepository familyRepository;
    @Autowired
    private final AppointmentRepository appointmentRepository;
    @Autowired
    private final OpinionRepository opinionRepository;
    @Autowired
    private final RequirementRepository requirementRepository;
    @Autowired
    private final NotificationRepository notificationRepository;
    @Autowired
    private final TokenRepository tokenRepository;
    @Autowired
    private final PoliticianRepository politicianRepository;

    public CitizenService(CitizenRepository citizenRepository, FamilyRepository familyRepository, AppointmentRepository appointmentRepository, OpinionRepository opinionRepository, RequirementRepository requirementRepository,
                          NotificationRepository notificationRepository,TokenRepository tokenRepository,PoliticianRepository politicianRepository) {
        this.citizenRepository = citizenRepository;
        this.familyRepository = familyRepository;
        this.appointmentRepository = appointmentRepository;
        this.opinionRepository = opinionRepository;
        this.requirementRepository = requirementRepository;
        this.notificationRepository = notificationRepository;
        this.tokenRepository = tokenRepository;
        this.politicianRepository = politicianRepository;
    }

    @Autowired

    public List<Citizen> getAllCitizen(){
        return citizenRepository.findAll();
    }
    public ResponseEntity<Citizen> getCitizenById(Long id){
        Optional<Citizen> citizenData= citizenRepository.findById(id);
        if(citizenData.isPresent()){
            return new ResponseEntity<>(citizenData.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
    public List<Citizen> getCityCitizen(String name){
        return citizenRepository.findByLocationCityContainingIgnoreCase(name);
    }
    public List<Citizen> getTownCitizen(String name){
        return citizenRepository.findByLocationTownContainingIgnoreCase(name);
    }
    public List<Citizen> getQuarterCitizen(String name){
        return citizenRepository.findByLocationQuarterContainingIgnoreCase(name);
    }
    public List<Citizen> getDistrictCitizen(String name){
        return citizenRepository.findByLocationDistrictContainingIgnoreCase(name);
    }

    public Citizen addCitizen(Citizen citizen){
        return citizenRepository.save(citizen);
    }
    @Transactional
    public void deleteCitizen(Long id){
        Citizen citizen = citizenRepository.findById(id).orElseThrow();
        Family family = citizen.getFamily();
        if (citizen.getFamily() != null) {
            family.getFamilyMemberList().remove(citizen);
            familyRepository.save(family);
        }
        citizen.setFamily(null);
        appointmentRepository.deleteByCitizenCitizenId(id);
        opinionRepository.deleteByCitizenCitizenId(id);
        requirementRepository.deleteByAuthorCitizenId(id);
        tokenRepository.deleteByCitizenCitizenId(id);
        politicianRepository.deleteByCitizenCitizenId(id);
        List<Notification> list=notificationRepository.findByCitizensCitizenId(id);
        for (Notification noti:list
             ) {
            noti.getCitizens().remove(citizen);
            notificationRepository.save(noti);
        }
        citizenRepository.deleteByCitizenId(id);
    }
    public ResponseEntity<Citizen> updateCitizen(UpdateCitizen request){
        Optional<Citizen> citizenData= citizenRepository.findById(request.getCitizenId());
        if(citizenData.isPresent()) {
            Location location = new Location(request.getQuarter(), request.getTown(), request.getDistrict(), request.getCity());
            Optional<Family> familyData = familyRepository.findById(request.getIdFamily());
            Family family = new Family();
            if (familyData.isPresent()) {
                family = familyData.get();
            }

            var citizen = Citizen.builder()
                    .citizenId(request.getCitizenId())
                    .name(request.getName())
                    .role(citizenData.get().getRole())
                    .password(citizenData.get().getPassword())
                    .birth(request.getBirth())
                    .family(family)
                    .gender(request.isGender())
                    .ethnic(request.getEthnic())
                    .religion(request.getReligion())
                    .nationality(request.getNationality())
                    .address(request.getAddress())
                    .location(location)
                    .profession(request.getProfession())
                    .phone(request.getPhone())
                    .email(request.getEmail())
                    .married(request.isMarried())
                    .imgUrl(request.getImgUrl())
                    .criminalRecord(request.getCriminalRecord())
                    .militaryService(request.isMilitaryService())
                    .build();
            return new ResponseEntity<>(citizenRepository.save(citizen),HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    public ReportForm<Citizen> getListMilitaryService(long poliId) {
        Politician politician=politicianRepository.findByPoliticianId(poliId).orElseThrow();
        LocalDate now = LocalDate.now();
        LocalDate d1Local = now.minusYears(18);
        LocalDate d2Local = now.minusYears(26);

        Date d1 = Date.from(d1Local.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date d2 = Date.from(d2Local.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println(d1);
        if(politician.getLevelManager().equalsIgnoreCase("city")){
            return new ReportForm<>(citizenRepository.countByMilitaryServiceFalseAndBirthBetweenAndLocationCityContainingIgnoreCase(d2,d1,politician.getAreaManage()),citizenRepository.findByMilitaryServiceAndBirthBetweenAndLocationCityContainingIgnoreCase(false,d2,d1,politician.getAreaManage()));
        }
        else if (politician.getLevelManager().equalsIgnoreCase("district")){
            System.out.println(d1);
            return new ReportForm<>(citizenRepository.countByMilitaryServiceAndBirthBetweenAndLocationDistrictContainingIgnoreCase(false,d2,d1,politician.getAreaManage()),citizenRepository.findByMilitaryServiceAndBirthBetweenAndLocationDistrictContainingIgnoreCase(false,d2,d1,politician.getAreaManage()));
        }
        else if (politician.getLevelManager().equalsIgnoreCase("town")){
            return new ReportForm<>(citizenRepository.countByMilitaryServiceFalseAndBirthBetweenAndLocationTownContainingIgnoreCase(d2,d1,politician.getAreaManage()),citizenRepository.findByMilitaryServiceFalseAndBirthBetweenAndLocationTownContainingIgnoreCase(d2,d1,politician.getAreaManage()));
        }
        else if (politician.getLevelManager().equalsIgnoreCase("quarter")){
            return new ReportForm<>(citizenRepository.countByMilitaryServiceFalseAndBirthBetweenAndLocationQuarterContainingIgnoreCase(d2,d1,politician.getAreaManage()),citizenRepository.findByMilitaryServiceFalseAndBirthBetweenAndLocationQuarterContainingIgnoreCase(d2,d1,politician.getAreaManage()));
        }
        else if (politician.getLevelManager().equalsIgnoreCase("country")){
            return new ReportForm<>(citizenRepository.countByMilitaryServiceAndBirthBetween(false,d2,d1),citizenRepository.findByMilitaryServiceAndBirthBetween(false,d2,d1));
        }
        else {
            return new ReportForm<>(-1,null);
        }
    }

    public ReportForm<Citizen> getListMarried(long poliId,boolean f) {
        Politician politician=politicianRepository.findByPoliticianId(poliId).orElseThrow();
        if(politician.getLevelManager().equalsIgnoreCase("city")){
            return new ReportForm<>(citizenRepository.countByMarriedAndLocationCityContainingIgnoreCase(f, politician.getAreaManage()),citizenRepository.findByMarriedAndLocationCityContainingIgnoreCase(f, politician.getAreaManage()));
        }
        else if (politician.getLevelManager().equalsIgnoreCase("district")){
            return new ReportForm<>(citizenRepository.countByMarriedAndLocationDistrictContainingIgnoreCase(f, politician.getAreaManage()),citizenRepository.findByMarriedAndLocationDistrictContainingIgnoreCase(f, politician.getAreaManage()));
        }
        else if (politician.getLevelManager().equalsIgnoreCase("town")) {
            return new ReportForm<>(citizenRepository.countByMarriedAndLocationTownContainingIgnoreCase(f, politician.getAreaManage()), citizenRepository.findByMarriedAndLocationTownContainingIgnoreCase(f, politician.getAreaManage()));
        }
        else if (politician.getLevelManager().equalsIgnoreCase("quarter")){
            return new ReportForm<>(citizenRepository.countByMarriedAndLocationQuarterContainingIgnoreCase(f, politician.getAreaManage()), citizenRepository.findByMarriedAndLocationQuarterContainingIgnoreCase(f, politician.getAreaManage()));
        } else if (politician.getLevelManager().equalsIgnoreCase("country")) {
            return new ReportForm<>(citizenRepository.countByMarried(f), citizenRepository.findByMarried(f));
        } else {
            return new ReportForm<>(-1,null);
        }
    }

    public List<Long> countGender(long poliId) {
        Politician politician=politicianRepository.findByPoliticianId(poliId).orElseThrow();
        List<Long>list = new ArrayList<>();
        if(politician.getLevelManager().equalsIgnoreCase("city")) {
            list.add(citizenRepository.countByGenderAndLocationCityContainingIgnoreCase(true, politician.getAreaManage()));
            list.add(citizenRepository.countByGenderAndLocationCityContainingIgnoreCase(false, politician.getAreaManage()));
            list.add(list.get(0) + list.get(1));
        }
        else if (politician.getLevelManager().equalsIgnoreCase("district")){
            list.add(citizenRepository.countByGenderAndLocationDistrictContainingIgnoreCase(true, politician.getAreaManage()));
            list.add(citizenRepository.countByGenderAndLocationDistrictContainingIgnoreCase(false, politician.getAreaManage()));
            list.add(list.get(0) + list.get(1));
        }
        else if (politician.getLevelManager().equalsIgnoreCase("town")) {
            list.add(citizenRepository.countByGenderAndLocationTownContainingIgnoreCase(true, politician.getAreaManage()));
            list.add(citizenRepository.countByGenderAndLocationTownContainingIgnoreCase(false, politician.getAreaManage()));
            list.add(list.get(0) + list.get(1));
        }
        else if (politician.getLevelManager().equalsIgnoreCase("quarter")){
            list.add(citizenRepository.countByGenderAndLocationQuarterContainingIgnoreCase(true, politician.getAreaManage()));
            list.add(citizenRepository.countByGenderAndLocationQuarterContainingIgnoreCase(false, politician.getAreaManage()));
            list.add(list.get(0) + list.get(1));
        } else if (politician.getLevelManager().equalsIgnoreCase("country")) {
            list.add(citizenRepository.countByGender(true));
            list.add(citizenRepository.countByGender(false));
            list.add(list.get(0) + list.get(1));
        }
        return list;
    }

    public ReportForm<Citizen> getListCriminalRecord(long poliId) {
        Politician politician=politicianRepository.findByPoliticianId(poliId).orElseThrow();
        if(politician.getLevelManager().equalsIgnoreCase("city")){
            return new ReportForm<>(citizenRepository.countByCriminalRecordIsNotAndLocationCityContainingIgnoreCase("",politician.getAreaManage()),citizenRepository.findByCriminalRecordIsNotAndLocationCityContainingIgnoreCase("",politician.getAreaManage()));
        }
        else if (politician.getLevelManager().equalsIgnoreCase("district")){
            return new ReportForm<>(citizenRepository.countByCriminalRecordIsNotAndLocationDistrictContainingIgnoreCase("",politician.getAreaManage()),citizenRepository.findByCriminalRecordIsNotAndLocationDistrictContainingIgnoreCase("",politician.getAreaManage()));
        }
        else if (politician.getLevelManager().equalsIgnoreCase("town")) {
            return new ReportForm<>(citizenRepository.countByCriminalRecordIsNotAndLocationTownContainingIgnoreCase("",politician.getAreaManage()), citizenRepository.findByCriminalRecordIsNotAndLocationTownContainingIgnoreCase("",politician.getAreaManage()));
        }
        else if (politician.getLevelManager().equalsIgnoreCase("quarter")) {
            return new ReportForm<>(citizenRepository.countByCriminalRecordIsNotAndLocationQuarterContainingIgnoreCase("",politician.getAreaManage()), citizenRepository.findByCriminalRecordIsNotAndLocationQuarterContainingIgnoreCase("",politician.getAreaManage()));
        }
        else if (politician.getLevelManager().equalsIgnoreCase("country")) {
            return new ReportForm<>(citizenRepository.countByCriminalRecordIsNot(""),citizenRepository.findByCriminalRecordIsNot(""));
        } else {
            return new ReportForm<>(-1,null);
        }
    }
    public List<Long> getReportAge(long poliId){
        List<Long> list =new ArrayList<>();
        Politician politician=politicianRepository.findByPoliticianId(poliId).orElseThrow();

        LocalDate now = LocalDate.now();
        LocalDate d1Local = now.minusYears(14);
        LocalDate d2Local = now.minusYears(15);
        LocalDate d3Local = now.minusYears(59);
        LocalDate d4Local = now.minusYears(60);

        Date d1 = Date.from(d1Local.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date d2 = Date.from(d2Local.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date d3 = Date.from(d3Local.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date d4 = Date.from(d4Local.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dNow = Date.from(now.atStartOfDay(ZoneId.systemDefault()).toInstant());

        if(politician.getLevelManager().equalsIgnoreCase("city")){
            list.add(citizenRepository.countByBirthBetweenAndLocationCityContainingIgnoreCase(d1,dNow,politician.getAreaManage()));
            list.add(citizenRepository.countByBirthBetweenAndLocationCityContainingIgnoreCase(d3,d2,politician.getAreaManage()));;
            list.add(citizenRepository.countByBirthBeforeAndLocationCityContainingIgnoreCase(d4,politician.getAreaManage()));;
        }
        else if (politician.getLevelManager().equalsIgnoreCase("district")){
            list.add(citizenRepository.countByBirthBetweenAndLocationDistrictContainingIgnoreCase(d1,dNow,politician.getAreaManage()));
            list.add(citizenRepository.countByBirthBetweenAndLocationDistrictContainingIgnoreCase(d3,d2,politician.getAreaManage()));;
            list.add(citizenRepository.countByBirthBeforeAndLocationDistrictContainingIgnoreCase(d4,politician.getAreaManage()));;
        }
        else if (politician.getLevelManager().equalsIgnoreCase("town")) {
            list.add(citizenRepository.countByBirthBetweenAndLocationTownContainingIgnoreCase(d1,dNow,politician.getAreaManage()));
            list.add(citizenRepository.countByBirthBetweenAndLocationTownContainingIgnoreCase(d3,d2,politician.getAreaManage()));;
            list.add(citizenRepository.countByBirthBeforeAndLocationTownContainingIgnoreCase(d4,politician.getAreaManage()));;
        }
        else if (politician.getLevelManager().equalsIgnoreCase("quarter")) {
            list.add(citizenRepository.countByBirthBetweenAndLocationQuarterContainingIgnoreCase(d1,dNow,politician.getAreaManage()));
            list.add(citizenRepository.countByBirthBetweenAndLocationQuarterContainingIgnoreCase(d3,d2,politician.getAreaManage()));;
            list.add(citizenRepository.countByBirthBeforeAndLocationQuarterContainingIgnoreCase(d4,politician.getAreaManage()));;
        }
        else if (politician.getLevelManager().equalsIgnoreCase("country")){
            list.add(citizenRepository.countByBirthBetween(d1,dNow));
            list.add(citizenRepository.countByBirthBetween(d3,d2));
            list.add(citizenRepository.countByBirthBefore(d4));
        }

        return list;
    }
    public long countCitizen(long poliId){
        Politician politician=politicianRepository.findByPoliticianId(poliId).orElseThrow();
        if(politician.getLevelManager().equalsIgnoreCase("city")){
            return citizenRepository.countByLocationCity(politician.getAreaManage());
        }
        else if (politician.getLevelManager().equalsIgnoreCase("district")){
            return  citizenRepository.countByLocationDistrict(politician.getAreaManage());
        }
        else if (politician.getLevelManager().equalsIgnoreCase("town")) {
            return citizenRepository.countByLocationTown(politician.getAreaManage());
        }
        else if (politician.getLevelManager().equalsIgnoreCase("quarter")) {
            return citizenRepository.countByLocationQuarter(politician.getAreaManage());
        }
        else if (politician.getLevelManager().equalsIgnoreCase("country")){
            return citizenRepository.count();
        }
        else {
            return -1;
        }
    }
}

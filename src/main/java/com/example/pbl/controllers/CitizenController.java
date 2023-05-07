package com.example.pbl.controllers;

import com.example.pbl.DTO.ReportForm;
import com.example.pbl.DTO.UpdateCitizen;
import com.example.pbl.util.ExcelGeneratorUtility;
import com.example.pbl.util.PdfGenerator;
import com.example.pbl.entity.Citizen;
import com.example.pbl.repositories.CitizenRepository;
import com.example.pbl.service.CitizenService;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path="api/citizen")
public class CitizenController {
    private final CitizenService citizenService;
    private final CitizenRepository citizenRepository;

    @Autowired
    public CitizenController(CitizenService citizenService,
                             CitizenRepository citizenRepository) {
        this.citizenService = citizenService;
        this.citizenRepository = citizenRepository;
    }
    @GetMapping("/listCitizen/country")
    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('POLITICIAN') or hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<Citizen>> getAllCitizen(){
        try {
            List<Citizen> citizenList=citizenService.getAllCitizen();
            return new ResponseEntity<>(citizenList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/listCitizen/city={nameCode}")
    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('POLITICIAN')")
    public ResponseEntity<List<Citizen>> getCityCitizen(@PathVariable String nameCode){
        try {
            String name= URLDecoder.decode(nameCode, StandardCharsets.UTF_8);
            List<Citizen> citizenList=citizenService.getCityCitizen(name);
            return new ResponseEntity<>(citizenList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/listCitizen/town={nameCode}")
    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('POLITICIAN')")
    public ResponseEntity<List<Citizen>> getTownCitizen(@PathVariable String nameCode){
        try {
            String name= URLDecoder.decode(nameCode, StandardCharsets.UTF_8);
            List<Citizen> citizenList=citizenService.getTownCitizen(name);
            return new ResponseEntity<>(citizenList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/listCitizen/district={nameCode}")
    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('POLITICIAN')")
    public ResponseEntity<List<Citizen>> getDistrictCitizen(@PathVariable String nameCode){
        try {
            String name= URLDecoder.decode(nameCode, StandardCharsets.UTF_8);
            List<Citizen> citizenList=citizenService.getDistrictCitizen(name);
            return new ResponseEntity<>(citizenList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/listCitizen/quarter={nameCode}")
    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('POLITICIAN')")
    public ResponseEntity<List<Citizen>> getQuarterCitizen(@PathVariable String nameCode){
        try {
            String name= URLDecoder.decode(nameCode, StandardCharsets.UTF_8);
            List<Citizen> citizenList=citizenService.getQuarterCitizen(name);
            return new ResponseEntity<>(citizenList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/listCitizen/id={id}")
    @PreAuthorize("hasAuthority('POLITICIAN') or hasAnyAuthority('ADMIN') or hasAuthority('CITIZEN') ")
    public ResponseEntity<Citizen> getCitizenById(@PathVariable("id") long id){
        return citizenService.getCitizenById(id);
    }
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('POLITICIAN')")
    public ResponseEntity<Citizen> updateCitizen(@RequestBody UpdateCitizen request){
        return citizenService.updateCitizen(request);
    }
    @DeleteMapping("/delete/id={id}")
    @PreAuthorize("hasAuthority('POLITICIAN') or hasAnyAuthority('ADMIN')")
    public ResponseEntity<Void> deleteCitizen(@PathVariable("id") long id){
        citizenService.deleteCitizen(id);
        return new ResponseEntity<>(null,HttpStatus.OK);
    }
//    @GetMapping("/report/age={age}")
//    @PreAuthorize("hasAuthority('POLITICIAN)")
//    public ResponseEntity<> AgeReport(@PathVariable("id") long age,@RequestParam String area){
//
//    }
    @GetMapping("/report/militaryService/poliId={poliId}")
    @PreAuthorize("hasAuthority('POLITICIAN')")
    public ResponseEntity<ReportForm<Citizen>> getListMilitaryService(@PathVariable("poliId") long poliId){
        return new ResponseEntity<>(citizenService.getListMilitaryService(poliId),HttpStatus.OK);
    }
    @GetMapping("/report/married/poliId={poliId}/")
    @PreAuthorize("hasAuthority('POLITICIAN')")
    public ResponseEntity<ReportForm<Citizen>> getListMarried(@PathVariable("poliId") long poliId,@RequestParam boolean isMarried){
        return new ResponseEntity<>(citizenService.getListMarried(poliId,isMarried),HttpStatus.OK);
    }
    @GetMapping("/report/gender/poliId={poliId}")
    @PreAuthorize("hasAuthority('POLITICIAN')")
    public ResponseEntity<List<Long>> countGender(@PathVariable("poliId") long poliId){
        return new ResponseEntity<>(citizenService.countGender(poliId),HttpStatus.OK);
    }
    @GetMapping("/report/criminalRecord/poliId={poliId}")
    @PreAuthorize("hasAuthority('POLITICIAN')")
    public ResponseEntity<ReportForm<Citizen>> getListCriminalRecord(@PathVariable("poliId") long poliId){
        return new ResponseEntity<>(citizenService.getListCriminalRecord(poliId),HttpStatus.OK);
    }
    @GetMapping("/report/age/poliId={poliId}")
    @PreAuthorize("hasAuthority('POLITICIAN')")
    public ResponseEntity<List<Long>> getReportAge(@PathVariable("poliId") long poliId){
        return new ResponseEntity<>(citizenService.getReportAge(poliId),HttpStatus.OK);
    }
    @GetMapping("/report/countCitizen/poliId={poliId}")
    @PreAuthorize("hasAuthority('POLITICIAN')")
    public ResponseEntity<Long> countAll(@PathVariable("poliId") long poliId){
        return new ResponseEntity<>(citizenService.countCitizen(poliId),HttpStatus.OK);
    }
    @GetMapping("family/id={id}")
    @PreAuthorize("hasAuthority('POLITICIAN') or hasAnyAuthority('ADMIN') or hasAuthority('CITIZEN') ")
    public ResponseEntity<List<Citizen>> getFamily(@PathVariable("id") long id){
        return citizenService.getFamily(id);
    }
    @GetMapping("/report/count/poliId={poliId}")
    @PreAuthorize("hasAuthority('POLITICIAN')")
    public ResponseEntity<List<Long>> countReport(@PathVariable("poliId") long poliId){
        return new ResponseEntity<>(citizenService.countReport(poliId),HttpStatus.OK);
    }
    @GetMapping("admin/report")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Long>>adminReport(){
        return new ResponseEntity<>(citizenService.adminReport(),HttpStatus.OK);
    }
//    @GetMapping("/export-to-pdf/militaryService/poliId={id}")
//    @PreAuthorize("hasAuthority('POLITICIAN')")
//    public void generatePdfFile(HttpServletResponse response,@PathVariable("id") long id) throws DocumentException, IOException
//    {
//        response.setContentType("application/pdf");
//        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
//        String currentDateTime = dateFormat.format(new Date());
//        String headerkey = "Content-Disposition";
//        String headervalue = "attachment; filename=student" + currentDateTime + ".pdf";
//        response.setHeader(headerkey, headervalue);
//        List < Citizen > Citizens = citizenService.getListMilitaryService(id).getList();
//        PdfGenerator generator = new PdfGenerator();
//        generator.generate(Citizens, response);
//    }
    @GetMapping("/export-to-pdf/militaryService/poliId={id}")
    @PreAuthorize("hasAuthority('POLITICIAN')")
    public void generateMilitaryServicePdfFile(HttpServletResponse response,@PathVariable("id") long id) throws Exception {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=student" + currentDateTime + ".pdf";
        response.setHeader(headerkey, headervalue);
        List < Citizen > Citizens = citizenService.getListMilitaryService(id).getList();
        PdfGenerator generator = new PdfGenerator();
        generator.generate(Citizens, response);
    }
    @GetMapping("/export-to-pdf/citizen/poliId={id}")
    @PreAuthorize("hasAuthority('POLITICIAN')")
    @ResponseBody
    public void generateCitizenListPdfFile(HttpServletResponse response,@PathVariable("id") long id) throws Exception {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=student" + currentDateTime + ".pdf";
        response.setHeader(headerkey, headervalue);
        response.setCharacterEncoding("UTF-8");
        List < Citizen > citizens = citizenService.getCitizen(id);
        PdfGenerator generator = new PdfGenerator();
        generator.generate(citizens, response);
    }
    @GetMapping("/excel/militaryService/poliId={id}")
    @PreAuthorize("hasAuthority('POLITICIAN')")
    public void citizenMilitaryServiceReport(@PathVariable("id") long id,HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String fileType = "attachment; filename=citizen_Military_Service_Report" + dateFormat.format(new Date()) + ".xls";
        response.setHeader("Content-Disposition", fileType);
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM.getType());

        ExcelGeneratorUtility.citizenDetailReport(citizenService.getListMilitaryService(id).getList());
    }
    @GetMapping("/excel/citizen/poliId={id}")
    @PreAuthorize("hasAuthority('POLITICIAN')")
    @ResponseBody
    public void citizenDetailsReport(@PathVariable("id") long id, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String fileType = "attachment; filename=citizen_details_.xls";
        response.setHeader("Content-Disposition", fileType);
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM.getType());
        response.setCharacterEncoding("utf-8");
        ByteArrayInputStream stream = ExcelGeneratorUtility.citizenDetailReport(citizenService.getCitizen(id));
        IOUtils.copy(stream, response.getOutputStream());
        response.flushBuffer();
        stream.close();
    }
}

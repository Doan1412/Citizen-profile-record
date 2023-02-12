//package com.example.pbl.configuration;
//
//import com.example.pbl.entity.Citizen;
//import com.example.pbl.entity.Location;
//import com.example.pbl.entity.Politician;
//import com.example.pbl.entity.Role;
//import com.example.pbl.repositories.CitizenRepository;
//import com.example.pbl.repositories.PoliticianRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//
//@Configuration
//public class CitizenConfig {
//      @Bean
//      CommandLineRunner commandLineRunner(CitizenRepository citizenRepository){
//        return args -> {
//            Location location=new Location("Khu pho 1","","Hai Chau","Da Nang");
//            //Politician p=new Politician("Phan Thi Minh","1234",new Date(999999999),Long.valueOf(206399),false,"Kinh","Khong","Viet Nam","64 Tran Thu Do",location,"Nhan vien nha nuoc","","Truong phong giao duc","Dien Ban","town");
//            String arr []={"Trom cap", "Gay tai nan", "Giet nguoi"};
//            String arr1 []={"Danh bai" , "Gay thuong tich" , "Vi pham giao thong"};
//            ArrayList<String> crime=new ArrayList<String>(Arrays.asList(arr));
//            ArrayList<String> crime2=new ArrayList<String>(Arrays.asList(arr1));
//            Date birth1=new Date(10000000);
//           //Location location=new Location("Quang Lang","Dien Nam","Dien Ban","Quang Nam");
//            Citizen p1=new Citizen("Nguyen Van A","12345", Role.CITIZEN,birth1,Long.valueOf(123),false,"Kinh","Khong","Viet Nam","64 Tran Thu Do",location,"Sinh vien",crime,"abc@gmail.com","0935365789");
//            //Citizen p1= new Citizen("Nguyen Van A","12345",birth1,Long.valueOf(123),false,"Kinh","Khong","Viet Nam","64 Tran Thu Do",location,"Sinh vien","Trom cap");
//            citizenRepository.save(p1);
//        };
//    }
//}

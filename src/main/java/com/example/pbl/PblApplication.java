package com.example.pbl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Date;

@SpringBootApplication
public class PblApplication {

    public static void main(String[] args) {
        System.out.println(new Date(System.currentTimeMillis()-26L*12*30*24*60*60*1000));
        SpringApplication.run(PblApplication.class, args);
    }
}

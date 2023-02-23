package com.example.pbl.DTO;

import com.example.pbl.entity.Role;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCitizen {
    private Long citizenId;
    private String name;
    private Date birth;
    private Long idFamily;//id so ho khau
    private boolean gender; //gioi tinh
    private String ethnic; //Dan toc
    private String religion; //Ton giao
    private String nationality; //
    private String address; // dia chi
    private String profession; //Nghe nghiep
    private String criminalRecord; //tien an tien su
    private String email;
    private String phone;
    private boolean married;
    private String imgUrl;
    private String quarter;
    private String town;
    private String district;
    private String city;
}

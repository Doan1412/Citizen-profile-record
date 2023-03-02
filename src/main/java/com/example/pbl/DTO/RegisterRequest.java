package com.example.pbl.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String name;
    private String password;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="Indochina")
    private Date birth;
    private Long idFamily;
    private boolean gender;
    private String ethnic;
    private String religion;
    private String nationality;
    private String address;
    private String quarter;
    private String town;
    private String district;
    private String city;
    private String profession;
    private String criminalRecord;
    private String email;
    private String phone;
    private boolean married;
    private String imgUrl;
    private boolean militaryService;
}

package com.example.pbl.DTO;

import com.example.pbl.entity.Role;
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
    String name;
    String password;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="Indochina")
    Date birth;
    Long idFamily;
    boolean gender;
    String ethnic;
    String religion;
    String nationality;
    String address;
    String quarter;
    String town;
    String district;
    String city;
    String profession;
    List<String> criminalRecord;
    String email;
    String phone;
    boolean married;
}

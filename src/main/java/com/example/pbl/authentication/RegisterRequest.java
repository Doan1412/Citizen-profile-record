package com.example.pbl.authentication;

import com.example.pbl.entity.Role;
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
    //Date birth;
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
}

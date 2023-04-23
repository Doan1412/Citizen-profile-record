package com.example.pbl.entity;

import com.example.pbl.util.PasswordUtil;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@TableGenerator(name="citizen")
public class Citizen implements UserDetails {
    @Id
    @SequenceGenerator(
            name = "Sequence",
            sequenceName = "Sequence",
            allocationSize = 1,
            initialValue=10000000
    )
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Sequence")
    @Column(name="citizen_id")
    private Long citizenId;
    @NotNull
    private String name;
    @Column(nullable = false)
    @NotNull
    @JsonIgnore
    private String password;
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @NotNull
    private Set<Role> role=new HashSet<>();
    @JsonFormat(pattern="yyyy-MM-dd",timezone="Indochina")
    @NotNull
    private Date birth;
    @ManyToOne(cascade = CascadeType.ALL)
//    @JsonManagedReference
    @JoinColumn(name = "family_id",referencedColumnName = "id")
    private Family family;//id so ho khau
    @NotNull
    private boolean gender; //gioi tinh
    @NotNull
    private String ethnic; //Dan toc
    private String religion; //Ton giao
    @NotNull
    private String nationality; //
    @NotNull
    private String address; // dia chi
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(name = "location_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Location location;//Vi tri
    @NotNull
    private String profession; //Nghe nghiep
    private String criminalRecord; //tien an tien su
    private String email;
    @NotNull
    private String phone;
    @NotNull
    @JoinColumn(name = "maritalStatus")
    private boolean married;
    private String imgUrl;
    private boolean militaryService;
    private String homeOwnerRelationship;

    public Citizen(@NotNull String name, @NotNull String password, @NotNull Set<Role> role, @NotNull Date birth, Family family, @NotNull boolean gender, @NotNull String ethnic, String religion, @NotNull String nationality, @NotNull String address, @NotNull Location location, @NotNull String profession, String criminalRecord, String email, @NotNull String phone, @NotNull boolean married, String imgUrl, boolean militaryService, String homeOwnerRelationship) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.birth = birth;
        this.family = family;
        this.gender = gender;
        this.ethnic = ethnic;
        this.religion = religion;
        this.nationality = nationality;
        this.address = address;
        this.location = location;
        this.profession = profession;
        this.criminalRecord = criminalRecord;
        this.email = email;
        this.phone = phone;
        this.married = married;
        this.imgUrl = imgUrl;
        this.militaryService = militaryService;
        this.homeOwnerRelationship = homeOwnerRelationship;
    }

    public Long getId() {
        return citizenId;
    }

    public void setId(Long id) {
        this.citizenId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getEthnic() {
        return ethnic;
    }

    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Long getCitizen_id() {
        return citizenId;
    }

    public void setCitizen_id(Long citizen_id) {
        this.citizenId = citizen_id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public String getCriminalRecord() {
        return criminalRecord;
    }

    public void setCriminalRecord(String criminalRecord) {
        this.criminalRecord = criminalRecord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Long getFamily() {
        return family.getId_Family();
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        married = married;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Long getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(Long citizenId) {
        this.citizenId = citizenId;
    }

    public boolean isMilitaryService() {
        return militaryService;
    }

    public void setMilitaryService(boolean militaryService) {
        this.militaryService = militaryService;
    }

    public String getHomeOwnerRelationship() {
        return homeOwnerRelationship;
    }

    public void setHomeOwnerRelationship(String homeOwnerRelationship) {
        this.homeOwnerRelationship = homeOwnerRelationship;
    }

    @Override
    public String toString() {
        return "Citizen{" +
                "citizenId=" + citizenId +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", birth=" + birth +
                ", family=" + (family != null ? family.getId_Family() : null)+
                ", gender=" + gender +
                ", ethnic='" + ethnic + '\'' +
                ", religion='" + religion + '\'' +
                ", nationality='" + nationality + '\'' +
                ", address='" + address + '\'' +
                ", location=" + location +
                ", profession='" + profession + '\'' +
                ", criminalRecord='" + criminalRecord + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", married=" + married +
                ", imgUrl='" + imgUrl + '\'' +
//                ", militaryService=" + militaryService +
                ", homeOwnerRelationship='" + homeOwnerRelationship + '\'' +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities=new HashSet<>();
        for(var r:this.role){
            var sga=new SimpleGrantedAuthority(r.name());
            authorities.add(sga);
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return String.valueOf(citizenId) ;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

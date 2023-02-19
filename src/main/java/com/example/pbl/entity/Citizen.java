package com.example.pbl.entity;

import com.example.pbl.util.PasswordUtil;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
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
@TableGenerator(name="citizen", initialValue=1, allocationSize=1)
public class Citizen implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="citizen_id")
    private Long citizenId;
    private String name;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> role=new HashSet<>();
    private Date birth;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(name = "family_id",referencedColumnName = "family_id")
    private Family family;//id so ho khau
    private boolean gender; //gioi tinh
    private String ethnic; //Dan toc
    private String religion; //Ton giao
    private String nationality; //
    private String address; // dia chi

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(name = "location_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Location location;//Vi tri
    private String profession; //Nghe nghiep
    @ElementCollection
    private List<String> criminalRecord; //tien an tien su
    private String email;
    private String phone;
    @JoinColumn(name = "maritalStatus")
    private boolean married;
    private String imgUrl;

    public Citizen(String name, String password, Set<Role> role, Date birth, Family family, boolean gender, String ethnic, String religion, String nationality, String address, Location location, String profession, List<String> criminalRecord, String email, String phone,boolean isMarried,String imgUrl) {
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
        this.married=isMarried;
        this.imgUrl=imgUrl;
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

    public List<String> getCriminalRecord() {
        return criminalRecord;
    }

    public void setCriminalRecord(List<String> criminalRecord) {
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

    public Family getFamily() {
        return family;
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

    @Override
    public String toString() {
        return "Citizen{" +
                "citizen_id=" + citizenId +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", birth=" + birth +
                ", family=" + family +
                ", gender=" + gender +
                ", ethnic='" + ethnic + '\'' +
                ", religion='" + religion + '\'' +
                ", nationality='" + nationality + '\'' +
                ", address='" + address + '\'' +
                ", location=" + location +
                ", profession='" + profession + '\'' +
                ", criminalRecord=" + criminalRecord +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", married=" + married +
                ", imgUrl=" + imgUrl +
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

package com.example.pbl.entity;

import com.example.pbl.util.PasswordUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@TableGenerator(name="citizen", initialValue=1, allocationSize=1)
public class Citizen implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long citizen_id;
    private String name;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Date birth;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(name = "id_family")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
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

    public Long getId() {
        return citizen_id;
    }

    public void setId(Long id) {
        this.citizen_id = id;
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
        return citizen_id;
    }

    public void setCitizen_id(Long citizen_id) {
        this.citizen_id = citizen_id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
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

    @Override
    public String toString() {
        return "citizen{" +
                "id=" + citizen_id +
                ", name='" + name + '\'' +
                ", birth=" + birth +
                ", idFamily=" + family +
                ", Gender=" + gender +
                ", ethnic='" + ethnic + '\'' +
                ", religion='" + religion + '\'' +
                ", nationality='" + nationality + '\'' +
                ", address='" + address + '\'' +
                ", location=" + location +
                ", profession='" + profession + '\'' +
                ", criminalRecord='" + criminalRecord + '\'' +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return String.valueOf(citizen_id) ;
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

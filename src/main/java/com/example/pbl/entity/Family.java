package com.example.pbl.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Builder
@Entity
@TableGenerator(name="family", initialValue=1, allocationSize=1)
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "family",cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Citizen> familyMemberList;

    public Family(Set<Citizen> familyMemberList) {
        this.familyMemberList = familyMemberList;
    }

    public Family() {

    }

    public Family(Long id, Set<Citizen> familyMemberList) {
        this.id = id;
        this.familyMemberList = familyMemberList;
    }


    public Long getId_Family() {
        return id;
    }

    public void setId_Family(Long id_Family) {
        this.id = id_Family;
    }
    public Collection<Citizen> getFamilyMemberList() {
        return familyMemberList;
    }
    public void addFamilyMenber(Citizen citizen){
        if(this.familyMemberList!=null){
            this.familyMemberList.add(citizen);
        }
        else {
            Set<Citizen> list=new HashSet<>();
            list.add(citizen);
            this.familyMemberList=list;
        }
    }

    public void setFamilyMemberList(Set<Citizen> familyMemberList) {
        this.familyMemberList = familyMemberList;
    }

    @Override
    public String toString() {
        return "Family{" +
                "idFamily=" + id +
                ", familyMemberList=" + familyMemberList +
                '}';
    }
}

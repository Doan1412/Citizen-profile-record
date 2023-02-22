package com.example.pbl.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Builder
@Entity
@TableGenerator(name="family", initialValue=1, allocationSize=1)
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long family_id;
    @OneToMany(cascade = CascadeType.ALL)
    @JsonBackReference
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude
    private Collection<Citizen> familyMemberList;

    public Family(Collection<Citizen> familyMemberList) {
        this.familyMemberList = familyMemberList;
    }

    public Family() {

    }

    public Family(Long id, Collection<Citizen> familyMemberList) {
        this.family_id = id;
        this.familyMemberList = familyMemberList;
    }


    public Long getId_Family() {
        return family_id;
    }

    public void setId_Family(Long id_Family) {
        this.family_id = id_Family;
    }

    public Collection<Citizen> getFamilyMemberList() {
        return familyMemberList;
    }
    public void addFamilyMenber(Citizen citizen){
        if(this.familyMemberList!=null){
            this.familyMemberList.add(citizen);
        }
        else {
            List<Citizen> list=new ArrayList<>();
            list.add(citizen);
            this.familyMemberList=list;
        }
    }

    public void setFamilyMemberList(Collection<Citizen> familyMemberList) {
        this.familyMemberList = familyMemberList;
    }

    @Override
    public String toString() {
        return "Family{" +
                "idFamily=" + family_id +
                ", familyMemberList=" + familyMemberList +
                '}';
    }
}

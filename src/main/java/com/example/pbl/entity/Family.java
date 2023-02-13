package com.example.pbl.entity;

import jakarta.persistence.*;
import lombok.*;

import java.awt.*;
import java.util.Collection;
import java.util.List;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableGenerator(name="family", initialValue=1, allocationSize=1)
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToMany(mappedBy = "family",cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude
    private Collection<Citizen> familyMemberList;

    public Family(Collection<Citizen> familyMemberList) {
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

    public void setFamilyMemberList(Collection<Citizen> familyMemberList) {
        this.familyMemberList = familyMemberList;
    }

    @Override
    public String toString() {
        return "Family{" +
                "id_Family=" + id +
                ", familyMemberList=" + familyMemberList +
                '}';
    }
}

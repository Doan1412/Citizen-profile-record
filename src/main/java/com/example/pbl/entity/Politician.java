package com.example.pbl.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableGenerator(name="politician", initialValue=1, allocationSize=1)
public class Politician {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long politicianId;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = false)
    @JoinColumn(name = "citizen_id")
    private Citizen citizen;
    private String position;//chuc danh
    private String areaManage;
    private String levelManager;// cap


    public Long getPolitician_id() {
        return politicianId;
    }

    public void setPolitician_id(Long politician_id) {
        this.politicianId = politician_id;
    }

    public Citizen getCitizen() {
        return citizen;
    }

    public void setCitizen(Citizen citizen) {
        this.citizen = citizen;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAreaManage() {
        return areaManage;
    }

    public void setAreaManage(String areaManage) {
        this.areaManage = areaManage;
    }

    public String getLevelManager() {
        return levelManager;
    }

    public void setLevelManager(String levelManager) {
        this.levelManager = levelManager;
    }

    @Override
    public String toString() {
        return "Politician{" +
                "politicianId=" + politicianId +
                ", citizen=" + citizen +
                ", position='" + position + '\'' +
                ", areaManage='" + areaManage + '\'' +
                ", levelManager='" + levelManager + '\'' +
                '}';
    }
}

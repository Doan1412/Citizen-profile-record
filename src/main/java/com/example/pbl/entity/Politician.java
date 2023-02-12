package com.example.pbl.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@TableGenerator(name="politician", initialValue=1, allocationSize=1)
public class Politician {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long politician_id;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = false)
    @JoinColumn(name = "citizen_id")
    private Citizen citizen;
    private String position;//chuc danh
    private String areaManage;
    private String levelManager;// cap

    public Politician(){}

    public Politician(Citizen citizen, String position, String areaManage, String levelManager) {
        this.citizen = citizen;
        this.position = position;
        this.areaManage = areaManage;
        this.levelManager = levelManager;
    }

    public Long getPolitician_id() {
        return politician_id;
    }

    public void setPolitician_id(Long politician_id) {
        this.politician_id = politician_id;
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

}

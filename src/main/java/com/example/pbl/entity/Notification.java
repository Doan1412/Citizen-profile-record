package com.example.pbl.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    @OneToOne
    @JoinColumn(name = "sender_politician_id")
    private Politician politician;
    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "receivers_citizen_id")
    private List<Citizen> citizens;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Politician getPolitician() {
        return politician;
    }

    public void setPolitician(Politician politician) {
        this.politician = politician;
    }

    public List<Citizen> getCitizens() {
        return citizens;
    }

    public void setCitizens(List<Citizen> citizens) {
        this.citizens = citizens;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", politician=" + politician +
                ", citizens=" + citizens +
                '}';
    }

    public void addCitizens(Citizen citizen) {
        if(this.citizens!=null){
            this.citizens.add(citizen);
        }
        else {
            List<Citizen> list=new ArrayList<>();
            list.add(citizen);
            this.citizens=list;
        }
    }
}

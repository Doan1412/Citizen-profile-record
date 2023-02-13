package com.example.pbl.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@TableGenerator(name = "requirement",initialValue=1, allocationSize=1)
public class Requirement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_requirement;
    @ManyToOne
    @JoinColumn(name = "author_citizen_id")
    private Citizen author;
    @OneToOne
    @JoinColumn(name = "recipient_politician_id")
    private Politician recipient;
    @Column(nullable = false)
    private String description;
    private Date date;
    private String status;
    public Requirement(){

    }

    public Requirement(Citizen author, Politician recipient, String description, Date date, String status) {
        this.author = author;
        this.recipient = recipient;
        this.description = description;
        this.date = date;
        this.status = status;
    }

    public Long getId_requirement() {
        return id_requirement;
    }

    public void setId_requirement(Long id_requirement) {
        this.id_requirement = id_requirement;
    }

    public Citizen getAuthor() {
        return author;
    }

    public void setAuthor(Citizen author) {
        this.author = author;
    }

    public Politician getRecipient() {
        return recipient;
    }

    public void setRecipient(Politician recipient) {
        this.recipient = recipient;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Requirement{" +
                "id_requirement=" + id_requirement +
                ", author='" + author + '\'' +
                ", recipient='" + recipient + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                '}';
    }
}

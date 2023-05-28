package com.example.pbl.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
@TableGenerator(name = "requirement",initialValue=1, allocationSize=1)
public class Requirement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_requirement;
    @OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "author_citizen_id")
    private Citizen author;
    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
//    @JsonBackReference
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude
    @JoinColumn(name = "recipient_politician_id")
    private Collection<Politician> recipient;
    @Column(nullable = false)
    private String description;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date date;
    private String status;
    public Requirement(){

    }

    public Requirement(Citizen author, Collection<Politician> recipient, String description, Date date, String status) {
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

    public Collection<Politician> getRecipient() {

        return recipient;
    }

    public void setRecipient(List<Politician> recipient) {
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
    public void addRecipient(Politician politician){
        if(this.recipient!=null){
            this.recipient.add(politician);
        }
        else {
            List<Politician> list=new ArrayList<>();
            list.add(politician);
            this.recipient=list;
        }
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

package com.example.pbl.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(unique = true)
    public String token;

    public String tokenType = "BEARER";

    public boolean revoked;

    public boolean expired;

    @ManyToOne
    @JoinColumn(name = "citizen_id")
    public Citizen citizen;
}

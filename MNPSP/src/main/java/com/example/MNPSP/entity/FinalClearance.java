package com.example.MNPSP.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.Data;

@Entity
@Data
public class FinalClearance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String mobileNumber;

    @Column(columnDefinition = "boolean default false")
    private boolean deactivationClearance;

    @Column(columnDefinition = "boolean default false")
    private boolean activationClearance;
}

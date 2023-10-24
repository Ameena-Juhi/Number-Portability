package com.example.MNPSP.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class NumberPortabilityDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "porting_number", length = 10)
    private String portingNumber;

    @Column(columnDefinition = "boolean default false")
    private boolean pending;

    @Column(columnDefinition = "boolean default false")
    private boolean ported;

    private boolean clearance;
    private boolean IdentityClearance;

    private Date portedAt;
}

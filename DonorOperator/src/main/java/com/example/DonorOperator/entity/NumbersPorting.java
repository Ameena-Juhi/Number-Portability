package com.example.DonorOperator.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class NumbersPorting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mobileNumberId",referencedColumnName = "id")
    private MobileNumber mobileNumber;
    
    @Column(name = "upc", length = 8)
    private String upc;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date requestedUpcTime;
}

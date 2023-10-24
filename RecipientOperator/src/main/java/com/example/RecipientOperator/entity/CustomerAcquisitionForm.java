package com.example.RecipientOperator.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class CustomerAcquisitionForm {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "mobile_number", length = 10)
    private String mobileNumber;

    @Column(name = "upc", length = 8)
    private String UPC;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date requestedTime;

    @Column
    private String name;
    private String address;
    private boolean identityClearance;
    


    //Also DonorOperator.
}

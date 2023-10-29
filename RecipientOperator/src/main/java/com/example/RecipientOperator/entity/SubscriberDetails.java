package com.example.RecipientOperator.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class SubscriberDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    private String address;

    @OneToOne
    @JoinColumn(name = "mobileNum_id", referencedColumnName = "id")
    private MobileNumber mobileNumber;

    @Column
    private String IMSI;
    private String IMEI;
    private String ICCID;
    private boolean billing_clearance;
    private Date portedInDate;

}

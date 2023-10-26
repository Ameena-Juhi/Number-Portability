package com.example.DonorOperator.DTO;

import lombok.Data;

@Data
public class CAFtoken {

    private String mobileNumber;
    private String upc;
    private String name;
    private String address;
    private boolean clearance;
    private String token;

}

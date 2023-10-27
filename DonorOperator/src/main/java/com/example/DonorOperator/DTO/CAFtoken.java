package com.example.DonorOperator.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CAFtoken {

    private String mobileNumber;
    private String upc;
    private String name;
    private String address;
    private boolean clearance;
    private String token;

}

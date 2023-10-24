package com.example.DonorOperator.DTO;

import lombok.Data;

@Data
public class CAFdto {
    private String mobileNumber;
    private String upc;
    private String name;
    private String address;
    private boolean clearance;
}

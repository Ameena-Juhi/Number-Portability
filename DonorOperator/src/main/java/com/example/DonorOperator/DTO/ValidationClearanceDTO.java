package com.example.DonorOperator.DTO;

import lombok.Data;

@Data
public class ValidationClearanceDTO {
    private String mobileNumber;
    private boolean validationClearance;
}
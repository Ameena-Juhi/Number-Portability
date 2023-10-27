package com.example.DonorOperator.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationClearanceDTO {
    private String mobileNumber;
    private boolean validationClearance;
}
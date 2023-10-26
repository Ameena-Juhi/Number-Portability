package com.example.RecipientOperator.DTO;

import lombok.Data;

@Data
public class ValidationClearancetoken {

    private String mobileNumber;
    private boolean validationClearance;
    private String token;
}

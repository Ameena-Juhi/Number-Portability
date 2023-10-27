package com.example.DonorOperator.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationClearancetoken {

    private String mobileNumber;
    private boolean validationClearance;
    private String token;
}

package com.example.RecipientOperator.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ActivationRequestDTO {
    private String mobileNumber;
    private LocalDateTime activationTime;

}

package com.example.DonorOperator.DTO;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivationRequestDTO {
    private String mobileNumber;
    private LocalDateTime activationTime;

}

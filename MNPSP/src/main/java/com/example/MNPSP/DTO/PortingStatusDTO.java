package com.example.MNPSP.DTO;

import lombok.Data;

@Data
public class PortingStatusDTO {
    String mobileNumber;
    boolean pending;
    boolean ported;
}

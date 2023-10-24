package com.example.MNPSP.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.MNPSP.DTO.ActivationRequestDTO;
import com.example.MNPSP.DTO.MessageDTO;
import com.example.MNPSP.DTO.ValidationClearanceDTO;

@FeignClient(name = "RECIPIENTOPERATOR", url = "http://localhost:8082/request")
public interface ROActivationClient {

    @PostMapping("/checkidentity")
    boolean storeIdentityClearance(@RequestBody ValidationClearanceDTO IdentityClearanceDTO);
    
    @PostMapping("/activation")
    MessageDTO addSubscriber(@RequestBody ActivationRequestDTO activationRequest);
}

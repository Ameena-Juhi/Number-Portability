package com.example.MNPSP.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.MNPSP.DTO.ActivationRequestDTO;
import com.example.MNPSP.DTO.MessageDTO;
import com.example.MNPSP.DTO.ValidationClearanceDTO;

@FeignClient(name = "RecipientOperator", url = "http://localhost:8071/request")
public interface ROActivationClient {

    @PostMapping("/checkidentity")
    boolean storeIdentityClearance(@RequestHeader(value = "Authorization", required = true) String authorizationHeader,
            @RequestBody ValidationClearanceDTO IdentityClearanceDTO);

    @PostMapping("/activation")
    MessageDTO addSubscriber(@RequestBody ActivationRequestDTO activationRequest);
}

package com.example.RecipientOperator.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.RecipientOperator.DTO.CAFdto;
import com.example.RecipientOperator.DTO.MessageDTO;
import com.example.RecipientOperator.DTO.ValidationClearanceDTO;

@FeignClient(name = "MNPSP", url = "http://localhost:8071/mnpsp")
public interface CAFClient {

    @PostMapping("/sendcaf")
    MessageDTO sendCAFToMNPSP(@RequestBody CAFdto Form);

    @PostMapping("/activationClearance")
    void setActivationClearance(@RequestHeader(value = "Authorization", required = true) String authorizationHeader,
            @RequestBody ValidationClearanceDTO activationClearance);
}

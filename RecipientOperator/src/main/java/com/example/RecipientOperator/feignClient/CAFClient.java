package com.example.RecipientOperator.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.RecipientOperator.DTO.CAFdto;
import com.example.RecipientOperator.DTO.MessageDTO;
import com.example.RecipientOperator.DTO.ValidationClearanceDTO;

@FeignClient(name = "MNPSP", url = "http://localhost:8083/mnpsp")
public interface CAFClient {

    @PostMapping("/sendcaf")
    MessageDTO sendCAFToMNPSP(@RequestBody CAFdto Form);

    @PostMapping("/activationClearance")
    void setActivationClearance(@RequestBody ValidationClearanceDTO activationClearance);
}

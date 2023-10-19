package com.example.MNPSP.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.MNPSP.DTO.ActivationRequestDTO;
import com.example.MNPSP.DTO.CAFdto;
import com.example.MNPSP.DTO.MessageDTO;

@FeignClient(name = "DONOROPERATOR", url = "http://localhost:8081/operator")
public interface forwardcaf {

    @PostMapping("/forwardcaf")
    MessageDTO sendCAFToMNPSP(@RequestBody CAFdto Form);

    @PostMapping("/deactivation")
    public MessageDTO deactivateMobileNumber(@RequestBody ActivationRequestDTO deactivationRequest);
}

package com.example.MNPSP.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.MNPSP.DTO.ActivationRequestDTO;
import com.example.MNPSP.DTO.CAFdto;
import com.example.MNPSP.DTO.CAFtoken;
import com.example.MNPSP.DTO.MessageDTO;

@FeignClient(name = "DonorOperator", url = "http://localhost:8071/operator")
public interface forwardcaf {

    @PostMapping("/passedcaf")
    boolean sendCAFToDonor(@RequestHeader(value = "Authorization", required = true) String authorizationHeader,
            @RequestBody CAFtoken Form);

    @PostMapping("/deactivation")
    public MessageDTO deactivateMobileNumber(@RequestBody ActivationRequestDTO deactivationRequest);
}

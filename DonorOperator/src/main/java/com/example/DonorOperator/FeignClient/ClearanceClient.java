package com.example.DonorOperator.FeignClient;

import java.time.LocalDateTime;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.DonorOperator.DTO.ValidationClearanceDTO;
import com.example.DonorOperator.DTO.ValidationClearancetoken;

@FeignClient(name = "MNSP", url = "http://localhost:8071/mnpsp")
public interface ClearanceClient {

        @PostMapping("/storeClearance")
        void storeClearance(@RequestHeader(value = "Authorization", required = true) String authorizationHeader,
                        @RequestBody ValidationClearanceDTO validationClearance);

        @PostMapping("/storeIdentityClearance")
        boolean storeIdentityClearance(
                        @RequestHeader(value = "Authorization", required = true) String authorizationHeader,
                        @RequestBody ValidationClearanceDTO IdentityClearance);

        @PostMapping("/deactivationClearance")
        void setDeactivationClearance(
                        @RequestHeader(value = "Authorization", required = true) String authorizationHeader,
                        @RequestBody ValidationClearanceDTO deactivationClearance);
}

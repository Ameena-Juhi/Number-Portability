package com.example.DonorOperator.FeignClient;

import java.time.LocalDateTime;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.DonorOperator.DTO.ValidationClearanceDTO;

@FeignClient(name = "MNSP", url = "http://localhost:8083/mnpsp")
public interface ClearanceClient {

    @PostMapping("/storeClearance")
    void storeClearance(@RequestBody ValidationClearanceDTO validationClearance);
}

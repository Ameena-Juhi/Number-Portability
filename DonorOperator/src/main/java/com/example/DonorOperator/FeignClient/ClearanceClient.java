package com.example.DonorOperator.FeignClient;

import java.time.LocalDateTime;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "MNSP", url = "http://localhost:8083/mnpsp")
public interface ClearanceClient {

    @PostMapping("/scheduleport")
    LocalDateTime schedulePortTime(@RequestParam("validationClearance") boolean validationClearance);
}

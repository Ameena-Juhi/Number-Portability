package com.example.DonorOperator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DonorOperator.DTO.MessageDTO;
import com.example.DonorOperator.exception.ResourceNotFoundException;
import com.example.DonorOperator.service.CancelRequestService;
import com.example.DonorOperator.service.MobileNumberService;

@RestController
@RequestMapping("/subscriber")
public class SubscriberController {

    @Autowired
    private MobileNumberService mobileNumberService;

    @Autowired
    private CancelRequestService cancelRequestService;

    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    @PostMapping("/port")
    public MessageDTO portingSMS(@RequestBody MessageDTO sms) throws ResourceNotFoundException {
        return mobileNumberService.retrieveMobileNumber(sms);
    }

    @PostMapping("/cancel")
    public MessageDTO cancelPorting(@RequestBody MessageDTO sms) throws ResourceNotFoundException {
        return cancelRequestService.cancelRequest(sms);
    }

}

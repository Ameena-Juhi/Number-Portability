package com.example.DonorOperator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DonorOperator.exception.ResourceNotFoundException;
import com.example.DonorOperator.service.MobileNumberService;

@CrossOrigin
@RestController
@RequestMapping("/operator")
public class DonorController {

    @Autowired
    private MobileNumberService mobileNumberService;

    @PostMapping("/port")
    public String portingSMS(@RequestBody String sms) throws ResourceNotFoundException{
        return mobileNumberService.retrieveMobileNumber(sms);
    }



    
}

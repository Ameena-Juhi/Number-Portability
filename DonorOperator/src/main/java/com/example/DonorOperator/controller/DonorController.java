package com.example.DonorOperator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DonorOperator.exception.ResourceNotFoundException;
import com.example.DonorOperator.service.MobileNumberService;
import com.example.DonorOperator.service.PortingVerificationService;

@CrossOrigin
@RestController
@RequestMapping("/operator")
public class DonorController {

    @Autowired
    private MobileNumberService mobileNumberService;

    @Autowired
    private PortingVerificationService portingVerificationService;

    @PostMapping("/port")
    public String portingSMS(@RequestBody String sms) throws ResourceNotFoundException{
        return mobileNumberService.retrieveMobileNumber(sms);
    }

    @GetMapping("/request/{mobileNumber}")
    public boolean respondToPortingRequest(@PathVariable("mobileNumber") String mobileNumber,@RequestParam("upc") String upc) throws Exception{
        return (portingVerificationService.checkActivationPeriod(mobileNumber) && 
        portingVerificationService.checkOutstandingPayments(mobileNumber) &&
        portingVerificationService.checkUPCMismatch(mobileNumber,upc) &&
        portingVerificationService.checkUPCValidity(mobileNumber,upc) &&
        portingVerificationService.checkChangeOfOwnership(mobileNumber));
    }

    
}

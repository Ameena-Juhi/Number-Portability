package com.example.DonorOperator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DonorOperator.DTO.CAFdto;
import com.example.DonorOperator.FeignClient.ClearanceClient;
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

    @Autowired
    private ClearanceClient clearanceClient;

    @PostMapping("/port")
    public String portingSMS(@RequestBody String sms) throws ResourceNotFoundException {
        return mobileNumberService.retrieveMobileNumber(sms);
    }

    @PostMapping("/forwardcaf")
    public boolean respondToPortingRequest(@RequestBody CAFdto form) throws ResourceNotFoundException {

        boolean clearance = (portingVerificationService.checkActivationPeriod(form.getMobileNumber()) &&
                portingVerificationService.checkOutstandingPayments(form.getMobileNumber()) &&
                portingVerificationService.checkUPCMismatch(form) &&
                portingVerificationService.checkUPCValidity(form.getMobileNumber()) &&
                portingVerificationService.checkChangeOfOwnership(form.getMobileNumber()));

        // this.clearanceClient.schedulePortTime(clearance);

        return clearance;
    }

}

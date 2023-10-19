package com.example.DonorOperator.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DonorOperator.DTO.ActivationRequestDTO;
import com.example.DonorOperator.DTO.CAFdto;
import com.example.DonorOperator.DTO.MessageDTO;
import com.example.DonorOperator.FeignClient.ClearanceClient;
import com.example.DonorOperator.exception.ResourceNotFoundException;
import com.example.DonorOperator.service.DeactivationService;
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
    private DeactivationService deactivationService;

    @PostMapping("/port")
    public String portingSMS(@RequestBody String sms) throws ResourceNotFoundException {
        return mobileNumberService.retrieveMobileNumber(sms);
    }

    @PostMapping("/forwardcaf")
    public boolean respondToPortingRequest(@RequestBody CAFdto form) throws ResourceNotFoundException {
        List<String> errorMessages = new ArrayList<>();

        if (!portingVerificationService.checkActivationPeriod(form.getMobileNumber())) {
            errorMessages.add("Activation period check failed.");
        }

        if (!portingVerificationService.checkOutstandingPayments(form.getMobileNumber())) {
            errorMessages.add("Outstanding payments check failed.");
        }

        if (!portingVerificationService.checkUPCMismatch(form)) {
            errorMessages.add("UPC mismatch check failed.");
        }

        if (!portingVerificationService.checkUPCValidity(form.getMobileNumber())) {
            errorMessages.add("UPC validity check failed.");
        }

        if (!portingVerificationService.checkChangeOfOwnership(form.getMobileNumber())) {
            errorMessages.add("Change of ownership check failed.");
        }

        if (!errorMessages.isEmpty()) {
            System.out.println("Errors: " + errorMessages); // Print error messages
            return false;
        }

        // this.clearanceClient.schedulePortTime(true);

        return true;
    }

    // boolean clearance =
    // (portingVerificationService.checkActivationPeriod(form.getMobileNumber()) &&
    // portingVerificationService.checkOutstandingPayments(form.getMobileNumber())
    // &&
    // portingVerificationService.checkUPCMismatch(form) &&
    // portingVerificationService.checkUPCValidity(form.getMobileNumber()) &&
    // portingVerificationService.checkChangeOfOwnership(form.getMobileNumber()));

    // // this.clearanceClient.schedulePortTime(clearance);

    // return clearance;
    // }

    @PostMapping("/deactivation")
    public MessageDTO deactivateMobileNumber(@RequestBody ActivationRequestDTO deactivationRequest) {
        return deactivationService.acceptDeactivation(deactivationRequest);
    }

}

package com.example.DonorOperator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClearanceService {

    @Autowired
    private PortingVerificationService portingVerificationService;

    // public boolean sendClearance(){
    // boolean clearance =
    // (portingVerificationService.checkActivationPeriod(form.getMobileNumber()) &&
    // portingVerificationService.checkOutstandingPayments(form.getMobileNumber())
    // &&
    // portingVerificationService.checkUPCMismatch(form) &&
    // portingVerificationService.checkUPCValidity(form.getMobileNumber()) &&
    // portingVerificationService.checkChangeOfOwnership(form.getMobileNumber()));
    // }

}

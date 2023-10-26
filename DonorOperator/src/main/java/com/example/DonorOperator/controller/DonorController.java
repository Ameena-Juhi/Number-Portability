package com.example.DonorOperator.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DonorOperator.DTO.ActivationRequestDTO;
import com.example.DonorOperator.DTO.CAFdto;
import com.example.DonorOperator.DTO.MessageDTO;
import com.example.DonorOperator.DTO.SubDetailsDTO;
import com.example.DonorOperator.DTO.ValidationClearanceDTO;
import com.example.DonorOperator.FeignClient.ClearanceClient;
import com.example.DonorOperator.entity.DeactivationRequest;
import com.example.DonorOperator.entity.ForwardedRequests;
import com.example.DonorOperator.entity.MobileNumber;
import com.example.DonorOperator.exception.ResourceNotFoundException;
import com.example.DonorOperator.repository.MobileNumberRepository;
import com.example.DonorOperator.service.DeactivationService;
import com.example.DonorOperator.service.ForwardedReqService;
import com.example.DonorOperator.service.PortingVerificationService;
import com.example.DonorOperator.service.RejectionPortService;
import com.example.DonorOperator.service.SubscriberDetailsService;

@RestController
// @CrossOrigin(origins = { "http://localhost:50331/", "http://localhost:4200"
// })
@RequestMapping("/operator")
public class DonorController {

    @Autowired
    private MobileNumberRepository mobileNumberRepository;

    @Autowired
    private PortingVerificationService portingVerificationService;

    @Autowired
    private ForwardedReqService forwardedReqService;

    @Autowired
    private ClearanceClient clearanceClient;

    @Autowired
    private DeactivationService deactivationService;

    @Autowired
    private SubscriberDetailsService subscriberDetailsService;

    @Autowired
    private RejectionPortService rejectionPortService;

    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    @GetMapping("/get")
    public List<MobileNumber> getMobNums() {
        return mobileNumberRepository.findAll();
    }

    @PostMapping("/passedcaf")
    public boolean sendCAFToDonor(@RequestBody CAFdto form) {

        boolean identityVerification = forwardedReqService.saveCAFDTO(form);
        ValidationClearanceDTO clearancedto = new ValidationClearanceDTO();
        clearancedto.setMobileNumber(form.getMobileNumber());
        clearancedto.setValidationClearance(identityVerification);
        this.clearanceClient.storeIdentityClearance(clearancedto);
        return identityVerification;
    }

    @GetMapping("/getRequests")
    public List<ForwardedRequests> getAllRequestsTillNow() {
        return forwardedReqService.getAllRequests();
    }

    @PostMapping("/validate")
    public MessageDTO respondToPortingRequest(@RequestBody CAFdto form) throws ResourceNotFoundException {
        MessageDTO msg = new MessageDTO();
        boolean clearance;
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
            msg.setMessage("Response: " + errorMessages);
            clearance = false;
            return msg;
        }
        clearance = true;
        ValidationClearanceDTO clearancedto = new ValidationClearanceDTO();
        clearancedto.setMobileNumber(form.getMobileNumber());
        clearancedto.setValidationClearance(clearance);
        this.clearanceClient.storeClearance(clearancedto);
        msg.setMessage("Successfully validated!");
        return msg;
    }

    @GetMapping("/allDeactReqs")
    public List<DeactivationRequest> getAllDeactReqs() {
        return deactivationService.getAllDeactReqs();
    }

    @PostMapping("/saveRequest")
    public void saveDeactReq(@RequestBody ActivationRequestDTO activationRequestDTO) {
        deactivationService.saveDeactReqs(activationRequestDTO);
    }

    @PostMapping("/deactivation")
    public MessageDTO deactivateMobileNumber(@RequestBody ActivationRequestDTO deactivationRequest) {
        return deactivationService.acceptDeactivation(deactivationRequest);
    }

    @GetMapping("/getSubscribers")
    public List<SubDetailsDTO> getAllSubDetails() {
        return subscriberDetailsService.getAllDetails();
    }

    @PostMapping("/rejectDO")
    public MessageDTO cancelRequest(@RequestBody MessageDTO mobileNumber) {
        return rejectionPortService.processRejection(mobileNumber);
    }

}

package com.example.RecipientOperator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.RecipientOperator.DTO.ActivationRequestDTO;
import com.example.RecipientOperator.DTO.MessageDTO;
import com.example.RecipientOperator.DTO.SubDetailsDTO;
import com.example.RecipientOperator.DTO.ValidationClearanceDTO;
import com.example.RecipientOperator.entity.ActivationRequest;
import com.example.RecipientOperator.entity.CustomerAcquisitionForm;
import com.example.RecipientOperator.feignClient.CAFClient;
import com.example.RecipientOperator.service.ActivationService;
import com.example.RecipientOperator.service.CAFClientService;
import com.example.RecipientOperator.service.CancelRequestService;
import com.example.RecipientOperator.service.CustomerAcquisitionFormService;
import com.example.RecipientOperator.service.ResourceNotFoundException;
import com.example.RecipientOperator.service.SubscriberDetailsService;

// @CrossOrigin
@RestController
@RequestMapping("/request")
public class Port_InController {

    @Autowired
    private CustomerAcquisitionFormService CAFService;

    @Autowired
    private ActivationService activationService;

    @Autowired
    private SubscriberDetailsService subscriberDetailsService;

    @Autowired
    private CancelRequestService cancelRequestService;

    @Autowired
    private CAFClientService cafClientService;

    @GetMapping("/allrequests")
    public List<CustomerAcquisitionForm> getAllRequests() {
        return CAFService.getAllRequests();
    }

    @PostMapping("/portin")
    public void processCAF(@RequestBody CustomerAcquisitionForm form) {
        CAFService.saveCustomerForm(form);
    }

    @PostMapping("/saveRequest")
    public void saveActivationRequest(@RequestBody ActivationRequestDTO requestDTO) {
        activationService.saveActivationReq(requestDTO);
    }

    @GetMapping("/activationRequests")
    public List<ActivationRequest> getAllActivationRequest() {
        return activationService.getAllActivationRequests();
    }

    @PostMapping("/checkidentity")
    public boolean storeIdentityClearance(@RequestBody ValidationClearanceDTO IdentityClearanceDTO) {
        System.out.println("checkidentity");
        return this.cafClientService.storeIdentityClearance(IdentityClearanceDTO);
    }

    @PostMapping("/activation")
    public MessageDTO addSubscriber(@RequestBody ActivationRequestDTO activationRequest) {
        return activationService.acceptRequest(activationRequest);
    }

    @GetMapping("/getSubscribers")
    public List<SubDetailsDTO> getAllSubDetails() {
        return this.subscriberDetailsService.getAllDetails();
    }

    @PostMapping("/cancelRO")
    public MessageDTO cancellationRequest(@RequestBody MessageDTO mobileNumber) throws ResourceNotFoundException {
        return cancelRequestService.cancelRequest(mobileNumber);
    }

}

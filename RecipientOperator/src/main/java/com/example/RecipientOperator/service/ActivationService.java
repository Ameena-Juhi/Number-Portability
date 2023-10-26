package com.example.RecipientOperator.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RecipientOperator.DTO.ActivationRequestDTO;
import com.example.RecipientOperator.DTO.MessageDTO;
import com.example.RecipientOperator.DTO.ValidationClearanceDTO;
import com.example.RecipientOperator.entity.ActivationRequest;
import com.example.RecipientOperator.entity.CustomerAcquisitionForm;
import com.example.RecipientOperator.entity.MobileNumber;
import com.example.RecipientOperator.entity.SubscriberDetails;
import com.example.RecipientOperator.feignClient.CAFClient;
import com.example.RecipientOperator.repository.ActivationReqRepo;
import com.example.RecipientOperator.repository.CustomerAcquisitionFormRepository;
import com.example.RecipientOperator.repository.MobileNumberRepository;
import com.example.RecipientOperator.repository.SubDetailsRepository;

@Service
public class ActivationService {

    @Autowired
    private MobileNumberRepository mobileNumberRepository;

    @Autowired
    private CustomerAcquisitionFormRepository customerAcquisitionFormRepository;

    @Autowired
    private SubDetailsRepository subDetailsRepository;

    @Autowired
    private ActivationReqRepo activationReqRepo;

    @Autowired
    private CAFClient cafClient;

    public void saveActivationReq(ActivationRequestDTO request) {
        ActivationRequest newRequest = new ActivationRequest();
        newRequest.setMobileNumber(request.getMobileNumber());
        newRequest.setActivationTime(request.getActivationTime());
        activationReqRepo.save(newRequest);
    }

    public List<ActivationRequest> getAllActivationRequests() {
        return activationReqRepo.findAll();
    }

    public MessageDTO acceptRequest(String authorizationHeader, ActivationRequestDTO activationRequest) {
        MessageDTO messageDTO = new MessageDTO();
        LocalDateTime currentDateTime = LocalDateTime.now();

        LocalDateTime activationTime = activationRequest.getActivationTime();
        if (currentDateTime.isAfter(activationTime)) {
            Optional<MobileNumber> optionalMobileNumber = mobileNumberRepository
                    .findByMobileNumber(activationRequest.getMobileNumber());
            if (optionalMobileNumber.isPresent()) {
                messageDTO.setMessage("Already Added successfully!");
            } else {
                Optional<CustomerAcquisitionForm> optionalCustomerDetails = customerAcquisitionFormRepository
                        .findByMobileNumber(activationRequest.getMobileNumber());
                if (optionalCustomerDetails.isPresent()) {
                    CustomerAcquisitionForm customerDetails = optionalCustomerDetails.get();
                    MobileNumber mobileNumber = new MobileNumber();
                    mobileNumber.setMobileNumber(activationRequest.getMobileNumber());
                    mobileNumberRepository.save(mobileNumber);
                    SubscriberDetails subscriberDetails = new SubscriberDetails();
                    subscriberDetails.setMobileNumber(mobileNumber);
                    subscriberDetails.setPortedInDate(new Date());
                    subscriberDetails.setName(customerDetails.getName());
                    subscriberDetails.setAddress(customerDetails.getAddress());
                    subDetailsRepository.save(subscriberDetails);
                    ValidationClearanceDTO clearanceDto = new ValidationClearanceDTO();
                    clearanceDto.setMobileNumber(activationRequest.getMobileNumber());
                    clearanceDto.setValidationClearance(true);
                    this.cafClient.setActivationClearance(authorizationHeader, clearanceDto);
                    messageDTO.setMessage("Mobile number added successfully.");
                } else {
                    messageDTO.setMessage("Customer details not found!");
                }
            }
        } else {
            messageDTO.setMessage("Activation Time not yet reached!");
        }

        return messageDTO;
    }

}

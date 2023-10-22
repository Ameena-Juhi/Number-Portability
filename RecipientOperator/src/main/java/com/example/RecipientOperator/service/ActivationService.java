package com.example.RecipientOperator.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RecipientOperator.DTO.ActivationRequestDTO;
import com.example.RecipientOperator.DTO.MessageDTO;
import com.example.RecipientOperator.entity.ActivationRequest;
import com.example.RecipientOperator.entity.MobileNumber;
import com.example.RecipientOperator.entity.SubscriberDetails;
import com.example.RecipientOperator.repository.ActivationReqRepo;
import com.example.RecipientOperator.repository.MobileNumberRepository;
import com.example.RecipientOperator.repository.SubDetailsRepository;

@Service
public class ActivationService {

    @Autowired
    private MobileNumberRepository mobileNumberRepository;

    @Autowired
    private SubDetailsRepository subDetailsRepository;

    @Autowired
    private ActivationReqRepo activationReqRepo;

    public void saveActivationReq(ActivationRequestDTO request) {
        ActivationRequest newRequest = new ActivationRequest();
        newRequest.setMobileNumber(request.getMobileNumber());
        newRequest.setActivationTime(request.getActivationTime());
        activationReqRepo.save(newRequest);
    }

    public List<ActivationRequest> getAllActivationRequests(){
        return activationReqRepo.findAll();
    }

    public MessageDTO acceptRequest(ActivationRequestDTO activationRequest) {
        MessageDTO messageDTO = new MessageDTO();
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Check if the current date and time are the same as the activation time
        LocalDateTime activationTime = activationRequest.getActivationTime();
        if (currentDateTime.isAfter(activationTime)) {
            MobileNumber mobileNumber = new MobileNumber();
            mobileNumber.setMobileNumber(activationRequest.getMobileNumber());
            mobileNumberRepository.save(mobileNumber);
            SubscriberDetails subscriberDetails = new SubscriberDetails();
            subscriberDetails.setMobileNumber(mobileNumber);
            subDetailsRepository.save(subscriberDetails);
            messageDTO.setMessage("Mobile number added successfully."); // Set the appropriate success message
        } else {
            messageDTO.setMessage("Activation Time not yet reached!");
        }

        return messageDTO;

    }
}

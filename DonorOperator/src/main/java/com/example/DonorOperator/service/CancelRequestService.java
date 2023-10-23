package com.example.DonorOperator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DonorOperator.DTO.MessageDTO;
import com.example.DonorOperator.entity.ForwardedRequests;
import com.example.DonorOperator.entity.NumbersPorting;
import com.example.DonorOperator.repository.ForwardedRequestsRepo;
import com.example.DonorOperator.repository.MobileNumberRepository;
import com.example.DonorOperator.repository.NumbersPortingRepository;

@Service
public class CancelRequestService {

    @Autowired
    private NumbersPortingRepository numbersPortingRepository;

    @Autowired
    private ForwardedRequestsRepo forwardedRequestsRepo;

    @Autowired
    private MobileNumberRepository mobileNumberRepository;

    public MessageDTO processCancellation(MessageDTO mobileNumber){
        MessageDTO msg = new MessageDTO();
        String mobNum = mobileNumber.getMessage();
        ForwardedRequests request = forwardedRequestsRepo.findByMobileNumber(mobNum);
        forwardedRequestsRepo.delete(request);
        Long MobNumid = mobileNumberRepository.findByMobileNumber(mobNum).getId();
        NumbersPorting numbersPorting = numbersPortingRepository.findByMobileNumberId(MobNumid);
        numbersPortingRepository.delete(numbersPorting);
        msg.setMessage("Succesfully cancelled!");
        return msg;


    }
    
}

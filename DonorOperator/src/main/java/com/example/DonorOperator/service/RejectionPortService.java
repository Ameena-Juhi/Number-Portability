package com.example.DonorOperator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DonorOperator.DTO.MessageDTO;
import com.example.DonorOperator.entity.ForwardedRequests;
import com.example.DonorOperator.entity.MobileNumber;
import com.example.DonorOperator.entity.NumbersPorting;
import com.example.DonorOperator.repository.ForwardedRequestsRepo;
import com.example.DonorOperator.repository.MobileNumberRepository;
import com.example.DonorOperator.repository.NumbersPortingRepository;

@Service
public class RejectionPortService {

    @Autowired
    private NumbersPortingRepository numbersPortingRepository;

    @Autowired
    private ForwardedRequestsRepo forwardedRequestsRepo;

    @Autowired
    private MobileNumberRepository mobileNumberRepository;

    public MessageDTO processRejection(MessageDTO mobileNumber) {
        MessageDTO msg = new MessageDTO();
        String mobNum = mobileNumber.getMessage();
        ForwardedRequests request = forwardedRequestsRepo.findByMobileNumber(mobNum);
        if (request != null) {
            forwardedRequestsRepo.delete(request);
        } else {
            MobileNumber mobileNum = mobileNumberRepository.findByMobileNumber(mobNum).get();
            if (mobileNum != null) {
                Long MobNumid = mobileNum.getId();
                NumbersPorting numbersPorting = numbersPortingRepository.findByMobileNumberId(MobNumid);
                numbersPortingRepository.delete(numbersPorting);
                msg.setMessage("Succesfully cancelled at Donor Operator!");
                return msg;
            }
        }
        msg.setMessage("mobile Number doesnt exist");
        return msg;

    }

}

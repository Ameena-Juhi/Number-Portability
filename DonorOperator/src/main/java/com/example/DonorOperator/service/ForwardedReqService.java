package com.example.DonorOperator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DonorOperator.DTO.CAFdto;
import com.example.DonorOperator.DTO.MessageDTO;
import com.example.DonorOperator.entity.ForwardedRequests;
import com.example.DonorOperator.entity.MobileNumber;
import com.example.DonorOperator.entity.SubscriberDetails;
import com.example.DonorOperator.repository.ForwardedRequestsRepo;
import com.example.DonorOperator.repository.MobileNumberRepository;
import com.example.DonorOperator.repository.SubscriberDetailsRepository;

@Service
public class ForwardedReqService {

    @Autowired
    private ForwardedRequestsRepo forwardedRequestsRepo;

    @Autowired
    private MobileNumberRepository mobileNumberRepository;

    @Autowired
    private SubscriberDetailsRepository subscriberDetailsRepository;

    public boolean saveCAFDTO(CAFdto form) {
        System.out.println("savecafdto");
        String mobNum = form.getMobileNumber();
        boolean identityVerification = this.checkIdentity(form);
        if (identityVerification) {

            ForwardedRequests request = new ForwardedRequests();
            request.setMobileNumber(mobNum);
            request.setUpc(form.getUpc());
            request.setName(form.getName());
            request.setAddress(form.getAddress());
            forwardedRequestsRepo.save(request);
            return true;
        }

        return false;
    }

    public List<ForwardedRequests> getAllRequests() {
        return forwardedRequestsRepo.findAll();
    }

    private MessageDTO createResponse(String message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setMessage(message);
        return messageDTO;
    }

    public boolean checkIdentity(CAFdto form) {

        String mobNum = form.getMobileNumber();
        String name = form.getName();
        String address = form.getAddress();
        MobileNumber mobileNum = mobileNumberRepository.findByMobileNumber(mobNum).get();
        if (mobNum != null) {
            SubscriberDetails subscriber = subscriberDetailsRepository.findbymobilenum_id(mobileNum
                    .getId());
            // can it be case insensitive => no because we need exact details as per adhaar,
            // a small typo can also cause rejection
            if ((subscriber.getName()).equals(name) && (subscriber.getAddress()).equals(address)) {
                return true;
            }
            return false;

        }
        return false;
    }
}

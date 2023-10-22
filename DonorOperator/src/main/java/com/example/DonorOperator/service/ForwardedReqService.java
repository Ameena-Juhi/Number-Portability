package com.example.DonorOperator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DonorOperator.DTO.CAFdto;
import com.example.DonorOperator.entity.ForwardedRequests;
import com.example.DonorOperator.repository.ForwardedRequestsRepo;

@Service
public class ForwardedReqService {

    @Autowired
    private ForwardedRequestsRepo forwardedRequestsRepo;

    public void saveCAFDTO(CAFdto form) {

        String mobNum = form.getMobileNumber();
        if (forwardedRequestsRepo.findByMobileNumber(mobNum) != null) {
            return;
        }
        ForwardedRequests request = new ForwardedRequests();
        request.setMobileNumber(mobNum);
        request.setUpc(form.getUpc());
        forwardedRequestsRepo.save(request);

    }

    public List<ForwardedRequests> getAllRequests() {
        return forwardedRequestsRepo.findAll();
    }
}

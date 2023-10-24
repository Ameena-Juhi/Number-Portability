package com.example.MNPSP.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MNPSP.DTO.MessageDTO;
import com.example.MNPSP.repository.NumberPortabilityDBRepository;

@Service
public class CancelRequestService {

    @Autowired
    private NumberPortabilityDBRepository numberPortabilityDBRepository;

    public MessageDTO processCancellation(MessageDTO mobileNumber) {
        MessageDTO msg = new MessageDTO();
        String mobNum = mobileNumber.getMessage();
        numberPortabilityDBRepository.deleteById(
                (numberPortabilityDBRepository.findByPortingNumber(mobNum).get()).getId());
        msg.setMessage("Succesfully cancelled at MNPSP!");
        return msg;
    }

}

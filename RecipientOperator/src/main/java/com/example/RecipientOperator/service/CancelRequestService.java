package com.example.RecipientOperator.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RecipientOperator.DTO.MessageDTO;
import com.example.RecipientOperator.entity.CustomerAcquisitionForm;
import com.example.RecipientOperator.entity.MobileNumber;
import com.example.RecipientOperator.repository.CustomerAcquisitionFormRepository;
import com.example.RecipientOperator.repository.MobileNumberRepository;

@Service
public class CancelRequestService {

    @Autowired
    private CustomerAcquisitionFormRepository customerAcquisitionFormRepository;

    @Autowired
    private MobileNumberRepository mobileNumberRepository;

    public MessageDTO cancelRequest(MessageDTO mobileNum) throws ResourceNotFoundException {
        MessageDTO msg = new MessageDTO();
        String mobileNumber = mobileNum.getMessage();
        CustomerAcquisitionForm form = customerAcquisitionFormRepository.findByMobileNumber(mobileNumber).get();
        if (form == null) {
            throw new ResourceNotFoundException("Resource Not Found Exception " +
                    mobileNumber);
        } else {
            customerAcquisitionFormRepository.deleteById(
                    customerAcquisitionFormRepository.findByMobileNumber(mobileNumber).get().getId());
            msg.setMessage("Succesfully cancelled at Recipient Operator!");
            return msg;
        }
    }

}
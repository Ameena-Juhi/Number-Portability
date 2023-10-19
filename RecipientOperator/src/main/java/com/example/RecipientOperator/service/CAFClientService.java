package com.example.RecipientOperator.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RecipientOperator.DTO.CAFdto;
import com.example.RecipientOperator.DTO.MessageDTO;
import com.example.RecipientOperator.entity.CustomerAcquisitionForm;
import com.example.RecipientOperator.feignClient.CAFClient;
import com.example.RecipientOperator.repository.CustomerAcquisitionFormRepository;

@Service
public class CAFClientService {

    @Autowired
    private CAFClient cAFClient;

    @Autowired
    private CustomerAcquisitionFormRepository formRepository;

    public MessageDTO sendcaFdto(String mobileNumber) {
        Optional<CustomerAcquisitionForm> Form = formRepository.findByMobileNumber(mobileNumber);
        if (Form != null) {
            CAFdto caf = new CAFdto();
            caf.setMobileNumber(Form.get().getMobileNumber());
            caf.setUpc(Form.get().getUPC());
            return cAFClient.sendCAFToMNPSP(caf);
        } else {
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setMessage("No form found.");
            return messageDTO;
        }

    }
}

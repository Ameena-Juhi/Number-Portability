package com.example.RecipientOperator.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RecipientOperator.DTO.MessageDTO;
import com.example.RecipientOperator.entity.CustomerAcquisitionForm;
import com.example.RecipientOperator.repository.CustomerAcquisitionFormRepository;

@Service
public class CustomerAcquisitionFormService {

    @Autowired
    private CustomerAcquisitionFormRepository CAFRepository;

    public void saveCustomerForm(CustomerAcquisitionForm form) {
        form.isIdentityClearance();
        form.setRequestedTime(new Date());
        CAFRepository.save(form);
    }

    public List<CustomerAcquisitionForm> getAllRequests() {
        return CAFRepository.findAll();
    }

    public boolean getIdentityClearance(MessageDTO mobNum) {
        Optional<CustomerAcquisitionForm> formOptional = CAFRepository.findByMobileNumber(mobNum.getMessage());
        if (formOptional.isPresent()) {
            CustomerAcquisitionForm form = formOptional.get();
            return form.isIdentityClearance();
        } else {
            return false;
        }

    }

}

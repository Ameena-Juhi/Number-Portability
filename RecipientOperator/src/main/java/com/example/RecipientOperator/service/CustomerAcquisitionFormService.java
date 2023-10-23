package com.example.RecipientOperator.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RecipientOperator.entity.CustomerAcquisitionForm;
import com.example.RecipientOperator.repository.CustomerAcquisitionFormRepository;

@Service
public class CustomerAcquisitionFormService {
    
    @Autowired
    private CustomerAcquisitionFormRepository CAFRepository;

    public void saveCustomerForm(CustomerAcquisitionForm form) {
        
        form.setRequestedTime(new Date());
        CAFRepository.save(form);
    }

    public List<CustomerAcquisitionForm> getAllRequests(){
       return CAFRepository.findAll();
    }


}

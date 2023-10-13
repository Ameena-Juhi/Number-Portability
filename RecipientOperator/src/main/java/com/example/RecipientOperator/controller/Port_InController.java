package com.example.RecipientOperator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.RecipientOperator.entity.CustomerAcquisitionForm;
import com.example.RecipientOperator.service.CustomerAcquisitionFormService;



@RestController
@RequestMapping("/request")
public class Port_InController {
    
    @Autowired
    private CustomerAcquisitionFormService CAFService;

    @PostMapping("/portin")
    public void processCAF(@RequestBody CustomerAcquisitionForm form ){
        CAFService.saveCustomerForm(form);
    }
}

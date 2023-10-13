package com.example.RecipientOperator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.RecipientOperator.entity.CustomerAcquisitionForm;
import com.example.RecipientOperator.repository.CustomerAcquisitionFormRepository;
import com.example.RecipientOperator.service.NumberPortabilityDBService;

@RestController
@RequestMapping("/mnpsp")
public class MNPSPController {

    @Autowired
    private NumberPortabilityDBService numPortDBService;

    @Autowired
    private CustomerAcquisitionFormRepository formRepository;

    @GetMapping("/validate")
    public String validateCAF(){
        CustomerAcquisitionForm latestForm = formRepository.findLatestForm();
        if (latestForm != null) {
            return numPortDBService.checkNPDB(latestForm);
        } else {
            return "No form found.";
        }
    }
    
}

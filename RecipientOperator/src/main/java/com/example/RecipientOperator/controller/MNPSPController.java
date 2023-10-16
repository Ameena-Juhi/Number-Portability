package com.example.RecipientOperator.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.RecipientOperator.DTO.MessageDTO;
import com.example.RecipientOperator.entity.CustomerAcquisitionForm;
import com.example.RecipientOperator.repository.CustomerAcquisitionFormRepository;
import com.example.RecipientOperator.service.NumberPortabilityDBService;

@CrossOrigin
@RestController
@RequestMapping("/mnpsp")
public class MNPSPController {

    @Autowired
    private NumberPortabilityDBService numPortDBService;

    @Autowired
    private CustomerAcquisitionFormRepository formRepository;


    @GetMapping("/validate/{mobileNumber}")
    public MessageDTO validateCAF(@PathVariable("mobileNumber") String mobileNumber){
        Optional<CustomerAcquisitionForm> Form = formRepository.findByMobileNumber(mobileNumber);
        if (Form != null) {
            return numPortDBService.checkNPDB(Form);
        } else {
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setMessage("No form found.");
            return messageDTO;
        }
    }
    
}

package com.example.RecipientOperator.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RecipientOperator.DTO.SubDetailsDTO;
import com.example.RecipientOperator.entity.SubscriberDetails;
import com.example.RecipientOperator.repository.SubDetailsRepository;

@Service
public class SubscriberDetailsService {
    
    @Autowired
    private SubDetailsRepository subDetailsRepository;

    public List<SubDetailsDTO> getAllDetails() {
        List<SubscriberDetails> subscriberDetailsList = subDetailsRepository.findAll();
        return subscriberDetailsList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private SubDetailsDTO convertToDto(SubscriberDetails subscriberDetails) {
        SubDetailsDTO subDetailsDTO = new SubDetailsDTO();
        subDetailsDTO.setName(subscriberDetails.getName());
        subDetailsDTO.setAddress(subscriberDetails.getAddress());
        subDetailsDTO.setMobileNumber(subscriberDetails.getMobileNumber().getMobileNumber());
        return subDetailsDTO;
    }
}

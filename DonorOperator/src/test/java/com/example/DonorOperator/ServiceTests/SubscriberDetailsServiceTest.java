package com.example.DonorOperator.ServiceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.example.DonorOperator.DTO.SubDetailsDTO;
import com.example.DonorOperator.entity.MobileNumber;
import com.example.DonorOperator.entity.SubscriberDetails;
import com.example.DonorOperator.repository.SubscriberDetailsRepository;
import com.example.DonorOperator.service.SubscriberDetailsService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SubscriberDetailsServiceTest {

    @InjectMocks
    private SubscriberDetailsService subscriberDetailsService;

    @Mock
    private SubscriberDetailsRepository subDetailsRepository;

    @Test
    void testGetAllDetails() {
        SubscriberDetails subscriberDetails1 = new SubscriberDetails();
        subscriberDetails1.setName("John Doe");
        subscriberDetails1.setAddress("123 Street");
        MobileNumber mobileNumber1 = new MobileNumber();
        mobileNumber1.setMobileNumber("1234567890");
        subscriberDetails1.setMobileNumber(mobileNumber1);

        SubscriberDetails subscriberDetails2 = new SubscriberDetails();
        subscriberDetails2.setName("Jane Doe");
        subscriberDetails2.setAddress("456 Street");
        MobileNumber mobileNumber2 = new MobileNumber();
        mobileNumber2.setMobileNumber("9876543210");
        subscriberDetails2.setMobileNumber(mobileNumber2);

        List<SubscriberDetails> subscriberDetailsList = Arrays.asList(subscriberDetails1, subscriberDetails2);
        when(subDetailsRepository.findAll()).thenReturn(subscriberDetailsList);

        List<SubDetailsDTO> expectedDTOList = subscriberDetailsList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        List<SubDetailsDTO> resultDTOList = subscriberDetailsService.getAllDetails();

        assertEquals(expectedDTOList.size(), resultDTOList.size());
        for (int i = 0; i < expectedDTOList.size(); i++) {
            assertEquals(expectedDTOList.get(i).getName(), resultDTOList.get(i).getName());
            assertEquals(expectedDTOList.get(i).getAddress(), resultDTOList.get(i).getAddress());
            assertEquals(expectedDTOList.get(i).getMobileNumber(), resultDTOList.get(i).getMobileNumber());
        }
    }

    public SubDetailsDTO convertToDto(SubscriberDetails subscriberDetails) {
        SubDetailsDTO subDetailsDTO = new SubDetailsDTO();
        subDetailsDTO.setName(subscriberDetails.getName());
        subDetailsDTO.setAddress(subscriberDetails.getAddress());
        subDetailsDTO.setMobileNumber(subscriberDetails.getMobileNumber().getMobileNumber());
        return subDetailsDTO;
    }

}

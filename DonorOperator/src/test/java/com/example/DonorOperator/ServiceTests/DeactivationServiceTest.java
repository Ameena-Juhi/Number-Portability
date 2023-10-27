package com.example.DonorOperator.ServiceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import com.example.DonorOperator.DTO.ActivationRequestDTO;
import com.example.DonorOperator.entity.MobileNumber;
import com.example.DonorOperator.entity.NumbersPorting;
import com.example.DonorOperator.entity.SubscriberDetails;
import com.example.DonorOperator.repository.DeactivationReqRepo;
import com.example.DonorOperator.repository.MobileNumberRepository;
import com.example.DonorOperator.repository.NumbersPortingRepository;
import com.example.DonorOperator.repository.SubscriberDetailsRepository;
import com.example.DonorOperator.service.DeactivationService;
import com.example.DonorOperator.FeignClient.ClearanceClient;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DeactivationServiceTest {

    @InjectMocks
    private DeactivationService deactivationService;

    @Mock
    private MobileNumberRepository mobileNumberRepository;

    @Mock
    private SubscriberDetailsRepository subscriberDetailsRepository;

    @Mock
    private NumbersPortingRepository numbersPortingRepository;

    @Mock
    private ClearanceClient clearanceClient;

    @Mock
    private DeactivationReqRepo deactivationReqRepo;

    @Test
    void testAcceptDeactivation() {
        ActivationRequestDTO deactivationRequest = new ActivationRequestDTO();
        deactivationRequest.setMobileNumber("1234567890");
        deactivationRequest.setActivationTime(LocalDateTime.now().minusDays(1));

        MobileNumber mobileEntry = new MobileNumber();
        mobileEntry.setId(1L);
        mobileEntry.setMobileNumber("1234567890");
        when(mobileNumberRepository.findByMobileNumber("1234567890")).thenReturn(Optional.of(mobileEntry));

        SubscriberDetails subscriberDetails = new SubscriberDetails();
        subscriberDetails.setId(1L);
        subscriberDetails.setMSISDN("1234567890");
        subscriberDetails.setIMSI("IMSI123");
        subscriberDetails.setIMEI("IMEI123");
        when(subscriberDetailsRepository.findbymobilenum_id(1L)).thenReturn(subscriberDetails);

        NumbersPorting numbersPortingEntry = new NumbersPorting();
        numbersPortingEntry.setId(1L);
        numbersPortingEntry.setMobileNumber(mobileEntry);
        when(numbersPortingRepository.findByMobileNumberId(1L)).thenReturn(numbersPortingEntry);

        String authorizationHeader = "Bearer YourTokenHere";
        assertEquals("Mobile number deleted successfully.",
                deactivationService.acceptDeactivation(authorizationHeader, deactivationRequest).getMessage());
    }

    @Test
    void testSaveDeactReqs() {
        ActivationRequestDTO activationRequestDTO = new ActivationRequestDTO();
        activationRequestDTO.setMobileNumber("1234567890");
        activationRequestDTO.setActivationTime(LocalDateTime.now().plusDays(1));

        deactivationService.saveDeactReqs(activationRequestDTO);
    }
}

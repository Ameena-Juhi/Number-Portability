package com.example.DonorOperator.ServiceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Answers.valueOf;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.DonorOperator.DTO.CAFtoken;
import com.example.DonorOperator.entity.ForwardedRequests;
import com.example.DonorOperator.entity.MobileNumber;
import com.example.DonorOperator.entity.SubscriberDetails;
import com.example.DonorOperator.repository.ForwardedRequestsRepo;
import com.example.DonorOperator.repository.MobileNumberRepository;
import com.example.DonorOperator.repository.SubscriberDetailsRepository;
import com.example.DonorOperator.service.ForwardedReqService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ForwardedReqServiceTest {

    @InjectMocks
    private ForwardedReqService forwardedReqService;

    @Mock
    private ForwardedRequestsRepo forwardedRequestsRepo;

    @Mock
    private MobileNumberRepository mobileNumberRepository;

    @Mock
    private SubscriberDetailsRepository subscriberDetailsRepository;

    @Test
    void testSaveCAFDTOWithIdentityVerification() {
        CAFtoken form = new CAFtoken();
        form.setMobileNumber("1234567890");
        form.setUpc("123456");
        form.setName("John Doe");
        form.setAddress("123 Street");
        MobileNumber mobileNumber = new MobileNumber();
        mobileNumber.setId(1L);
        when(mobileNumberRepository.findByMobileNumber("1234567890")).thenReturn(Optional.of(mobileNumber));
        when(subscriberDetailsRepository.findbymobilenum_id(1L)).thenReturn(createMockSubscriberDetails(1L, "John Doe",
                "123 Street", "1234567890", "IMSI123", "IMEI123", "ICCID123", true));

        boolean result = forwardedReqService.saveCAFDTO(form);

        assertEquals(true, result);
    }

    @Test
    void testSaveCAFDTOWithIdentityVerificationFalseCase() {
        CAFtoken form = new CAFtoken();
        form.setMobileNumber("1234567890");
        form.setUpc("123456");
        form.setName("John Doe");
        form.setAddress("123 Street");
        MobileNumber mobileNumber = new MobileNumber();
        mobileNumber.setId(1L);
        when(mobileNumberRepository.findByMobileNumber("1234567890")).thenReturn(Optional.of(mobileNumber));
        when(subscriberDetailsRepository.findbymobilenum_id(1L)).thenReturn(createMockSubscriberDetails(1L, "John Doe",
                "1234 Street", "1234567890", "IMSI123", "IMEI123", "ICCID123", true));

        boolean result = forwardedReqService.saveCAFDTO(form);

        assertEquals(false, result);
    }

    @Test
    void testGetAllRequests() {
        List<ForwardedRequests> requests = new ArrayList<>();
        when(forwardedRequestsRepo.findAll()).thenReturn(requests);

        List<ForwardedRequests> result = forwardedReqService.getAllRequests();

        assertEquals(requests, result);
    }

    @Test
    void testCheckIdentityWithValidDetails() {
        CAFtoken form = new CAFtoken();
        form.setMobileNumber("1234567890");
        form.setName("John Doe");
        form.setAddress("123 Street");
        MobileNumber mobileNumber = new MobileNumber();
        mobileNumber.setId(1L);
        when(mobileNumberRepository.findByMobileNumber("1234567890")).thenReturn(Optional.of(mobileNumber));
        when(subscriberDetailsRepository.findbymobilenum_id(1L)).thenReturn(createMockSubscriberDetails(1L, "John Doe",
                "123 Street", "1234567890", "IMSI123", "IMEI123", "ICCID123", true));

        boolean result = forwardedReqService.checkIdentity(form);

        assertEquals(true, result);
    }

    @Test
    void testCheckIdentityWithInvalidDetails() {
        CAFtoken form = new CAFtoken();
        form.setMobileNumber("1234567890");
        form.setName("Jane Doe");
        form.setAddress("456 Street");
        MobileNumber mobileNumber = new MobileNumber();
        mobileNumber.setId(1L);
        when(mobileNumberRepository.findByMobileNumber("1234567890")).thenReturn(Optional.of(mobileNumber));
        when(subscriberDetailsRepository.findbymobilenum_id(1L)).thenReturn(createMockSubscriberDetails(1L, "John Doe",
                "123 Street", "1234567890", "IMSI123", "IMEI123", "ICCID123", true));

        boolean result = forwardedReqService.checkIdentity(form);

        assertEquals(false, result);
    }

    private SubscriberDetails createMockSubscriberDetails(Long id, String name, String address, String MSISDN,
            String IMSI, String IMEI, String ICCID, boolean billing_clearance) {
        SubscriberDetails subscriberDetails = new SubscriberDetails();
        subscriberDetails.setId(id);
        subscriberDetails.setName(name);
        subscriberDetails.setAddress(address);
        subscriberDetails.setMSISDN(MSISDN);
        subscriberDetails.setIMSI(IMSI);
        subscriberDetails.setIMEI(IMEI);
        subscriberDetails.setICCID(ICCID);
        subscriberDetails.setBilling_clearance(billing_clearance);
        return subscriberDetails;
    }
}

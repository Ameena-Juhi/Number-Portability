package com.example.DonorOperator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.example.DonorOperator.DTO.ActivationRequestDTO;
import com.example.DonorOperator.DTO.CAFdto;
import com.example.DonorOperator.DTO.CAFtoken;
import com.example.DonorOperator.DTO.MessageDTO;
import com.example.DonorOperator.entity.ForwardedRequests;
import com.example.DonorOperator.entity.MobileNumber;
import com.example.DonorOperator.exception.ResourceNotFoundException;
import com.example.DonorOperator.entity.DeactivationRequest;
import com.example.DonorOperator.service.DeactivationService;
import com.example.DonorOperator.service.ForwardedReqService;
import com.example.DonorOperator.service.PortingVerificationService;
import com.example.DonorOperator.service.RejectionPortService;
import com.example.DonorOperator.service.SubscriberDetailsService;
import com.example.DonorOperator.FeignClient.ClearanceClient;
import com.example.DonorOperator.controller.DonorController;
import com.example.DonorOperator.repository.MobileNumberRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DonorControllerTest {

    @InjectMocks
    private DonorController donorController;

    @Mock
    private MobileNumberRepository mobileNumberRepository;

    @Mock
    private PortingVerificationService portingVerificationService;

    @Mock
    private ForwardedReqService forwardedReqService;

    @Mock
    private ClearanceClient clearanceClient;

    @Mock
    private DeactivationService deactivationService;

    @Mock
    private SubscriberDetailsService subscriberDetailsService;

    @Mock
    private RejectionPortService rejectionPortService;

    @Test
    void testSayHello() {
        assertEquals("hello", donorController.sayHello());
    }

    @Test
    void testGetMobNums() {
        List<MobileNumber> mobileNumbers = new ArrayList<>();
        when(mobileNumberRepository.findAll()).thenReturn(mobileNumbers);

        assertEquals(mobileNumbers, donorController.getMobNums());
    }

    @Test
    void testGetAllRequestsTillNow() {
        List<ForwardedRequests> forwardedRequests = new ArrayList<>();
        when(forwardedReqService.getAllRequests()).thenReturn(forwardedRequests);

        assertEquals(forwardedRequests, donorController.getAllRequestsTillNow());
    }

    @Test
    void testRespondToPortingRequest() throws ResourceNotFoundException {
        CAFdto form = new CAFdto();
        form.setMobileNumber("1234567890");

        List<String> errorMessages = new ArrayList<>();
        errorMessages.add("Activation period check failed.");
        errorMessages.add("Outstanding payments check failed.");
        errorMessages.add("UPC mismatch check failed.");
        errorMessages.add("UPC validity check failed.");
        errorMessages.add("Change of ownership check failed.");

        when(portingVerificationService.checkActivationPeriod("1234567890")).thenReturn(false);
        when(portingVerificationService.checkOutstandingPayments("1234567890")).thenReturn(false);
        when(portingVerificationService.checkUPCMismatch(form)).thenReturn(false);
        when(portingVerificationService.checkUPCValidity("1234567890")).thenReturn(false);
        when(portingVerificationService.checkChangeOfOwnership("1234567890")).thenReturn(false);

        MessageDTO expectedMessage = new MessageDTO();
        expectedMessage.setMessage("Response: " + errorMessages);
        MessageDTO actualMessage = donorController.respondToPortingRequest("AuthorizationToken", form);

        assertEquals(expectedMessage.getMessage(), actualMessage.getMessage());
    }

    @Test
    void testGetAllDeactReqs() {
        List<DeactivationRequest> deactivationRequests = new ArrayList<>();
        when(deactivationService.getAllDeactReqs()).thenReturn(deactivationRequests);

        assertEquals(deactivationRequests, donorController.getAllDeactReqs());
    }

    @Test
    void testDeactivateMobileNumber() {
        ActivationRequestDTO deactivationRequest = new ActivationRequestDTO();
        MessageDTO messageDTO = new MessageDTO();
        when(deactivationService.acceptDeactivation("AuthorizationToken", deactivationRequest)).thenReturn(messageDTO);

        assertEquals(messageDTO, donorController.deactivateMobileNumber("AuthorizationToken", deactivationRequest));
    }

    @Test
    void testCancelRequest() {
        MessageDTO mobileNumber = new MessageDTO();
        MessageDTO messageDTO = new MessageDTO();
        when(rejectionPortService.processRejection(mobileNumber)).thenReturn(messageDTO);

        assertEquals(messageDTO, donorController.cancelRequest("AuthorizationToken", mobileNumber));
    }
}

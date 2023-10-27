package com.example.DonorOperator.ServiceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.example.DonorOperator.DTO.MessageDTO;
import com.example.DonorOperator.entity.ForwardedRequests;
import com.example.DonorOperator.entity.MobileNumber;
import com.example.DonorOperator.entity.NumbersPorting;
import com.example.DonorOperator.repository.ForwardedRequestsRepo;
import com.example.DonorOperator.repository.MobileNumberRepository;
import com.example.DonorOperator.repository.NumbersPortingRepository;
import com.example.DonorOperator.service.RejectionPortService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RejectionPortServiceTest {

    @InjectMocks
    private RejectionPortService rejectionPortService;

    @Mock
    private NumbersPortingRepository numbersPortingRepository;

    @Mock
    private ForwardedRequestsRepo forwardedRequestsRepo;

    @Mock
    private MobileNumberRepository mobileNumberRepository;

    @Test
    void testProcessRejectionWithExistingRequest() {
        String mobNum = "1234567890";
        MessageDTO mobileNumber = new MessageDTO();
        mobileNumber.setMessage(mobNum);
        ForwardedRequests request = new ForwardedRequests();
        when(forwardedRequestsRepo.findByMobileNumber(mobNum)).thenReturn(request);

        MessageDTO result = rejectionPortService.processRejection(mobileNumber);

        assertEquals("mobile Number doesnt exist", result.getMessage());
    }

    @Test
    void testProcessRejectionWithExistingMobileNumber() {
        String mobNum = "1234567890";
        MessageDTO mobileNumber = new MessageDTO();
        mobileNumber.setMessage(mobNum);
        MobileNumber mobileNum = new MobileNumber();
        mobileNum.setId(1L);
        Optional<MobileNumber> optionalMobileNumber = Optional.of(mobileNum);
        when(mobileNumberRepository.findByMobileNumber(mobNum)).thenReturn(optionalMobileNumber);

        NumbersPorting numbersPorting = new NumbersPorting();
        numbersPorting.setId(1L);
        when(numbersPortingRepository.findByMobileNumberId(1L)).thenReturn(numbersPorting);

        MessageDTO result = rejectionPortService.processRejection(mobileNumber);

        assertEquals("Succesfully cancelled at Donor Operator!", result.getMessage());
    }

    @Test
    void testProcessRejectionWithNonExistentData() {
        String mobNum = "1234567890";
        MessageDTO mobileNumber = new MessageDTO();
        mobileNumber.setMessage(mobNum);
        when(forwardedRequestsRepo.findByMobileNumber(mobNum)).thenReturn(null);
        when(mobileNumberRepository.findByMobileNumber(mobNum))
                .thenReturn(Optional.of(createMockMobileNumber(1L, "1234567890")));

        MessageDTO result = rejectionPortService.processRejection(mobileNumber);

        assertEquals("Succesfully cancelled at Donor Operator!", result.getMessage());
    }

    private MobileNumber createMockMobileNumber(Long id, String mobileNumber) {
        MobileNumber mockMobileNumber = new MobileNumber();
        mockMobileNumber.setId(id);
        mockMobileNumber.setMobileNumber(mobileNumber);
        return mockMobileNumber;
    }
}

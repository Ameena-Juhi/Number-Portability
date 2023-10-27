package com.example.DonorOperator.ServiceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import com.example.DonorOperator.DTO.MessageDTO;
import com.example.DonorOperator.entity.MobileNumber;
import com.example.DonorOperator.entity.NumbersPorting;
import com.example.DonorOperator.repository.MobileNumberRepository;
import com.example.DonorOperator.repository.NumbersPortingRepository;
import com.example.DonorOperator.service.MobileNumberService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MobileNumberServiceTest {

    @InjectMocks
    private MobileNumberService mobileNumberService;

    @Mock
    private MobileNumberRepository mobileNumberRepository;

    @Mock
    private NumbersPortingRepository numbersPortingRepository;

    @Test
    void testRetrieveMobileNumber() throws Exception {
        MessageDTO SMS = new MessageDTO();
        SMS.setMessage("port 1234567890");
        MobileNumber mobileNumber = new MobileNumber();
        mobileNumber.setId(1L);
        mobileNumber.setMobileNumber("1234567890");
        Optional<MobileNumber> optionalMobileNumber = Optional.of(mobileNumber);
        when(mobileNumberRepository.findByMobileNumber("1234567890")).thenReturn(optionalMobileNumber);

        NumbersPorting numbersPorting = new NumbersPorting();
        numbersPorting.setId(1L);
        numbersPorting.setMobileNumber(mobileNumber);
        numbersPorting.setRequestedUpcTime(new Date());
        when(numbersPortingRepository.findByMobileNumberId(1L)).thenReturn(numbersPorting);

        MessageDTO result = mobileNumberService.retrieveMobileNumber(SMS);
        assertEquals("Already generated UPC for this number!", result.getMessage());
    }

    @Test
    public void testValidateSMSValidFormat() {
        boolean result = mobileNumberService.validateSMS("port 1234567890");
        assertEquals(true, result);
    }

    @Test
    public void testValidateSMSInvalidFormat() {
        boolean result = mobileNumberService.validateSMS("random message");
        assertEquals(false, result);
    }

    @Test
    void testCreateNewPorting() throws Exception {
        MobileNumber existingMobileNumber = new MobileNumber();
        existingMobileNumber.setId(1L);
        existingMobileNumber.setMobileNumber("1234567890");

        when(numbersPortingRepository.save(any())).then(AdditionalAnswers.returnsFirstArg());

        MessageDTO msg = new MessageDTO();
        mobileNumberService.createNewPorting(existingMobileNumber, msg);

        assertEquals(8, msg.getMessage().length()); // assuming the UPC is of length 8
    }

}

package com.example.DonorOperator.ServiceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;
import java.util.regex.Pattern;

import com.example.DonorOperator.entity.MobileNumber;
import com.example.DonorOperator.repository.MobileNumberRepository;
import com.example.DonorOperator.repository.NumbersPortingRepository;
import com.example.DonorOperator.service.MobileNumberService;
import com.example.DonorOperator.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class MobileNumberServiceTest {

    @InjectMocks
    private MobileNumberService mobileNumberService;

    @Mock
    private MobileNumberRepository mobileNumberRepository;

    @Mock
    private NumbersPortingRepository numbersPortingRepository;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testRetrieveMobileNumber() throws ResourceNotFoundException {
        // Create a sample SMS
        String sms = "PORT 9014315818";

        // Create a sample MobileNumber entity

        MobileNumber mobileNumber = new MobileNumber();
        mobileNumber.setId((long) 1);
        mobileNumber.setMobileNumber("9014315818");

        // Define expected behavior of mocked repositories
        when(mobileNumberRepository.findByMobileNumber(any())).thenReturn(mobileNumber);
        when(numbersPortingRepository.findByMobileNumberId(mobileNumber.getId())).thenReturn(null);

        // Call the service method
        String upc = mobileNumberService.retrieveMobileNumber(sms);
        assertNotNull(upc);
        assertTrue(Pattern.matches("^\\d{8}$", upc));
    }

}

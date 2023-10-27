package com.example.DonorOperator.ServiceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.DonorOperator.DTO.CAFdto;
import com.example.DonorOperator.entity.MobileNumber;
import com.example.DonorOperator.entity.NumbersPorting;
import com.example.DonorOperator.entity.SubscriberDetails;
import com.example.DonorOperator.exception.ResourceNotFoundException;
import com.example.DonorOperator.repository.MobileNumberRepository;
import com.example.DonorOperator.repository.NumbersPortingRepository;
import com.example.DonorOperator.repository.SubscriberDetailsRepository;
import com.example.DonorOperator.service.PortingVerificationService;

@ExtendWith(MockitoExtension.class)
public class PortingVerificationServiceTest {

    @InjectMocks
    private PortingVerificationService portingVerificationService;

    @Mock
    private NumbersPortingRepository numbersPortingRepository;

    @Mock
    private MobileNumberRepository mobileNumberRepository;

    @Mock
    private SubscriberDetailsRepository subscriberDetailsRepository;

    @Test
    void testCheckOutstandingPayments() throws ResourceNotFoundException {
        String mobNum = "1234567890";
        SubscriberDetails subscriberDetails = createMockSubscriberDetails(1L, "John Doe", "123 Street", "1234567890",
                "IMSI123", "IMEI123", "ICCID123", true, new Date(), createMockMobileNumber(1L, "1234567890"));
        when(mobileNumberRepository.findByMobileNumber(mobNum))
                .thenReturn(Optional.of(createMockMobileNumber(1L, "1234567890")));
        when(subscriberDetailsRepository.findbymobilenum_id(1L)).thenReturn(subscriberDetails);

        boolean result = portingVerificationService.checkOutstandingPayments(mobNum);

        assertEquals(true, result);
    }

    @Test
    void testCheckActivationPeriod() throws ResourceNotFoundException {
        String mobNum = "1234567890";
        SubscriberDetails subscriberDetails = createMockSubscriberDetails(1L, "John Doe", "123 Street", "1234567890",
                "IMSI123", "IMEI123", "ICCID123", true, new Date(System.currentTimeMillis() -
                        9 * 60 * 1000),
                createMockMobileNumber(1L, "1234567890"));
        when(mobileNumberRepository.findByMobileNumber(mobNum))
                .thenReturn(Optional.of(createMockMobileNumber(1L, "1234567890")));
        when(subscriberDetailsRepository.findbymobilenum_id(1L)).thenReturn(subscriberDetails);

        boolean result = portingVerificationService.checkActivationPeriod(mobNum);

        assertEquals(true, result);
    }

    @Test
    void testCheckUPCMismatch() throws ResourceNotFoundException {
        CAFdto form = new CAFdto();
        form.setMobileNumber("1234567890");
        form.setUpc("123456");
        String mobNum = form.getMobileNumber();
        when(mobileNumberRepository.findByMobileNumber(mobNum))
                .thenReturn(Optional.of(createMockMobileNumber(1L, "1234567890")));
        NumbersPorting portingRequest = createMockNumbersPorting(1L, "123456", new Date(),
                createMockMobileNumber(1L, "1234567890"));
        when(portingVerificationService.getPortingRequest(mobNum)).thenReturn(portingRequest);

        boolean result = portingVerificationService.checkUPCMismatch(form);

        assertEquals(true, result);
    }

    @Test
    void testCheckChangeOfOwnership() throws ResourceNotFoundException {
        String mobNum = "1234567890";
        when(mobileNumberRepository.findByMobileNumber(mobNum))
                .thenReturn(Optional.of(createMockMobileNumber(1L, "1234567890")));
        NumbersPorting portingRequest = createMockNumbersPorting(1L, "123456", new Date(),
                createMockMobileNumber(1L, "1234567890"));
        when(portingVerificationService.getPortingRequest(mobNum)).thenReturn(portingRequest);

        boolean result = portingVerificationService.checkChangeOfOwnership(mobNum);

        assertEquals(true, result);
    }

    @Test
    void testCheckUPCValidity() throws ResourceNotFoundException {
        String mobNum = "1234567890";
        when(mobileNumberRepository.findByMobileNumber(mobNum))
                .thenReturn(Optional.of(createMockMobileNumber(1L, "1234567890")));
        NumbersPorting portingRequest = createMockNumbersPorting(1L, "123456",
                new Date(System.currentTimeMillis() - 30 * 60 * 1000), createMockMobileNumber(1L, "1234567890"));
        when(portingVerificationService.getPortingRequest(mobNum)).thenReturn(portingRequest);

        boolean result = portingVerificationService.checkUPCValidity(mobNum);

        assertEquals(true, result);
    }

    private NumbersPorting createMockNumbersPorting(Long id, String upc, Date requestedUpcTime,
            MobileNumber mobileNumber) {
        NumbersPorting numbersPorting = new NumbersPorting();
        numbersPorting.setId(id);
        numbersPorting.setUpc(upc);
        numbersPorting.setRequestedUpcTime(requestedUpcTime);
        numbersPorting.setMobileNumber(mobileNumber);
        return numbersPorting;
    }

    private SubscriberDetails createMockSubscriberDetails(Long id, String name,
            String address, String MSISDN,
            String IMSI, String IMEI, String ICCID, boolean billing_clearance, Date portedInDate,
            MobileNumber mobileNumber) {
        SubscriberDetails subscriberDetails = new SubscriberDetails();
        subscriberDetails.setId(id);
        subscriberDetails.setName(name);
        subscriberDetails.setAddress(address);
        subscriberDetails.setMSISDN(MSISDN);
        subscriberDetails.setIMSI(IMSI);
        subscriberDetails.setIMEI(IMEI);
        subscriberDetails.setICCID(ICCID);
        subscriberDetails.setBilling_clearance(billing_clearance);
        subscriberDetails.setPortedInDate(portedInDate);
        subscriberDetails.setMobileNumber(mobileNumber);
        return subscriberDetails;
    }

    private MobileNumber createMockMobileNumber(Long id, String mobileNumber) {
        MobileNumber mockMobileNumber = new MobileNumber();
        mockMobileNumber.setId(id);
        mockMobileNumber.setMobileNumber(mobileNumber);
        return mockMobileNumber;
    }

    @Test
    void testCheckOutstandingPaymentsFalseCase() throws ResourceNotFoundException {
        String mobNum = "1234567890";
        SubscriberDetails subscriberDetails = createMockSubscriberDetails(1L, "John Doe", "123 Street", "1234567890",
                "IMSI123", "IMEI123", "ICCID123", false, new Date(), createMockMobileNumber(1L, "1234567890"));
        when(mobileNumberRepository.findByMobileNumber(mobNum))
                .thenReturn(Optional.of(createMockMobileNumber(1L, "1234567890")));
        when(subscriberDetailsRepository.findbymobilenum_id(1L)).thenReturn(subscriberDetails);

        boolean result = portingVerificationService.checkOutstandingPayments(mobNum);

        assertEquals(false, result);
    }

    @Test
    void testCheckActivationPeriodFalseCase() throws ResourceNotFoundException {
        String mobNum = "1234567890";
        when(mobileNumberRepository.findByMobileNumber(mobNum))
                .thenReturn(Optional.of(createMockMobileNumber(1L, "1234567890")));
        SubscriberDetails subscriberDetails = createMockSubscriberDetails(1L, "John Doe", "123 Street", "1234567890",
                "IMSI123", "IMEI123", "ICCID123", true, new Date(System.currentTimeMillis() - 91 * 24 * 60 * 60 * 1000),
                createMockMobileNumber(1L, "1234567890")); // 91 days ago
        when(subscriberDetailsRepository.findbymobilenum_id(1L)).thenReturn(subscriberDetails);

        boolean result = portingVerificationService.checkActivationPeriod(mobNum);

        assertEquals(false, result);
    }

    @Test
    void testCheckUPCMismatchFalseCase() throws ResourceNotFoundException {
        CAFdto form = new CAFdto();
        form.setMobileNumber("1234567890");
        form.setUpc("654321"); // Different UPC from the mock data
        String mobNum = form.getMobileNumber();
        when(mobileNumberRepository.findByMobileNumber(mobNum))
                .thenReturn(Optional.of(createMockMobileNumber(1L, "1234567890")));
        NumbersPorting portingRequest = createMockNumbersPorting(1L, "123456", new Date(),
                createMockMobileNumber(1L, "1234567890"));
        when(portingVerificationService.getPortingRequest(mobNum)).thenReturn(portingRequest);

        boolean result = portingVerificationService.checkUPCMismatch(form);

        assertEquals(false, result);
    }

    @Test
    void testCheckChangeOfOwnershipFalseCase() throws ResourceNotFoundException {
        String mobNum = "1234567890";
        when(mobileNumberRepository.findByMobileNumber(mobNum))
                .thenReturn(Optional.of(createMockMobileNumber(1L, "1234567890")));
        when(portingVerificationService.getPortingRequest(mobNum)).thenReturn(null); // No porting request found

        boolean result = portingVerificationService.checkChangeOfOwnership(mobNum);

        assertEquals(false, result);
    }

    @Test
    void testCheckUPCValidityFalseCase() throws ResourceNotFoundException {
        String mobNum = "1234567890";
        when(mobileNumberRepository.findByMobileNumber(mobNum))
                .thenReturn(Optional.of(createMockMobileNumber(1L, "1234567890")));
        NumbersPorting portingRequest = createMockNumbersPorting(1L, "123456",
                new Date(System.currentTimeMillis() - 50 * 60 * 1000), // A time greater than 40 minutes ago
                createMockMobileNumber(1L, "1234567890"));
        when(portingVerificationService.getPortingRequest(mobNum)).thenReturn(portingRequest);

        boolean result = portingVerificationService.checkUPCValidity(mobNum);

        assertEquals(false, result);
    }

}

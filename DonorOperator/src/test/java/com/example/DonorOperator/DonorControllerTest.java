package com.example.DonorOperator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.DonorOperator.controller.DonorController;
import com.example.DonorOperator.service.MobileNumberService;
import com.example.DonorOperator.service.PortingVerificationService;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
class DonorControllerTest {

    @InjectMocks
    private DonorController donorController;

    @Mock
    private MobileNumberService mobileNumberService;

    @Mock
    private PortingVerificationService portingVerificationService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {

        org.mockito.MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(donorController).build();
    }

    @Test
    public void testPortingSMS() throws Exception {
        // Mock the behavior of mobileNumberService
        Mockito.when(mobileNumberService.retrieveMobileNumber(Mockito.anyString()))
                .thenReturn("UniquePortingCode123");

        // Send a POST request to /operator/port with sample SMS content
        mockMvc.perform(MockMvcRequestBuilders.post("/operator/port")
                .content("Sample SMS content")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testRespondToPortingRequest() throws Exception {
        // Mock the behavior of portingVerificationService
        Mockito.when(portingVerificationService.checkActivationPeriod(Mockito.anyString())).thenReturn(true);
        Mockito.when(portingVerificationService.checkOutstandingPayments(Mockito.anyString())).thenReturn(true);
        Mockito.when(portingVerificationService.checkUPCMismatch(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
        Mockito.when(portingVerificationService.checkUPCValidity(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
        Mockito.when(portingVerificationService.checkChangeOfOwnership(Mockito.anyString())).thenReturn(true);

        // Send a POST request to /operator/request/123 with upc parameter
        mockMvc.perform(MockMvcRequestBuilders.post("/operator/request/123")
                .param("upc", "SampleUPC")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}


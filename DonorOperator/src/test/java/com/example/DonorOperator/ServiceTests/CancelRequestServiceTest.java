// package com.example.DonorOperator.ServiceTests;

// import com.example.DonorOperator.DTO.MessageDTO;
// import com.example.DonorOperator.entity.MobileNumber;
// import com.example.DonorOperator.entity.NumbersPorting;
// import com.example.DonorOperator.exception.ResourceNotFoundException;
// import com.example.DonorOperator.repository.MobileNumberRepository;
// import com.example.DonorOperator.repository.NumbersPortingRepository;
// import com.example.DonorOperator.service.CancelRequestService;

// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;

// import java.util.Optional;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// public class CancelRequestServiceTest {

// @InjectMocks
// private CancelRequestService cancelRequestService;

// @Mock
// private MobileNumberRepository mobileNumberRepository;

// @Mock
// private NumbersPortingRepository numbersPortingRepository;

// // @Test
// // void testCancelRequestSuccess() throws ResourceNotFoundException {
// // MessageDTO SMS = new MessageDTO();
// // SMS.setMessage("cancel 1234567890");
// // MobileNumber mobileNumber = new MobileNumber();
// // mobileNumber.setId(1L);
// // mobileNumber.setMobileNumber("1234567890");
// //
// when(mobileNumberRepository.findByMobileNumber(mobileNumber.getMobileNumber()))
// // .thenReturn(Optional.of(createMockMobileNumber(1L, "1234567890")));

// // NumbersPorting numbersPorting = new NumbersPorting();
// // numbersPorting.setId(1L);
// // numbersPorting.setMobileNumber(mobileNumber);
// //
// when(numbersPortingRepository.findByMobileNumberId(1L)).thenReturn(numbersPorting);

// // MessageDTO result = cancelRequestService.cancelRequest(SMS);
// // assertEquals("Succesfully cancelled!", result.getMessage());
// // verify(numbersPortingRepository,
// // times(1)).deleteById(numbersPorting.getId());
// // }

// @Test
// void testCancelRequestInvalidSMSFormat() throws ResourceNotFoundException {
// MessageDTO SMS = new MessageDTO();
// SMS.setMessage("invalid format");
// MessageDTO result = cancelRequestService.cancelRequest(SMS);
// assertEquals("SMS is not Valid!", result.getMessage());
// }

// @Test
// void testCancelRequestMobileNumberNotFound() {
// MessageDTO SMS = new MessageDTO();
// SMS.setMessage("cancel 1234567890");
// when(mobileNumberRepository.findByMobileNumber(anyString())).thenReturn(Optional.empty());
// assertThrows(ResourceNotFoundException.class, () ->
// cancelRequestService.cancelRequest(SMS));
// }

// @Test
// void testCancelRequestPortingNumberNotFound() throws
// ResourceNotFoundException {
// MessageDTO SMS = new MessageDTO();
// SMS.setMessage("cancel 1234567890");
// MobileNumber mobileNumber = new MobileNumber();
// mobileNumber.setId(1L);
// mobileNumber.setMobileNumber("1234567890");
// when(mobileNumberRepository.findByMobileNumber(mobileNumber.getMobileNumber()))
// .thenReturn(Optional.of(createMockMobileNumber(1L, "1234567890")));
// when(numbersPortingRepository.findByMobileNumberId(1L)).thenReturn(null);
// MessageDTO result = cancelRequestService.cancelRequest(SMS);
// assertEquals("Succesfully cancelled!", result.getMessage()); // assuming it
// returns a success message
// }

// // Helper method for creating mock MobileNumber object
// private MobileNumber createMockMobileNumber(Long id, String mobileNumber) {
// MobileNumber mockMobileNumber = new MobileNumber();
// mockMobileNumber.setId(id);
// mockMobileNumber.setMobileNumber(mobileNumber);
// return mockMobileNumber;
// }
// }

// // import com.example.DonorOperator.DTO.MessageDTO;
// // import com.example.DonorOperator.entity.MobileNumber;
// // import com.example.DonorOperator.entity.NumbersPorting;
// // import com.example.DonorOperator.exception.ResourceNotFoundException;
// // import com.example.DonorOperator.repository.MobileNumberRepository;
// // import com.example.DonorOperator.repository.NumbersPortingRepository;
// // import com.example.DonorOperator.service.CancelRequestService;

// // import org.junit.jupiter.api.Test;
// // import org.mockito.InjectMocks;
// // import org.mockito.Mock;

// // import static org.junit.jupiter.api.Assertions.*;
// // import static org.mockito.Mockito.*;

// // import java.util.Optional;

// // public class CancelRequestServiceTest {

// // @InjectMocks
// // private CancelRequestService cancelRequestService;

// // @Mock
// // private MobileNumberRepository mobileNumberRepository;

// // @Mock
// // private NumbersPortingRepository numbersPortingRepository;

// // @Test
// // void testCancelRequest() throws ResourceNotFoundException {
// // MessageDTO SMS = new MessageDTO();
// // SMS.setMessage("cancel 1234567890");
// // MobileNumber mobileNumber = new MobileNumber();
// // mobileNumber.setId(1L);
// // mobileNumber.setMobileNumber("1234567890");
// //
// when(mobileNumberRepository.findByMobileNumber(mobileNumber.getMobileNumber()))
// // .thenReturn(Optional.of(createMockMobileNumber(1L, "1234567890")));

// // NumbersPorting numbersPorting = new NumbersPorting();
// // numbersPorting.setId(1L);
// // numbersPorting.setMobileNumber(mobileNumber);
// //
// when(numbersPortingRepository.findByMobileNumberId(1L)).thenReturn(numbersPorting);

// // MessageDTO result = cancelRequestService.cancelRequest(SMS);
// // assertEquals("Succesfully cancelled!", result.getMessage());
// // verify(numbersPortingRepository,
// // times(1)).deleteById(numbersPorting.getId());
// // }

// // @Test
// // void testInvalidCancelRequest() throws ResourceNotFoundException {
// // MessageDTO SMS = new MessageDTO();
// // SMS.setMessage("random message");
// // MessageDTO result = cancelRequestService.cancelRequest(SMS);
// // assertEquals("SMS is not Valid!", result.getMessage());
// // verifyNoInteractions(mobileNumberRepository);
// // verifyNoInteractions(numbersPortingRepository);
// // }

// // private MobileNumber createMockMobileNumber(Long id, String mobileNumber)
// {
// // MobileNumber mockMobileNumber = new MobileNumber();
// // mockMobileNumber.setId(id);
// // mockMobileNumber.setMobileNumber(mobileNumber);
// // return mockMobileNumber;
// // }
// // }

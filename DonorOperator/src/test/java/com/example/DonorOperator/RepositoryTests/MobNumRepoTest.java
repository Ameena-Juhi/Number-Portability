// package com.example.DonorOperator.RepositoryTests;

// import com.example.DonorOperator.entity.MobileNumber;
// import com.example.DonorOperator.repository.MobileNumberRepository;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mock;
// import org.mockito.Mockito;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
// import org.springframework.test.context.ActiveProfiles;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// @DataJpaTest
// @ActiveProfiles("test")
// public class MobNumRepoTest {

// @Mock
// private MobileNumberRepository mobileNumberRepository;

// @BeforeEach
// public void setup() {
// // org.mockito.MockitoAnnotations.openMocks(this);

// }

// @Test
// public void testFindByMobileNumber() {
// String mobileNumber = "9014315818";

// // Mock the repository behavior
// MobileNumber mobileNumberEntity = new MobileNumber();
// mobileNumberEntity.setId(1L);
// mobileNumberEntity.setMobileNumber(mobileNumber);

// Mockito.when(mobileNumberRepository.findByMobileNumber(mobileNumber)).thenReturn(mobileNumberEntity);

// // Perform the test
// MobileNumber result =
// mobileNumberRepository.findByMobileNumber(mobileNumber);

// // Assert the result
// assertEquals(mobileNumber, result.getMobileNumber());
// }
// }

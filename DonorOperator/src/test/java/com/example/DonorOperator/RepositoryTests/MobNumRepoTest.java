package com.example.DonorOperator.RepositoryTests;

import com.example.DonorOperator.entity.MobileNumber;
import com.example.DonorOperator.repository.MobileNumberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class MobNumRepoTest {

    @InjectMocks
    private MobileNumberRepository mobileNumberRepository;

    @MockBean
    private MobileNumberRepository repository;

    @BeforeEach
    public void setup() {
        org.mockito.MockitoAnnotations.openMocks(this);

    }

    @Test
    public void testFindByMobileNumber() {
        String mobileNumber = "9014315818";

        // Mock the repository behavior
        MobileNumber mobileNumberEntity = new MobileNumber();
        mobileNumberEntity.setId(1L);
        mobileNumberEntity.setMobileNumber(mobileNumber);

        Mockito.when(repository.findByMobileNumber(mobileNumber)).thenReturn(mobileNumberEntity);

        // Perform the test
        MobileNumber result = mobileNumberRepository.findByMobileNumber(mobileNumber);

        // Assert the result
        assertEquals(mobileNumber, result.getMobileNumber());
    }
}

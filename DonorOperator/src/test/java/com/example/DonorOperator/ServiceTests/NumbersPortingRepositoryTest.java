package com.example.DonorOperator.ServiceTests;

import com.example.DonorOperator.entity.MobileNumber;
import com.example.DonorOperator.entity.NumbersPorting;
import com.example.DonorOperator.repository.MobileNumberRepository;
import com.example.DonorOperator.repository.NumbersPortingRepository;

import jakarta.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
public class NumbersPortingRepositoryTest {

    @Autowired
    private NumbersPortingRepository numbersPortingRepository;

    @Autowired
    private MobileNumberRepository mobileNumberRepository;

    @Test
    @Transactional
    public void testFindNumbersPortingByMobileNumber() {
        // Create a sample MobileNumber entity
        MobileNumber mobileNumber = new MobileNumber();
        mobileNumber.setMobileNumber("9014315818");

        // Create a sample NumbersPorting entity
        NumbersPorting numbersPorting = new NumbersPorting();
        numbersPorting.setMobileNumber(mobileNumber);
        numbersPorting.setUpc("12345678");

        // Save the entities to the database
        mobileNumber = mobileNumberRepository.save(mobileNumber); // Ensure the entity is attached
        numbersPortingRepository.save(numbersPorting);

        // Find NumbersPorting by MobileNumber
        NumbersPorting foundNumbersPorting = numbersPortingRepository.findByMobileNumberId(mobileNumber.getId());

        // Perform assertions
        assertEquals("12345678", foundNumbersPorting.getUpc());
    }
}

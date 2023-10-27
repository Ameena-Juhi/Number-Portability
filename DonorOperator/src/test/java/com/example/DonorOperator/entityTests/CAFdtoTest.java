package com.example.DonorOperator.entityTests;

import org.junit.jupiter.api.Test;

import com.example.DonorOperator.DTO.CAFdto;

import static org.junit.jupiter.api.Assertions.*;

public class CAFdtoTest {

    @Test
    void testCAFdtoGettersAndSetters() {
        // Test data
        String mobileNumber = "1234567890";
        String upc = "UPC123";
        String name = "John Doe";
        String address = "123 Street";
        boolean clearance = true;

        CAFdto cafDto = new CAFdto();
        cafDto.setMobileNumber(mobileNumber);
        cafDto.setUpc(upc);
        cafDto.setName(name);
        cafDto.setAddress(address);
        cafDto.setClearance(clearance);

        assertEquals(mobileNumber, cafDto.getMobileNumber());
        assertEquals(upc, cafDto.getUpc());
        assertEquals(name, cafDto.getName());
        assertEquals(address, cafDto.getAddress());
        assertTrue(cafDto.isClearance());
    }
}

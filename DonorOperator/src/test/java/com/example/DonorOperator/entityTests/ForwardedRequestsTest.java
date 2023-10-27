package com.example.DonorOperator.entityTests;

import org.junit.jupiter.api.Test;

import com.example.DonorOperator.entity.ForwardedRequests;

import static org.junit.jupiter.api.Assertions.*;

public class ForwardedRequestsTest {

    @Test
    void testForwardedRequestsEntity() {

        Long id = 1L;
        String mobileNumber = "1234567890";
        String upc = "UPC123";
        String name = "John Doe";
        String address = "123 Street";

        ForwardedRequests forwardedRequests = new ForwardedRequests();
        forwardedRequests.setId(id);
        forwardedRequests.setMobileNumber(mobileNumber);
        forwardedRequests.setUpc(upc);
        forwardedRequests.setName(name);
        forwardedRequests.setAddress(address);

        assertEquals(id, forwardedRequests.getId());
        assertEquals(mobileNumber, forwardedRequests.getMobileNumber());
        assertEquals(upc, forwardedRequests.getUpc());
        assertEquals(name, forwardedRequests.getName());
        assertEquals(address, forwardedRequests.getAddress());
    }
}

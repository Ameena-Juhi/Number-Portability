package com.example.RecipientOperator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.RecipientOperator.entity.ActivationRequest;

public interface ActivationReqRepo extends JpaRepository<ActivationRequest, Long> {
    // ActivationRequest findByMobileNumberRequest(String MobileNumber);
}

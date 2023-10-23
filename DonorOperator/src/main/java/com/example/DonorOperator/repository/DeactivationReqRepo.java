package com.example.DonorOperator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.DonorOperator.entity.DeactivationRequest;

public interface DeactivationReqRepo extends JpaRepository<DeactivationRequest, Long> {

}

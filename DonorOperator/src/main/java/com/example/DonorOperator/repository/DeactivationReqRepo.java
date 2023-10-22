package com.example.DonorOperator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.DonorOperator.entity.DeactivateRequest;

public interface DeactivationReqRepo extends JpaRepository<DeactivateRequest,Long>{
    
}

package com.example.RecipientOperator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.RecipientOperator.entity.MobileNumber;

@Repository
public interface MobileNumberRepository extends JpaRepository<MobileNumber,Long>{
    
}

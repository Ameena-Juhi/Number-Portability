package com.example.RecipientOperator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.RecipientOperator.entity.NumberPortabilityDB;

@Repository
public interface NumberPortabilityDBRepository extends JpaRepository<NumberPortabilityDB,Long> {
    
    NumberPortabilityDB findByPortingNumber(String mobileNumber);
}

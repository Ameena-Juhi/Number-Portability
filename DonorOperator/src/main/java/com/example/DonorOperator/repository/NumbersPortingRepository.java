package com.example.DonorOperator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.DonorOperator.entity.NumbersPorting;

@Repository
public interface NumbersPortingRepository extends JpaRepository<NumbersPorting,Long> {

    NumbersPorting findBymobileNumberIdNumbersPorting(Long mobileNum_id);
    
}

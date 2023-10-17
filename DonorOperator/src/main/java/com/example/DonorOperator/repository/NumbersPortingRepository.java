package com.example.DonorOperator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.DonorOperator.entity.NumbersPorting;

@Repository
public interface NumbersPortingRepository extends JpaRepository<NumbersPorting,Long> {

    @Query("SELECT np FROM NumbersPorting np WHERE np.mobileNumber.id = :mobileNumId")
    NumbersPorting findByMobileNumberId(Long mobileNumId);
    
}

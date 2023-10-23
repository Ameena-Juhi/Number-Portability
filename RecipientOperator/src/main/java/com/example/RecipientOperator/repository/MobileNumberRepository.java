package com.example.RecipientOperator.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.RecipientOperator.entity.MobileNumber;

@Repository
public interface MobileNumberRepository extends JpaRepository<MobileNumber,Long>{
    @Query(value = "SELECT * FROM mobile_numbers WHERE mobile_number = :mobileNumber", nativeQuery = true)
    Optional<MobileNumber> findByMobileNumber(String mobileNumber);
}

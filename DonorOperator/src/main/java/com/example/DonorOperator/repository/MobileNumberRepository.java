package com.example.DonorOperator.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.DonorOperator.entity.MobileNumber;

@Repository
public interface MobileNumberRepository extends JpaRepository<MobileNumber, Long> {

    Optional<MobileNumber> findByMobileNumber(String number);

}

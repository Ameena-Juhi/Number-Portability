package com.example.DonorOperator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.DonorOperator.entity.SubscriberDetails;

@Repository
public interface SubscriberDetailsRepository extends JpaRepository<SubscriberDetails, Long>{

    @Query("SELECT sd FROM SubscriberDetails sd WHERE sd.mobileNumber.id = :mobileNumId")
    SubscriberDetails findbymobilenum_id(Long mobileNumId);
}

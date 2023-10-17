package com.example.DonorOperator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.DonorOperator.entity.SubscriberDetails;

@Repository
public interface SubscriberDetailsRepository extends JpaRepository<SubscriberDetails, Long>{
    SubscriberDetails findbymobilenum_id(Long mobileNum_id);
}

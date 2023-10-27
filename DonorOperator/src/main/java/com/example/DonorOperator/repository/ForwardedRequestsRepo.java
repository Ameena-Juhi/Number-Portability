package com.example.DonorOperator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.DonorOperator.entity.ForwardedRequests;

@Repository
public interface ForwardedRequestsRepo extends JpaRepository<ForwardedRequests, Long> {

    ForwardedRequests findByMobileNumber(String mobNum);
}

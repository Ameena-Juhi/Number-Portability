package com.example.RecipientOperator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.RecipientOperator.entity.SubscriberDetails;

public interface SubDetailsRepository extends JpaRepository<SubscriberDetails, Long> {

}

package com.example.RecipientOperator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.RecipientOperator.entity.CustomerAcquisitionForm;

@Repository
public interface CustomerAcquisitionFormRepository extends JpaRepository<CustomerAcquisitionForm,Long>{

    @Query(value = "SELECT * FROM customer_acquisition_form ORDER BY requested_time DESC LIMIT 1", nativeQuery = true)
    CustomerAcquisitionForm findLatestForm();
}

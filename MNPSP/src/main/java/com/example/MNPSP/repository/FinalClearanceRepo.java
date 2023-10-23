package com.example.MNPSP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.MNPSP.entity.FinalClearance;

@Repository
public interface FinalClearanceRepo extends JpaRepository<FinalClearance,Long>{
    
    @Query(value = "SELECT * FROM Final_Clearance WHERE mobile_number = ?1", nativeQuery = true)
    FinalClearance findByMobileNumClearance(String mobNum);
}

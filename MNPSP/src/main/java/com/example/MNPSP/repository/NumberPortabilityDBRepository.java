package com.example.MNPSP.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MNPSP.entity.NumberPortabilityDB;

@Repository
public interface NumberPortabilityDBRepository extends JpaRepository<NumberPortabilityDB, Long> {

    Optional<NumberPortabilityDB> findByPortingNumber(String mobileNumber);
}

package com.example.MNPSP.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MNPSP.DTO.ValidationClearanceDTO;
import com.example.MNPSP.entity.FinalClearance;
import com.example.MNPSP.entity.NumberPortabilityDB;
import com.example.MNPSP.repository.FinalClearanceRepo;

@Service
public class FinalClearanceService {
    
    @Autowired
    private FinalClearanceRepo finalClearanceRepo;

    public void setDonorClearance(ValidationClearanceDTO clearanceDTO){
        String mobNum = clearanceDTO.getMobileNumber();
        FinalClearance finalClearance = finalClearanceRepo.findByMobileNumClearance(mobNum);
        if(finalClearance == null){
            FinalClearance finalDonorClearance = new FinalClearance();
            finalDonorClearance.setMobileNumber(mobNum);
            finalDonorClearance.setDeactivationClearance(true);
            finalClearanceRepo.save(finalDonorClearance);
        }
        return;
    }

    public void setRecipientClearance(ValidationClearanceDTO clearanceDTO){
        String mobNum = clearanceDTO.getMobileNumber();
        FinalClearance finalClearance = finalClearanceRepo.findByMobileNumClearance(mobNum);
        if(finalClearance != null){
            finalClearance.setActivationClearance(true);
            finalClearanceRepo.save(finalClearance);
        }
    }


}

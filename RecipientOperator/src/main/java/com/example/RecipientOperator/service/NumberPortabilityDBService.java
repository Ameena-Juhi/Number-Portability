package com.example.RecipientOperator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RecipientOperator.entity.CustomerAcquisitionForm;
import com.example.RecipientOperator.entity.NumberPortabilityDB;
import com.example.RecipientOperator.repository.CustomerAcquisitionFormRepository;
import com.example.RecipientOperator.repository.NumberPortabilityDBRepository;

@Service
public class NumberPortabilityDBService {
    
    @Autowired
    private NumberPortabilityDBRepository numDBRepository;

    @Autowired
    private CustomerAcquisitionFormRepository CAFRepository;

    private static final long TIME_THRESHOLD = 4 * 60 * 1000; // 4 minute

    public String  checkNPDB(CustomerAcquisitionForm form){
        NumberPortabilityDB numDB = numDBRepository.findByPortingNumber(form.getMobileNumber());
        if( numDB == null){
            NumberPortabilityDB newNumDB = new NumberPortabilityDB();
            newNumDB.setPortingNumber(form.getMobileNumber());
            newNumDB.setPending(true);
            numDBRepository.save(newNumDB);
            return "Request Accepted";
        }
        else{
             if(numDB.isPending())
                return "Request is pending !";

            else{
                long currentTime = System.currentTimeMillis();
                long portedAtTime = numDB.getPortedAt().getTime();
                if(currentTime - portedAtTime < TIME_THRESHOLD){
                    return "Request can't be processed further, elapsed time has not fullfilled!";
                }
                else{
                    numDB.setPending(true);
                    numDBRepository.save(numDB);
                    return "Request Accepted";
                }
            }
        }
    }
}

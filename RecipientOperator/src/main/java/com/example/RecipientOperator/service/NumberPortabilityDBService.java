package com.example.RecipientOperator.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RecipientOperator.DTO.MessageDTO;
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

    public MessageDTO  checkNPDB(Optional<CustomerAcquisitionForm> form){
        NumberPortabilityDB numDB = numDBRepository.findByPortingNumber(form.get().getMobileNumber());
        if( numDB == null){
            NumberPortabilityDB newNumDB = new NumberPortabilityDB();
            newNumDB.setPortingNumber(form.get().getMobileNumber());
            newNumDB.setPending(true);
            numDBRepository.save(newNumDB);
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setMessage("Request Accepted");
            return messageDTO;
        }
        else{
             if(numDB.isPending()){
                MessageDTO messageDTO = new MessageDTO();
                messageDTO.setMessage("Request is Pending!");
                return messageDTO;
             }

            else{
                long currentTime = System.currentTimeMillis();
                long portedAtTime = numDB.getPortedAt().getTime();
                if(currentTime - portedAtTime < TIME_THRESHOLD){
                     MessageDTO messageDTO = new MessageDTO();
                    messageDTO.setMessage("Request can't be processed further, elapsed time has not fullfilled!");
                    return messageDTO;
                }
                else{
                    numDB.setPending(true);
                    numDBRepository.save(numDB);
                    MessageDTO messageDTO = new MessageDTO();
                    messageDTO.setMessage("Request Accepted");
                    return messageDTO;
                }
            }
        }
    }
}

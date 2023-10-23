package com.example.MNPSP.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MNPSP.DTO.CAFdto;
import com.example.MNPSP.DTO.MessageDTO;
import com.example.MNPSP.DTO.PortingStatusDTO;
import com.example.MNPSP.DTO.ValidationClearanceDTO;
import com.example.MNPSP.entity.FinalClearance;
import com.example.MNPSP.entity.NumberPortabilityDB;
import com.example.MNPSP.repository.FinalClearanceRepo;
import com.example.MNPSP.repository.NumberPortabilityDBRepository;

@Service
public class NumberPortabilityDBService {

    @Autowired
    private NumberPortabilityDBRepository numDBRepository;

    @Autowired
    private FinalClearanceRepo finalClearanceRepo;

    private static final long TIME_THRESHOLD = 4 * 60 * 1000; // 4 minutes

    public MessageDTO checkNPDB(CAFdto form) {
        NumberPortabilityDB numDB = numDBRepository.findByPortingNumber(form.getMobileNumber());

        if (numDB == null) {
            // If the record does not exist, create a new one.
            NumberPortabilityDB newNumDB = new NumberPortabilityDB();
            newNumDB.setPortingNumber(form.getMobileNumber());
            newNumDB.setPending(true);
            numDBRepository.save(newNumDB);
            return createResponse("Request Accepted");
        } else {
            if (numDB.isPending()) {
                return createResponse("Request is Pending!");
            } else {
                return checkPortedInfo(numDB);
            }
        }
    }

    private MessageDTO createResponse(String message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setMessage(message);
        return messageDTO;
    }

    private MessageDTO checkPortedInfo(NumberPortabilityDB numDB) {
        long currentTime = System.currentTimeMillis();
        long portedAtTime = numDB.getPortedAt().getTime();
        if (currentTime - portedAtTime < TIME_THRESHOLD) {
            return createResponse("Request can't be processed further, elapsed time has not been fulfilled!");
        } else {
            numDB.setPending(true);
            numDBRepository.save(numDB);
            return createResponse("Request Accepted");
        }
    }

    public List<PortingStatusDTO> allStatus() {
        List<NumberPortabilityDB> numbersPorting = numDBRepository.findAll();
        List<PortingStatusDTO> portingStatusDTOs = new ArrayList<>();
        for (NumberPortabilityDB number : numbersPorting) {
            PortingStatusDTO portingStatusDTO = new PortingStatusDTO();
            portingStatusDTO.setMobileNumber(number.getPortingNumber());
            portingStatusDTO.setPending(number.isPending());
            portingStatusDTO.setPorted(number.isPorted());
            portingStatusDTOs.add(portingStatusDTO);
        }
        return portingStatusDTOs;
    }

    public void storeDonorClearance(ValidationClearanceDTO validationClearanceDTO){
        NumberPortabilityDB numberPortabilityDB = numDBRepository.findByPortingNumber(validationClearanceDTO.getMobileNumber());
        if(numberPortabilityDB!=null){
            numberPortabilityDB.setClearance(validationClearanceDTO.isValidationClearance());
            numDBRepository.save(numberPortabilityDB);
        }
        return;
    }

    public LocalDateTime schedulePortDateTime(MessageDTO mobNum){
        NumberPortabilityDB numberPortabilityDB = numDBRepository.findByPortingNumber(mobNum.getMessage());
        boolean clearance = numberPortabilityDB.isClearance();
            if (clearance) {
                // Get the current time
                LocalDateTime currentTime = LocalDateTime.now();
    
                // Add 1 minute to the current time
                LocalDateTime scheduledTime = currentTime.plusMinutes(1);
    
                // Scheduled time is 1 minute ahead of the current time
                return scheduledTime;
            }
            // Return null if validationClearance is false
            return null;
        }

        public void updatePortabilityDB(MessageDTO portingNumber) {
            String mobNum = portingNumber.getMessage();
            NumberPortabilityDB portabilityDB = numDBRepository.findByPortingNumber(mobNum);
            FinalClearance finalClearance = finalClearanceRepo.findByMobileNumClearance(mobNum);
            if (finalClearance.isActivationClearance() && finalClearance.isDeactivationClearance()) {
                portabilityDB.setPending(false);
                portabilityDB.setPorted(true);
                portabilityDB.setPortedAt(new Date());
                numDBRepository.save(portabilityDB);
            }
        }
    
}

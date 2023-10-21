package com.example.MNPSP.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MNPSP.DTO.CAFdto;
import com.example.MNPSP.DTO.MessageDTO;
import com.example.MNPSP.DTO.PortingStatusDTO;
import com.example.MNPSP.entity.NumberPortabilityDB;
import com.example.MNPSP.repository.NumberPortabilityDBRepository;

@Service
public class NumberPortabilityDBService {

    @Autowired
    private NumberPortabilityDBRepository numDBRepository;

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
}

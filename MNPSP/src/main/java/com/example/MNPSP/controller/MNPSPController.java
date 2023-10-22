package com.example.MNPSP.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.List;

import com.example.MNPSP.DTO.CAFdto;
import com.example.MNPSP.DTO.MessageDTO;
import com.example.MNPSP.DTO.PortingStatusDTO;
import com.example.MNPSP.DTO.ValidationClearanceDTO;
import com.example.MNPSP.feignClient.forwardcaf;
import com.example.MNPSP.service.NumberPortabilityDBService;

@CrossOrigin
@RestController
@RequestMapping("/mnpsp")
public class MNPSPController {

    @Autowired
    private NumberPortabilityDBService numPortDBService;

    @Autowired
    private forwardcaf forwardform;

    @PostMapping("/sendcaf")
    public MessageDTO validateCAF(@RequestBody CAFdto caf) {
        if (caf != null) {
            return numPortDBService.checkNPDB(caf);
        } else {
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setMessage("No form found.");
            return messageDTO;
        }
    }

    @PostMapping("/processform")
    public void processForm(@RequestBody CAFdto form) {
        forwardform.sendCAFToDonor(form);
    }

    @PostMapping("/storeClearance")
    public void storeClearance(@RequestBody ValidationClearanceDTO validationClearanceDTO) {
        numPortDBService.storeDonorClearance(validationClearanceDTO);
    }

    @PostMapping("/scheduleport")
    public LocalDateTime schedulePortTime(@RequestBody MessageDTO mobNum) {
        return numPortDBService.schedulePortDateTime(mobNum);
    }

    @GetMapping("/all")
    public List<PortingStatusDTO> getallStatus() {
        return numPortDBService.allStatus();
    }

}

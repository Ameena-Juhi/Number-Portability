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
import com.example.MNPSP.service.NumberPortabilityDBService;

@CrossOrigin
@RestController
@RequestMapping("/mnpsp")
public class MNPSPController {

    @Autowired
    private NumberPortabilityDBService numPortDBService;

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

    @PostMapping("/scheduleport")
    public LocalDateTime schedulePortTime(@RequestParam("validationClearance") boolean validationClearance) {
        if (validationClearance) {
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

    @GetMapping("/all")
    public List<PortingStatusDTO> getallStatus() {
        return numPortDBService.allStatus();
    }

}

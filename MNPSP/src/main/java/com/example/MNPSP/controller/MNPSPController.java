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
import com.example.MNPSP.service.CancelRequestService;
import com.example.MNPSP.service.FinalClearanceService;
import com.example.MNPSP.service.NumberPortabilityDBService;

@CrossOrigin
@RestController
@RequestMapping("/mnpsp")
public class MNPSPController {

    @Autowired
    private NumberPortabilityDBService numPortDBService;

    @Autowired
    private forwardcaf forwardform;

    @Autowired
    private FinalClearanceService finalClearanceService;

    @Autowired
    private CancelRequestService cancelRequestService;

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

    @PostMapping("/deactivationClearance")
    public void setDeactivationClearance(@RequestBody ValidationClearanceDTO deactivationClearance){
        finalClearanceService.setDonorClearance(deactivationClearance);
    }

    @PostMapping("/activationClearance")
    public void setActivationClearance(@RequestBody ValidationClearanceDTO activationClearance){
        finalClearanceService.setRecipientClearance(activationClearance);
    }

    @PostMapping("/updateNumDb")
    public void updateNumDB(@RequestBody MessageDTO MobileNumber){
        System.out.println("i am called");
        numPortDBService.updatePortabilityDB(MobileNumber);
    }

    @PostMapping
    private MessageDTO cancelRequest(@RequestBody MessageDTO mobileNumber){
        return cancelRequestService.processCancellation(mobileNumber);
    }

}

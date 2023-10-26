package com.example.MNPSP.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.List;
import com.example.MNPSP.DTO.CAFdto;
import com.example.MNPSP.DTO.CAFtoken;
import com.example.MNPSP.DTO.MessageDTO;
import com.example.MNPSP.DTO.PortingStatusDTO;
import com.example.MNPSP.DTO.ValidationClearanceDTO;
import com.example.MNPSP.feignClient.ROActivationClient;
import com.example.MNPSP.feignClient.forwardcaf;
import com.example.MNPSP.service.CancelRequestService;
import com.example.MNPSP.service.FinalClearanceService;
import com.example.MNPSP.service.NumberPortabilityDBService;

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

    @Autowired
    private ROActivationClient activationClient;

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
    public boolean processForm(@RequestHeader(value = "Authorization", required = true) String authorizationHeader,
            @RequestBody CAFtoken form) {
        System.out.println("processform");
        return this.forwardform.sendCAFToDonor(authorizationHeader, form);

    }

    @PostMapping("/storeIdentityClearance")
    public boolean storeIdentityClearance(
            @RequestHeader(value = "Authorization", required = true) String authorizationHeader,
            @RequestBody ValidationClearanceDTO IdentityClearance) {

        boolean clearance = numPortDBService.storeIdentityClearance(IdentityClearance);
        ValidationClearanceDTO clearancedto = new ValidationClearanceDTO();
        clearancedto.setMobileNumber(IdentityClearance.getMobileNumber());
        clearancedto.setValidationClearance(clearance);
        this.activationClient.storeIdentityClearance(authorizationHeader, clearancedto);
        return clearance;
    }

    @PostMapping("/storeClearance")
    public void storeClearance(@RequestHeader(value = "Authorization", required = true) String authorizationHeader,
            @RequestBody ValidationClearanceDTO validationClearanceDTO) {
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
    public void setDeactivationClearance(
            @RequestHeader(value = "Authorization", required = true) String authorizationHeader,
            @RequestBody ValidationClearanceDTO deactivationClearance) {
        finalClearanceService.setDonorClearance(deactivationClearance);
    }

    @PostMapping("/activationClearance")
    public void setActivationClearance(
            @RequestHeader(value = "Authorization", required = true) String authorizationHeader,
            @RequestBody ValidationClearanceDTO activationClearance) {
        finalClearanceService.setRecipientClearance(activationClearance);
    }

    @PostMapping("/updateNumDb")
    public boolean updateNumDB(@RequestHeader(value = "Authorization", required = true) String authorizationHeader,
            @RequestBody MessageDTO MobileNumber) {
        return numPortDBService.updatePortabilityDB(MobileNumber);
    }

    @PostMapping("/cancelMNPSP")
    private MessageDTO cancelRequest(@RequestBody MessageDTO mobileNumber) {
        return cancelRequestService.processCancellation(mobileNumber);
    }

    @PostMapping("/getClearance")
    public boolean getDonorClearance(@RequestBody MessageDTO mobileNum) {
        return this.numPortDBService.getClearance(mobileNum);
    }

}

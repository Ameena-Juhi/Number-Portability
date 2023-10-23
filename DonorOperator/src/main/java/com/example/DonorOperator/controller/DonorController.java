package com.example.DonorOperator.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DonorOperator.DTO.ActivationRequestDTO;
import com.example.DonorOperator.DTO.CAFdto;
import com.example.DonorOperator.DTO.MessageDTO;
import com.example.DonorOperator.DTO.SubDetailsDTO;
import com.example.DonorOperator.DTO.ValidationClearanceDTO;
import com.example.DonorOperator.FeignClient.ClearanceClient;
import com.example.DonorOperator.entity.DeactivationRequest;
import com.example.DonorOperator.entity.ForwardedRequests;
import com.example.DonorOperator.entity.MobileNumber;
import com.example.DonorOperator.exception.ResourceNotFoundException;
import com.example.DonorOperator.repository.MobileNumberRepository;
import com.example.DonorOperator.service.CancelRequestService;
import com.example.DonorOperator.service.DeactivationService;
import com.example.DonorOperator.service.ForwardedReqService;
import com.example.DonorOperator.service.MobileNumberService;
import com.example.DonorOperator.service.PortingVerificationService;
import com.example.DonorOperator.service.SubscriberDetailsService;

@RestController
@CrossOrigin(origins = { "http://localhost:53840/", "http://localhost:4200" })
@RequestMapping("/operator")
public class DonorController {

    @Autowired
    private MobileNumberRepository mobileNumberRepository;

    @Autowired
    private MobileNumberService mobileNumberService;

    @Autowired
    private PortingVerificationService portingVerificationService;

    @Autowired
    private ForwardedReqService forwardedReqService;

    @Autowired
    private ClearanceClient clearanceClient;

    @Autowired
    private DeactivationService deactivationService;

    @Autowired
    private SubscriberDetailsService subscriberDetailsService;

    @Autowired
    private CancelRequestService cancelRequestService;

    @GetMapping("/get")
    public List<MobileNumber> getMobNums() {
        return mobileNumberRepository.findAll();
    }

    @PostMapping("/port")
    public MessageDTO portingSMS(@RequestBody MessageDTO sms) throws ResourceNotFoundException {
        return mobileNumberService.retrieveMobileNumber(sms);
    }

    @PostMapping("/passedcaf")
    public MessageDTO sendCAFToDonor(@RequestBody CAFdto Form) {
        MessageDTO msg = new MessageDTO();
        forwardedReqService.saveCAFDTO(Form);
        msg.setMessage("Forwarded the request successfully!");
        return msg;
    }

    @GetMapping("/getRequests")
    public List<ForwardedRequests> getAllRequestsTillNow() {
        return forwardedReqService.getAllRequests();
    }

    @PostMapping("/validate")
    public boolean respondToPortingRequest(@RequestBody CAFdto form) throws ResourceNotFoundException {
        boolean clearance;
        List<String> errorMessages = new ArrayList<>();

        if (!portingVerificationService.checkActivationPeriod(form.getMobileNumber())) {
            errorMessages.add("Activation period check failed.");
        }

        if (!portingVerificationService.checkOutstandingPayments(form.getMobileNumber())) {
            errorMessages.add("Outstanding payments check failed.");
        }

        if (!portingVerificationService.checkUPCMismatch(form)) {
            errorMessages.add("UPC mismatch check failed.");
        }

        if (!portingVerificationService.checkUPCValidity(form.getMobileNumber())) {
            errorMessages.add("UPC validity check failed.");
        }

        if (!portingVerificationService.checkChangeOfOwnership(form.getMobileNumber())) {
            errorMessages.add("Change of ownership check failed.");
        }

        if (!errorMessages.isEmpty()) {
            System.out.println("Errors: " + errorMessages); // Print error messages
            clearance = false;
            return clearance;
        }
        clearance = true;
        ValidationClearanceDTO clearancedto = new ValidationClearanceDTO();
        clearancedto.setMobileNumber(form.getMobileNumber());
        clearancedto.setValidationClearance(clearance);
        this.clearanceClient.storeClearance(clearancedto);

        return clearance;
    }

    @GetMapping("/allDeactReqs")
    public List<DeactivationRequest> getAllDeactReqs() {
        return deactivationService.getAllDeactReqs();
    }

    @PostMapping("/saveRequest")
    public void saveDeactReq(@RequestBody ActivationRequestDTO activationRequestDTO) {
        deactivationService.saveDeactReqs(activationRequestDTO);
    }

    @PostMapping("/deactivation")
    public MessageDTO deactivateMobileNumber(@RequestBody ActivationRequestDTO deactivationRequest) {
        return deactivationService.acceptDeactivation(deactivationRequest);
    }

    @GetMapping("/getSubscribers")
    public List<SubDetailsDTO> getAllSubDetails() {
        return subscriberDetailsService.getAllDetails();
    }
//afterForward Request before SchedulePortTime
    @PostMapping("/cancelDO")
    public MessageDTO cancelRequest(@RequestBody MessageDTO mobileNumber){
        return cancelRequestService.processCancellation(mobileNumber);
    }

}

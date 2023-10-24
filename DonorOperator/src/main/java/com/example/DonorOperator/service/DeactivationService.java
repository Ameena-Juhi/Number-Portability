package com.example.DonorOperator.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DonorOperator.DTO.ActivationRequestDTO;
import com.example.DonorOperator.DTO.MessageDTO;
import com.example.DonorOperator.DTO.ValidationClearanceDTO;
import com.example.DonorOperator.FeignClient.ClearanceClient;
import com.example.DonorOperator.entity.DeactivationRequest;
import com.example.DonorOperator.entity.MobileNumber;
import com.example.DonorOperator.entity.NumbersPorting;
import com.example.DonorOperator.entity.SubscriberDetails;
import com.example.DonorOperator.repository.DeactivationReqRepo;
import com.example.DonorOperator.repository.MobileNumberRepository;
import com.example.DonorOperator.repository.NumbersPortingRepository;
import com.example.DonorOperator.repository.SubscriberDetailsRepository;

@Service
public class DeactivationService {

    @Autowired
    private MobileNumberRepository mobileNumberRepository;

    @Autowired
    private SubscriberDetailsRepository subscriberDetailsRepository;

    @Autowired
    private NumbersPortingRepository numbersPortingRepository;

    @Autowired
    private ClearanceClient clearanceClient;

    @Autowired
    private DeactivationReqRepo deactivationReqRepo;

    public List<DeactivationRequest> getAllDeactReqs() {
        return deactivationReqRepo.findAll();
    }

    public MessageDTO acceptDeactivation(ActivationRequestDTO deactivationRequest) {
        MessageDTO messageDTO = new MessageDTO();
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime scheduledTime = deactivationRequest.getActivationTime();

        if (currentDateTime.isAfter(scheduledTime)) {
            MobileNumber mobileEntry = mobileNumberRepository.findByMobileNumber(deactivationRequest.getMobileNumber())
                    .get();
            SubscriberDetails subscriberDetails = subscriberDetailsRepository.findbymobilenum_id(mobileEntry.getId());
            NumbersPorting numbersPortingEntry = numbersPortingRepository.findByMobileNumberId(mobileEntry.getId());
            ValidationClearanceDTO clearance = new ValidationClearanceDTO();
            clearance.setMobileNumber(mobileEntry.getMobileNumber());
            clearance.setValidationClearance(true);
            subscriberDetailsRepository.delete(subscriberDetails);
            numbersPortingRepository.delete(numbersPortingEntry);
            mobileNumberRepository.delete(mobileEntry);

            this.clearanceClient.setDeactivationClearance(clearance);
            messageDTO.setMessage("Mobile number deleted successfully."); // Set the appropriate success message
        } else {
            messageDTO.setMessage("Deactivation Time not yet reached!/Already deactivated your account");
        }
        return messageDTO;

    }

    public void saveDeactReqs(ActivationRequestDTO activationRequestDTO) {
        DeactivationRequest request = new DeactivationRequest();
        request.setMobileNumber(activationRequestDTO.getMobileNumber());
        request.setActivationTime(activationRequestDTO.getActivationTime());
        deactivationReqRepo.save(request);
    }
}

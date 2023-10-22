package com.example.DonorOperator.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DonorOperator.DTO.ActivationRequestDTO;
import com.example.DonorOperator.DTO.MessageDTO;
import com.example.DonorOperator.entity.DeactivateRequest;
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
    private DeactivationReqRepo deactivationReqRepo;

    public List<DeactivateRequest> getAllDeactReqs() {
        return deactivationReqRepo.findAll();
    }

    public MessageDTO acceptDeactivation(ActivationRequestDTO deactivationRequest) {
        MessageDTO messageDTO = new MessageDTO();
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime scheduledTime = deactivationRequest.getActivationTime();
        // Check if the current date and time are the same as the activation time

        if (currentDateTime.isAfter(scheduledTime)) {
            MobileNumber mobileEntry = mobileNumberRepository.findByMobileNumber(deactivationRequest.getMobileNumber());
            SubscriberDetails subscriberDetails = subscriberDetailsRepository.findbymobilenum_id(mobileEntry.getId());
            NumbersPorting numbersPortingEntry = numbersPortingRepository.findByMobileNumberId(mobileEntry.getId());
            subscriberDetailsRepository.delete(subscriberDetails);
            numbersPortingRepository.delete(numbersPortingEntry);
            mobileNumberRepository.delete(mobileEntry);

            messageDTO.setMessage("Mobile number deleted successfully."); // Set the appropriate success message
        } else {
            messageDTO.setMessage("Deactivation Time not yet reached!/Already deactivated your account");
        }
        return messageDTO;

    }

    public void saveDeactReqs(ActivationRequestDTO activationRequestDTO) {
        DeactivateRequest request = new DeactivateRequest();
        request.setMobileNumber(activationRequestDTO.getMobileNumber());
        request.setActivationTime(activationRequestDTO.getActivationTime());
        deactivationReqRepo.save(request);
    }
}

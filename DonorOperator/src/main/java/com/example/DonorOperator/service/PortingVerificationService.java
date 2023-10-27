package com.example.DonorOperator.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DonorOperator.DTO.CAFdto;
import com.example.DonorOperator.entity.MobileNumber;
import com.example.DonorOperator.entity.NumbersPorting;
import com.example.DonorOperator.entity.SubscriberDetails;
import com.example.DonorOperator.exception.ResourceNotFoundException;
import com.example.DonorOperator.repository.MobileNumberRepository;
import com.example.DonorOperator.repository.NumbersPortingRepository;
import com.example.DonorOperator.repository.SubscriberDetailsRepository;

@Service
public class PortingVerificationService {

    @Autowired
    private NumbersPortingRepository numbersPortingRepository;

    @Autowired
    private MobileNumberRepository mobileNumberRepository;

    @Autowired
    private SubscriberDetailsRepository subscriberDetailsRepository;

    public NumbersPorting getPortingRequest(String mobNum) throws ResourceNotFoundException {
        if (mobileNumberRepository.findByMobileNumber(mobNum).isPresent()) {

            Long mobileNum_id = mobileNumberRepository.findByMobileNumber(mobNum).get().getId();
            return (numbersPortingRepository.findByMobileNumberId(mobileNum_id));
        }
        throw new ResourceNotFoundException(mobNum + "is not found in DB");
    }

    public SubscriberDetails getSubscriberDetails(String mobNum) throws ResourceNotFoundException {
        Optional<MobileNumber> optionalMobileNumber = mobileNumberRepository.findByMobileNumber(mobNum);
        if (optionalMobileNumber.isPresent()) {
            Long mobileNum_id = optionalMobileNumber.get().getId();
            return subscriberDetailsRepository.findbymobilenum_id(mobileNum_id);
        }
        throw new ResourceNotFoundException(mobNum + " is not found in DB");
    }

    public boolean checkOutstandingPayments(String mobNum) throws ResourceNotFoundException {

        SubscriberDetails subscriber = this.getSubscriberDetails(mobNum);
        return subscriber.isBilling_clearance();
    }

    public boolean checkActivationPeriod(String mobNum) throws ResourceNotFoundException {
        // Check if 90 days in this context assuming 9 minutes have elapsed from the
        // activation date.
        SubscriberDetails subscriber = this.getSubscriberDetails(mobNum);
        Date activationDate = subscriber.getPortedInDate();
        Date currentdate = new Date();
        Long timedifference = (currentdate.getTime() - activationDate.getTime()) / (60 * 1000);
        return timedifference >= 9;
    }

    public boolean checkUPCMismatch(CAFdto form) throws ResourceNotFoundException {

        NumbersPorting portingRequest = this.getPortingRequest(form.getMobileNumber());
        String storedUPC = portingRequest.getUpc();
        String fetchedUPC = form.getUpc();
        return fetchedUPC.equals(storedUPC);
    }

    public boolean checkChangeOfOwnership(String mobNum) throws ResourceNotFoundException {

        return (this.getPortingRequest(mobNum) != null);
    }

    public boolean checkUPCValidity(String mobNum) throws ResourceNotFoundException {

        NumbersPorting portingRequest = this.getPortingRequest(mobNum);
        Date upcGenDate = portingRequest.getRequestedUpcTime();
        Date currentdate = new Date();
        Long timedifference = (currentdate.getTime() - upcGenDate.getTime()) / (60 * 1000);
        return timedifference <= 40; // 4 days ago equivalent 40 mins(asssumption)
    }

}
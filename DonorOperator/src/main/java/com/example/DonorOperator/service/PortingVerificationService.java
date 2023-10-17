package com.example.DonorOperator.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DonorOperator.entity.NumbersPorting;
import com.example.DonorOperator.entity.SubscriberDetails;
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

    public NumbersPorting getPortingRequest(String mobNum) throws Exception{
        if(mobileNumberRepository.findByMobileNumber(mobNum) != null){
            Long mobileNum_id = mobileNumberRepository.findByMobileNumber(mobNum).getId();
            return(numbersPortingRepository.findByMobileNumberId(mobileNum_id));
        }
        throw new Exception(mobNum + "is not found in DB");
    }

    public SubscriberDetails getSubscriberDetails(String mobNum) throws Exception{
        if(mobileNumberRepository.findByMobileNumber(mobNum) != null){
            Long mobileNum_id = mobileNumberRepository.findByMobileNumber(mobNum).getId();
            return(subscriberDetailsRepository.findbymobilenum_id(mobileNum_id));
        }
        throw new Exception(mobNum + "is not found in DB");
    }

    public boolean checkOutstandingPayments(String mobNum) throws Exception {
            SubscriberDetails subscriber = this.getSubscriberDetails(mobNum);
            return !subscriber.isBilling_clearance();
    }

    public boolean checkActivationPeriod(String mobNum) throws Exception {
        // Check if 90 days in this context assuming 9 minutes have elapsed from the activation date.
            SubscriberDetails subscriber = this.getSubscriberDetails(mobNum);
            Date activationDate = subscriber.getPortedInDate();
            Date currentdate = new Date();
            Long timedifference = (currentdate.getTime() - activationDate.getTime())/(60*1000);
            return timedifference>=9;
    }

    public boolean checkUPCMismatch(String mobNum, String upc) throws Exception {
    // Implement logic to check if UPC in the request matches the one associated with the mobile number.

        NumbersPorting portingRequest = this.getPortingRequest(mobNum);
        String storedUPC = portingRequest.getUpc();
        return upc.equals(storedUPC);
    }

    public boolean checkChangeOfOwnership(String mobNum) throws Exception {
        return(this.getPortingRequest(mobNum)!=null);
    }

    public boolean checkUPCValidity(String mobNum, String upc) throws Exception {
            NumbersPorting portingRequest = this.getPortingRequest(mobNum);
            Date upcGenDate = portingRequest.getRequestedUpcTime();
            Date currentdate = new Date();
            Long timedifference = (currentdate.getTime() - upcGenDate.getTime())/(60*1000);
            return timedifference<=4; //4 days ago equivalent 4 mins(asssumption) 
    }

    

    // public boolean checkSubJudice(SubscriberDetails subscriber) {
    //     // Implement logic to check if the mobile number is sub-judice.
    //     // Return true if it's not sub-judice.
    //     return /* your logic here */;
    // }

    // public boolean checkProhibitedByCourt(SubscriberDetails subscriber) {
    //     // Implement logic to check if the number is prohibited by a court of law.
    //     // Return true if it's not prohibited.
    //     return /* your logic here */;
    // }


    // public boolean checkContractualObligation(SubscriberDetails subscriber) {
    //     // Implement logic to check if contractual obligations are cleared.
    //     // Return true if obligations are cleared.
    //     return /* your logic here */;
    // }

    // public boolean checkAuthorizationLetter(NumbersPorting portingRequest) {
    //     // Implement logic to check if an authorization letter is provided for corporate numbers.
    //     // Return true if an authorization letter is provided.
    //     return /* your logic here */;
    // }

    
}
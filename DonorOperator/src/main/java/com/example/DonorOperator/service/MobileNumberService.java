package com.example.DonorOperator.service;

import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DonorOperator.entity.MobileNumber;
import com.example.DonorOperator.entity.NumbersPorting;
import com.example.DonorOperator.exception.ResourceNotFoundException;
import com.example.DonorOperator.repository.MobileNumberRepository;
import com.example.DonorOperator.repository.NumbersPortingRepository;

@Service
public class MobileNumberService {

    @Autowired
    private MobileNumberRepository mobileNumberRepository;

    @Autowired
    private NumbersPortingRepository numbersPortingRepository;

    public String retrieveMobileNumber(String SMS) throws ResourceNotFoundException {
        Pattern pattern = Pattern.compile("\\d{10}");
        Matcher matcher = pattern.matcher(SMS);
        String mobileNumber = "";

        while (matcher.find()) {
            mobileNumber = matcher.group();
        }

        if (!mobileNumber.isEmpty()) {
            MobileNumber existingMobileNumber = mobileNumberRepository.findByMobileNumber(mobileNumber);
            NumbersPorting portingNumber = numbersPortingRepository.findByMobileNumberId(existingMobileNumber.getId());
            if (portingNumber != null) {
                Date upcGenDate = portingNumber.getRequestedUpcTime();
                Date currentdate = new Date();
                Long timedifference = (currentdate.getTime() - upcGenDate.getTime()) / (60 * 1000);
                if (timedifference >= 40) {
                    String upc = generateUniquePortingCode();
                    portingNumber.setUpc(upc);
                    portingNumber.setRequestedUpcTime(new Date());
                    numbersPortingRepository.save(portingNumber);
                    return upc;
                }
                return ("Already generated UPC for this number!");
            }
            if (existingMobileNumber != null) {

                NumbersPorting numsPorting = new NumbersPorting();
                numsPorting.setMobileNumber(existingMobileNumber);
                String uniquePortingCode = generateUniquePortingCode();
                numsPorting.setUpc(uniquePortingCode);
                numsPorting.setRequestedUpcTime(new Date());
                numbersPortingRepository.save(numsPorting);
                return uniquePortingCode;
            }
            // } else {

            // throw new ResourceNotFoundException(mobileNumber + " does not exist in the
            // Database");
            // }
        }

        return "No valid mobile number found in the SMS.";
    }

    private String generateUniquePortingCode() {
        Random random = new Random();
        int min = 10000000;
        int max = 99999999;

        int randomNumber = random.nextInt(max - min + 1) + min;

        return String.format("%08d", randomNumber);
    }
}

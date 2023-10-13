package com.example.DonorOperator.service;

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

            if (existingMobileNumber != null) {

                NumbersPorting numsPorting = new NumbersPorting();
                numsPorting.setMobileNumber(existingMobileNumber);
                String uniquePortingCode = generateUniquePortingCode();
                numsPorting.setUpc(uniquePortingCode);
                numbersPortingRepository.save(numsPorting);
                return uniquePortingCode;
                
            } else {
                
                throw new ResourceNotFoundException(mobileNumber + " does not exist in the Database");
            }
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

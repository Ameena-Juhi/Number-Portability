package com.example.DonorOperator.service;

import java.util.Date;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DonorOperator.DTO.MessageDTO;
import com.example.DonorOperator.entity.MobileNumber;
import com.example.DonorOperator.entity.NumbersPorting;
import com.example.DonorOperator.exception.ResourceNotFoundException;
import com.example.DonorOperator.repository.MobileNumberRepository;
import com.example.DonorOperator.repository.NumbersPortingRepository;

@Service
public class MobileNumberService {
    Random random = new Random();
    @Autowired
    private MobileNumberRepository mobileNumberRepository;

    @Autowired
    private NumbersPortingRepository numbersPortingRepository;

    public MessageDTO retrieveMobileNumber(MessageDTO SMS) throws ResourceNotFoundException {

        MessageDTO msg = new MessageDTO();
        String sms = SMS.getMessage();
        boolean smsFormat = this.validateSMS(sms);
        if (smsFormat) {
            Pattern pattern = Pattern.compile("\\d{10}");
            Matcher matcher = pattern.matcher(sms);
            String mobileNumber = "";

            while (matcher.find()) {
                mobileNumber = matcher.group();
            }

            if (!mobileNumber.isEmpty()) {
                Optional<MobileNumber> optionalMobileNumber = mobileNumberRepository.findByMobileNumber(mobileNumber);
                if (!optionalMobileNumber.isPresent()) {
                    throw new ResourceNotFoundException("Resource Not Found Exception " + mobileNumber);
                }
                MobileNumber existingMobileNumber = optionalMobileNumber.get();
                NumbersPorting portingNumber = numbersPortingRepository
                        .findByMobileNumberId(existingMobileNumber.getId());
                if (portingNumber != null) {
                    Date upcGenDate = portingNumber.getRequestedUpcTime();
                    Date currentDate = new Date();
                    if (upcGenDate != null) {
                        long timeDifference = (currentDate.getTime() - upcGenDate.getTime()) / (60 * 1000);
                        if (timeDifference >= 40) {
                            String upc = generateUniquePortingCode();
                            portingNumber.setUpc(upc);
                            portingNumber.setRequestedUpcTime(new Date());
                            numbersPortingRepository.save(portingNumber);
                            msg.setMessage(upc);
                        } else {
                            msg.setMessage("Already generated UPC for this number!");
                        }
                    } else {
                        msg.setMessage("UpcGenDate is null!");
                    }
                } else {
                    createNewPorting(existingMobileNumber, msg);
                }
            }
        } else {
            msg.setMessage("Not a valid SMS.");
        }

        return msg;
    }

    private String generateUniquePortingCode() {
        int min = 10000000;
        int max = 99999999;
        int randomNumber = random.nextInt(max - min + 1) + min;
        return String.format("%08d", randomNumber);
    }

    public void createNewPorting(MobileNumber existingMobileNumber, MessageDTO msg) {
        NumbersPorting numsPorting = new NumbersPorting();
        numsPorting.setMobileNumber(existingMobileNumber);
        String uniquePortingCode = generateUniquePortingCode();
        numsPorting.setUpc(uniquePortingCode);
        numsPorting.setRequestedUpcTime(new Date());
        numbersPortingRepository.save(numsPorting);
        msg.setMessage(uniquePortingCode);
    }

    public boolean validateSMS(String sms) {
        String regex = "(?i)^port\\s\\d{10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sms);
        return matcher.matches();
    }

}
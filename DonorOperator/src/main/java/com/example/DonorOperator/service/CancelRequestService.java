package com.example.DonorOperator.service;

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
public class CancelRequestService {
    @Autowired
    private MobileNumberRepository mobileNumberRepository;

    @Autowired
    private NumbersPortingRepository numbersPortingRepository;

    public MessageDTO cancelRequest(MessageDTO SMS) throws ResourceNotFoundException {

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
                MobileNumber mobileNum = mobileNumberRepository.findByMobileNumber(mobileNumber).get();
                NumbersPorting portingNumber = numbersPortingRepository.findByMobileNumberId(mobileNum.getId());
                if (portingNumber == null) {
                    throw new ResourceNotFoundException("Resource Not Found Exception " +
                            mobileNumber);
                } else {
                    numbersPortingRepository.deleteById(portingNumber.getId());
                    msg.setMessage("Succesfully cancelled!");
                    return msg;
                }
            }

        }
        msg.setMessage("SMS is not Valid!");
        return msg;
    }

    public boolean validateSMS(String sms) {
        String regex = "(?i)^cancel\\s\\d{10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sms);
        return matcher.matches();
    }
}

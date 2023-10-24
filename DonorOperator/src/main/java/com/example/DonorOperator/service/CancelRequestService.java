// package com.example.DonorOperator.service;

// import java.util.regex.Matcher;
// import java.util.regex.Pattern;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.example.DonorOperator.DTO.MessageDTO;
// import com.example.DonorOperator.entity.NumbersPorting;
// import com.example.DonorOperator.exception.ResourceNotFoundException;
// import com.example.DonorOperator.repository.MobileNumberRepository;
// import com.example.DonorOperator.repository.NumbersPortingRepository;

// @Service
// public void cancelRequestService(MessageDTO SMS) {

// // MessageDTO msg = new MessageDTO();
// // String sms = SMS.getMessage();
// // boolean smsFormat = this.validateSMS(sms);
// // if(smsFormat){
// // Pattern pattern = Pattern.compile("\\d{10}");
// // Matcher matcher = pattern.matcher(sms);
// // String mobileNumber = "";

// // while (matcher.find()) {
// // mobileNumber = matcher.group();
// // }

// // if (!mobileNumber.isEmpty()) {
// // CustomerAcquisitionForm form =
// customerAcquisitionFormRepository.findByMobileNumber(mobileNumber).get();
// // if (form == null) {
// // throw new ResourceNotFoundException("Resource Not Found Exception " +
// mobileNumber);
// // }
// // else{
// // customerAcquisitionFormRepository.deleteById(
// //
// customerAcquisitionFormRepository.findByMobileNumber(mobileNumber).get().getId());

// // msg.setMessage("Succesfully cancelled!");
// // return msg;
// // }
// // }
// // msg.setMessage("SMS is not Valid!");
// // }return msg;
// // }

// // public boolean validateSMS(String sms) {
// // String regex = "^Cancel\\s\\d{10}$";
// // Pattern pattern = Pattern.compile(regex);
// // Matcher matcher = pattern.matcher(sms);
// // return matcher.matches();
// // }
// // }

// }

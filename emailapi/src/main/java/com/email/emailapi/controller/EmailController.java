package com.email.emailapi.controller;

import com.email.emailapi.Service.EmailService;
import com.email.emailapi.model.EmailOtp;
import com.email.emailapi.model.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @RequestMapping("/welcome")
    public String welcome(){
        return "Welcome to email service";
    }

    @PostMapping("/sendmail")
    public String sendEmail(@RequestBody EmailRequest emailRequest){
        boolean f = emailService.sendEmail(emailRequest.getTo(),emailRequest.getSubject(),emailRequest.getMessage());
        if(f) {
            return "otp verified sent Successfully";
        }else{
            return "something went wrong";
        }
    }
    @GetMapping("/forget-password")
    public String forgetPassword(){

        return "forgetpassword";
    }

    @PostMapping("/send-email-verification")
    public String sendEmailVerify(@RequestBody Map<String,String> map){
        System.out.println("sendEmailVerify called");
        boolean flag = emailService.sendEmailVerificationOtp(map.get("email"));
        if(flag){
            System.out.println("Email sent Successfully");
             return "Email sent Successfully";
        }
        return "something went wrong";
    }
    @PostMapping("/verify-otp")
    public String verifyEmail(@RequestBody EmailOtp emailOtp){
        boolean flag = emailService.verifyOtp(emailOtp.getEmail(),emailOtp.getOtp());
        if(flag){
            return "Otp verified Successfully";
        }
        return "Otp not invalid";
    }
}
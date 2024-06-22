package org.example;

import org.example.mail.GMailSender;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        GMailSender gmailSender = new GMailSender();
        boolean mail = gmailSender.sendEmail("sahukartik26@gmail.com","kartiksahu81713@gmail.com","Test email","Hello this is test email, don't reply back");
        if(mail) {
            System.out.println("Email sent successfully");
        }else{
            System.out.println("Something went wrong");
        }

    }
}
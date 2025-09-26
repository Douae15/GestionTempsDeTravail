package com.employee.back_gestionTempsDeTravail.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendReminderEmail(String to, String subject, String message){
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(message, true);
            javaMailSender.send(mimeMessage);
            System.out.println("Email sent successfully.");
            
            
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Email no.");
        }
    }
}

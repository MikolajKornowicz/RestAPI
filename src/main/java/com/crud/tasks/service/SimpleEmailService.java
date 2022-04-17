package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleEmailService {

    private JavaMailSender javaMailSender;
    private Mail mail;

    public void send(final Mail mail) {
        log.info("Starting email preparation...");
        try {
            SimpleMailMessage mailMessage = createMailMessage(mail);
            javaMailSender.send(mailMessage);
            log.info("Email has been sent.");
        } catch (MailException e) {
            log.error("Failed to process email sending: " + e.getMessage(), e);
        }
    }

    private SimpleMailMessage createMailMessage(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getSendTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        if(mail.getToCC().length()>0){
            mailMessage.setCc(String.valueOf(mail.getToCC()));
        }
            return mailMessage;
    }
}

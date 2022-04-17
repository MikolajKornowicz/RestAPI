package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;


import static org.mockito.Mockito.times;

@ExtendWith({MockitoExtension.class})
class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void shouldSendEmail() {
        //given
        Mail mail =  Mail.builder()
                .sendTo("test@message.com")
                .subject("test subject")
                .message("test message")
                .build();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getSendTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());

        //when
        simpleEmailService.send(mail);

        //then
        verify(javaMailSender, times(1)).send(mailMessage);
    }

    @Test
    public void shouldSendEmailToCC() {
        //given
        Mail mail = Mail.builder()
                .sendTo("test@mail.com")
                .subject("Test subject")
                .message("Test message")
                .toCC("testCC@mail.com")
                .build();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getSendTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setCc(mail.getToCC());
        mailMessage.setText(mail.getMessage());

        //when
        simpleEmailService.send(mail);
        String cc = mail.getToCC();

        //then
        assertEquals("testCC@mail.com", cc);
    }
}
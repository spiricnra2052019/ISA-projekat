package com.ftn.isa.model;

import javax.mail.MessagingException;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootTest
public class EmailSenderTest {
    @Autowired
    private JavaMailSender javaMailSender;

    @Test
    public void testSendEmail() throws MessagingException {
        String toAddress = "jikah12023@semonir.com";
        String subject = "Test Email";
        String body = "This is a test email.";

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(toAddress);

        msg.setSubject(subject);
        msg.setText(body);
        javaMailSender.send(msg);

    }
}
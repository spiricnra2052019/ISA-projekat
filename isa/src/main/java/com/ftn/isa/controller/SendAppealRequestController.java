package com.ftn.isa.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class SendAppealRequestController {
	JavaMailSender sender;
	
	public SendAppealRequestController(JavaMailSender sender) {
		this.sender = sender;
	}
	
	
	@GetMapping("/response-appeal")
	public String sendMail() throws MessagingException, UnsupportedEncodingException, javax.mail.MessagingException{
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setSubject("Test email");
		helper.setText("Hello this is the test email");
		helper.setFrom("spiricn00@gmail.com");
		helper.setTo("maki.spiric@gmail.com");
		sender.send(message);
		return "mail send successfully";
	}

}







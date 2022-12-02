package com.ftn.isa.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ftn.isa.model.SendAppeal;
import com.ftn.isa.repository.SendAppealRepository;

@Service
public class SendAppealService {
	@Autowired
	private SendAppealRepository sendAppealRepository;
	@Autowired
	private JavaMailSender mailSender;
	
	
	public SendAppeal findOne(Long id) {
		return sendAppealRepository.findById(id).orElseGet(null);
	}
	
	public List<SendAppeal> findAll(){
		return sendAppealRepository.findAll();
	}
	
	public SendAppeal save(SendAppeal appeal) {
		return sendAppealRepository.save(appeal);
	}
	
	public void sendEmail(String to, String subject, String message) {
		
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom("spiricn00@gmail.com");
		simpleMailMessage.setTo(to);
		simpleMailMessage.setText(subject);
		simpleMailMessage.setSubject(message);
		
		this.mailSender.send(simpleMailMessage);
		System.out.println("Mail send");
	} 
	
	public void remove(Long id) {
		sendAppealRepository.deleteById(id);
	}
}

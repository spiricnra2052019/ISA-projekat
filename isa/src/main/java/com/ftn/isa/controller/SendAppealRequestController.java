package com.ftn.isa.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.isa.model.SendAppeal;
import com.ftn.isa.service.SendAppealService;



@RestController
public class SendAppealRequestController {
	@Autowired
	private SendAppealService sendAppealService;
	
	
	@PostMapping("/send-appeal-request")
	public ResponseEntity sendEmail(@RequestBody SendAppeal sendAppeal) {
	//	this.sendAppealService.sendEmail(String nes, String nesto, String nesto);
		return ResponseEntity.ok("Success");
	}

}







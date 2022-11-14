package com.ftn.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.isa.model.BloodCenter;
import com.ftn.isa.model.RegisteredUser;
import com.ftn.isa.repository.AddressRepository;
import com.ftn.isa.repository.BloodAmountRepository;
import com.ftn.isa.repository.BloodCenterRepository;

@Service
public class BloodCenterService {
	@Autowired
	private BloodCenterRepository bloodCenterRepository;
	
	@Autowired
	private BloodAmountRepository bloodAmountRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	public BloodCenter findOne(Long id) {
		return bloodCenterRepository.findById(id).orElseGet(null);
	}
	
	public List<BloodCenter> findAll(){
		return bloodCenterRepository.findAll();
	}
}
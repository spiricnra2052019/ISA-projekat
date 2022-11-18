package com.ftn.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.isa.model.Administrator;
import com.ftn.isa.model.BloodCenterAdministrator;
import com.ftn.isa.repository.BloodCenterAdministratorRepository;

@Service
public class BloodCenterAdministratorService {
	
	@Autowired
	private BloodCenterAdministratorRepository administratorRepository;
	
	public BloodCenterAdministrator findOne(Long id) {
		return administratorRepository.findById(id).orElseGet(null);
	}
	
	public List<BloodCenterAdministrator> findAll(){
		return administratorRepository.findAll();
	}
	
	public BloodCenterAdministrator save(BloodCenterAdministrator Administrator) {
		return administratorRepository.save(Administrator);
	}
	
	public void remove(Long id) {
		administratorRepository.deleteById(id);
	}
}

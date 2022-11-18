package com.ftn.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.isa.model.Address;
import com.ftn.isa.model.BloodAmount;
import com.ftn.isa.model.BloodCenter;
<<<<<<< Updated upstream
import com.ftn.isa.model.RegisteredUser;
=======
import com.ftn.isa.model.BloodCenterAdministrator;
>>>>>>> Stashed changes
import com.ftn.isa.repository.AddressRepository;
import com.ftn.isa.repository.BloodAmountRepository;
import com.ftn.isa.repository.BloodCenterAdministratorRepository;
import com.ftn.isa.repository.BloodCenterRepository;

@Service
public class BloodCenterService {
	@Autowired
	private BloodCenterRepository bloodCenterRepository;
	
	@Autowired
	private BloodAmountRepository bloodAmountRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private BloodCenterAdministratorRepository bloodCenterAdministratorRepository;
	
	public BloodCenter findOne(Long id) {
		return bloodCenterRepository.findById(id).orElseGet(null);
	}
	
	public List<BloodCenter> findAll(){
		return bloodCenterRepository.findAll();
	}

	public BloodCenter save(BloodCenter BloodCenter) {
		Address address = addressRepository.save(BloodCenter.getAddress());
		BloodAmount blood = bloodAmountRepository.save(BloodCenter.getBloodAmount());
		BloodCenterAdministrator bloodCenterAdministrator = bloodCenterAdministratorRepository.save(BloodCenter.getBloodCenterAdministrator());
		BloodCenter.setAddress(address);
		BloodCenter.setBloodAmount(blood);
		BloodCenter.setBloodCenterAdministrator(bloodCenterAdministrator);
		return bloodCenterRepository.save(BloodCenter);
	}
	
	public List<BloodCenter> searchCenters(String query){
		List<BloodCenter> bloodCenters = bloodCenterRepository.searchCenters(query);
		return bloodCenters;
	}
	
	public List<BloodCenter> filterCenters(String searchQuery, float filterQuery){
		List<BloodCenter> bloodCenters = bloodCenterRepository.filterCenters(searchQuery, filterQuery);
		return bloodCenters;
	}
}

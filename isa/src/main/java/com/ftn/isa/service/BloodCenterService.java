package com.ftn.isa.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ftn.isa.model.Address;
import com.ftn.isa.model.BloodAmount;
import com.ftn.isa.model.BloodCenter;
import com.ftn.isa.model.Employee;
import com.ftn.isa.model.RegisteredUser;
import com.ftn.isa.model.WorkingTime;
import com.ftn.isa.model.BloodCenterAdministrator;
import com.ftn.isa.repository.AddressRepository;
import com.ftn.isa.repository.BloodAmountRepository;
import com.ftn.isa.repository.BloodCenterAdministratorRepository;
import com.ftn.isa.repository.BloodCenterRepository;
import com.ftn.isa.repository.WorkingTimeRepository;

@Service
public class BloodCenterService {
	@Autowired
	private BloodCenterRepository bloodCenterRepository;

	@Autowired
	private BloodAmountRepository bloodAmountRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private WorkingTimeRepository workingTimeRepository;

	@Autowired
	private BloodCenterAdministratorRepository bloodCenterAdministratorRepository;

	public BloodCenter findOne(Long id) {
		return bloodCenterRepository.findById(id).orElseGet(null);
	}

	public List<BloodCenter> findAll(Sort by) {
		return bloodCenterRepository.findAll(by);
	}

	public List<BloodCenter> findAll() {
		return bloodCenterRepository.findAll();
	}

	public BloodCenter save(BloodCenter bloodCenter) {
		WorkingTime workingTime = workingTimeRepository.save(bloodCenter.getWorkingTime());
		Address address = addressRepository.save(bloodCenter.getAddress());
		BloodAmount blood = bloodAmountRepository.save(bloodCenter.getBloodAmount());
		bloodCenter.setWorkingTime(workingTime);
		bloodCenter.setAddress(address);
		bloodCenter.setBloodAmount(blood);
		return bloodCenterRepository.save(bloodCenter);
	}

	public List<BloodCenter> searchCenters(String query) {
		List<BloodCenter> bloodCenters = bloodCenterRepository.searchCenters(query);
		return bloodCenters;
	}

	public List<BloodCenter> filterCenters(String searchQuery, float filterQuery) {
		List<BloodCenter> bloodCenters = bloodCenterRepository.filterCenters(searchQuery, filterQuery);
		return bloodCenters;
	}

	public BloodCenter edit(BloodCenter bloodCenter) {
		Address address = addressRepository.save(bloodCenter.getAddress());
		bloodCenter.setAddress(address);
		return bloodCenterRepository.save(bloodCenter);
	}

	public BloodCenter findOneByBloodCenterAdministratorId(Long id) {
		return bloodCenterRepository.findOneByBloodCenterAdministratorId(id);
	}

}

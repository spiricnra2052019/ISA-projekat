package com.ftn.isa.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.ftn.isa.dto.RegisteredUserDTO;
import com.ftn.isa.mapper.RegisteredUserMapper;
import com.ftn.isa.model.*;
import com.ftn.isa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BloodCenterRepository bloodCenterRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private UserVisitHistoryRepository userVisitHistoryRepository;

	public List<RegisteredUser> searchUsers(String query) {
		List<RegisteredUser> users = userRepository.searchUsers(query);
		return users;
	}

	public RegisteredUser findOne(Long id) {
		return userRepository.findById(id).orElseGet(null);
	}

	public List<RegisteredUser> findAll() {
		return userRepository.findAll();
	}

	public RegisteredUser save(RegisteredUser RegisteredUser) {
		Address address = addressRepository.save(RegisteredUser.getAddress());
		RegisteredUser.setAddress(address);
		return userRepository.save(RegisteredUser);
	}

	public void remove(Long id) {
		userRepository.deleteById(id);
	}

	public List<RegisteredUser> findByLastname(String lastname) {
		return userRepository.findAllByLastname(lastname);
	}

	public List<RegisteredUser> findByNameAndLastname(String firstName, String lastName) {
		return userRepository.findByNameAndLastnameAllIgnoringCase(firstName, lastName);
	}

	// public RegisteredUser loginUser(String email, String password) throws
	// Exception {
	// RegisteredUser registeredUser = userRepository.loginUser(email, password);
	// if (registeredUser == null) {
	// throw new Exception("Invalid login!");
	// }
	// return registeredUser;
	// }

	public void activateUser(String token) throws Exception {
		RegisteredUser registeredUser = userRepository.findOneByActivationToken(token);
		if (registeredUser == null) {
			throw new Exception("Invalid token!");
		}
		registeredUser.setEnabled(true);
		userRepository.save(registeredUser);
	}

	public List<RegisteredUserDTO> getVisitedUsers(Long adminId, String sortType, boolean direction){
		BloodCenter center = bloodCenterRepository.findOneByBloodCenterAdministratorId(adminId);
		List<UserVisitHistory> histories = userVisitHistoryRepository.findAllByAppointmentBloodCenterId(center.getId())
				.stream()
				.filter(h -> h.getAppointment().getDate().isBefore(LocalDate.now()))
				.collect(Collectors.toList());
		if (sortType.equals("name")) {
			histories = sortByName(histories, direction);
		} else if (sortType.equals("lastName")) {
			histories = sortByLastName(histories, direction);
		} else if (sortType.equals("date")) {
			histories = sortByDate(histories, direction);
		} else if (!sortType.equals("")) {
			throw new RuntimeException("Invalid sort type.");
		}
		List<RegisteredUserDTO> users = RegisteredUserMapper.toDtoList(histories);
		return users;
	}

	private List<UserVisitHistory> sortByName(List<UserVisitHistory> histories, boolean direction) {
		if (!direction) {
			Comparator<UserVisitHistory> nameComparator = Comparator.comparing(h -> h.getUser().getName());
			Collections.sort(histories, nameComparator.reversed());
		} else {
			Collections.sort(histories, Comparator.comparing(h -> h.getUser().getName()));
		}
		return histories;
	}

	private List<UserVisitHistory> sortByLastName(List<UserVisitHistory> histories, boolean direction) {
		if (!direction) {
			Comparator<UserVisitHistory> lastNameComparator = Comparator.comparing(h -> h.getUser().getLastname());
			Collections.sort(histories, lastNameComparator.reversed());
		} else {
			Collections.sort(histories, Comparator.comparing(h -> h.getUser().getLastname()));
		}
		return histories;
	}

	private List<UserVisitHistory> sortByDate(List<UserVisitHistory> histories, boolean direction) {
		if (!direction) {
			Comparator<UserVisitHistory> dateComparator = Comparator.comparing(h -> h.getAppointment().getDate());
			Collections.sort(histories, dateComparator.reversed());
		} else {
			Collections.sort(histories, Comparator.comparing(h -> h.getAppointment().getDate()));
		}
		return histories;
	}

}

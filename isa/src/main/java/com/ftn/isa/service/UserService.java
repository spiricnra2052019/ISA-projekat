package com.ftn.isa.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.ftn.isa.dto.RegisterdUserDTO;
import com.ftn.isa.mapper.RegisteredUserMapper;
import com.ftn.isa.model.*;
import com.ftn.isa.repository.*;
import org.hibernate.type.ListType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;

import com.ftn.isa.auth.AuthenticationResponse;
import com.ftn.isa.model.Address;
import com.ftn.isa.model.BaseUser;
import com.ftn.isa.model.RegisteredUser;
import com.ftn.isa.repository.AddressRepository;
import com.ftn.isa.repository.UserRepository;

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
		RegisteredUser.setPenalty(0);
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

	public Integer getPenaltyPointsById(Long id) {
		RegisteredUser user = userRepository.findOneById(id).orElseGet(null);

		return user.getPenalty();
	}

	public void activateUser(String token) throws Exception {
		RegisteredUser registeredUser = userRepository.findOneByActivationToken(token);
		if (registeredUser == null) {
			throw new Exception("Invalid token!");
		}
		registeredUser.setEnabled(true);
		userRepository.save(registeredUser);
	}

	public List<RegisterdUserDTO> getVisitedUsers(Long adminId, String sortType){
		BloodCenter center = bloodCenterRepository.findOneByBloodCenterAdministratorId(adminId);
		List<UserVisitHistory> histories = userVisitHistoryRepository.findAllByAppointmentBloodCenterId(center.getId())
				.stream()
				.filter(h -> h.getAppointment().getDate().isBefore(LocalDate.now()))
				.collect(Collectors.toList());
		if (sortType.equals("name")) {
			histories = sortByName(histories);
		} else if (sortType.equals("lastName")) {
			histories = sortByLastName(histories);
		} else if (sortType.equals("date")) {
			histories = sortByDate(histories);
		} else {
			throw new RuntimeException("Invalid sort type.");
		}
		return RegisteredUserMapper.toDtoList(histories);
	}

	private List<UserVisitHistory> sortByName(List<UserVisitHistory> histories) {
		histories.sort(Comparator.comparing(o -> o.getUser().getName()));
		return histories;
	}

	private List<UserVisitHistory> sortByLastName(List<UserVisitHistory> histories) {
		histories.sort(Comparator.comparing(o -> o.getUser().getLastname()));
		return histories;
	}

	private List<UserVisitHistory> sortByDate(List<UserVisitHistory> histories) {
		histories.sort(Comparator.comparing(o -> o.getAppointment().getDate()));
		return histories;
	}

	public RegisteredUser incrementPenaltyPoints(Long id) {
		RegisteredUser user = userRepository.findOneById(id).orElseGet(null);
		user.setPenalty(user.getPenalty() + 1);
		return userRepository.save(user);
	}

	// schedule every 1st day of month at 00:00
	// test schedule every 10 seconds
	// @Scheduled(cron = "*/10 * * * * *")
	@Scheduled(cron = "0 0 0 1 * ?")
	public void setPenaltyPointsToZeroForAllUsers() {
		List<RegisteredUser> users = userRepository.findAll();
		for (RegisteredUser user : users) {
			user.setPenalty(0);
			System.out.println("User " + user.getUsername() + " has " + user.getPenalty() + " penalty points.");
			userRepository.save(user);
		}
	}
}

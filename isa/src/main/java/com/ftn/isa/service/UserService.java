package com.ftn.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;

import com.ftn.isa.auth.AuthenticationResponse;
import com.ftn.isa.model.Address;
import com.ftn.isa.model.RegisteredUser;
import com.ftn.isa.repository.AddressRepository;
import com.ftn.isa.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AddressRepository addressRepository;

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

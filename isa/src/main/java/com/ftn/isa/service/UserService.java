package com.ftn.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ftn.isa.model.RegisteredUser;
import com.ftn.isa.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	
	public List<RegisteredUser> searchUsers(String query) {
	List<RegisteredUser> users = userRepository.searchUsers(query);
	return users;
	}

	
	public RegisteredUser findOne(Long id) {
		return userRepository.findById(id).orElseGet(null);
	}
	
	public List<RegisteredUser> findAll(){
		return userRepository.findAll();
	}
	
	public RegisteredUser save(RegisteredUser RegisteredUser) {
		return userRepository.save(RegisteredUser);
	}
	
	public void remove(Long id) {
		userRepository.deleteById(id);
	}
	
	public List<RegisteredUser> findByLastname(String lastname){
		return userRepository.findAllByLastname(lastname);
	}
	
	public List<RegisteredUser> findByNameAndLastname(String firstName, String lastName) {
		return userRepository.findByNameAndLastnameAllIgnoringCase(firstName, lastName);
	}
	
}

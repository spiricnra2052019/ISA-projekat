package com.ftn.isa.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;

import com.ftn.isa.model.User;
import com.ftn.isa.repository.InMemoryUserRepository;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private InMemoryUserRepository userRepository;

	@Override
	public Collection<User> findAll() {
		Collection<User> users = userRepository.findAll();
		return users;
	}

	@Override
	public User findOne(Long id) {
		User user = userRepository.findOne(id);
		return user;
	}

	@Override
	public User create(User user) throws Exception {
		if (user.getId() != null) {
			throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
		}
		User savedUser = userRepository.create(user);
		return savedUser;
	}

	@Override
	public User update(User user) throws Exception {
		User userToUpdate = findOne(user.getId());
		if (userToUpdate == null) {
			throw new Exception("Trazeni entitet nije pronadjen.");
		}
		userToUpdate.setName(user.getName());
		User updateUser = userRepository.create(userToUpdate);
		return updateUser;
	}

	@Override
	public User delete(Long id) {
		return userRepository.delete(id);
	}
}

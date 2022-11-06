package com.ftn.isa.service;

import java.util.Collection;

import com.ftn.isa.model.User;

public interface UserService {
	
	Collection<User> findAll();

	User findOne(Long id);

	User create(User user) throws Exception;

	User update(User user) throws Exception;

	User delete(Long id);

}

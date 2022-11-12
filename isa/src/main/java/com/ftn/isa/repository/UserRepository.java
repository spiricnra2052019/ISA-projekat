package com.ftn.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ftn.isa.model.RegisteredUser;

public interface UserRepository extends JpaRepository<RegisteredUser, Long> {
	
	public RegisteredUser findOneByUsername(String username);
		
	public List<RegisteredUser> findAllByLastname(String lastname);
	
	public List<RegisteredUser> findByNameAndLastnameAllIgnoringCase(String firstname, String lastname);
	
	@Query("select u from RegisteredUser u where u.email = ?1 and u.password = ?2")
	public RegisteredUser loginUser(String email, String password);
	
}

package com.ftn.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.ftn.isa.model.RegisteredUser;

@Repository
public interface UserRepository extends JpaRepository<RegisteredUser, Long> {
	
	public RegisteredUser findOneByUsername(String username);
		
	public List<RegisteredUser> findAllByLastname(String lastname);
	
	public List<RegisteredUser> findByNameAndLastnameAllIgnoringCase(String firstname, String lastname);
	
	@Query("SELECT u FROM RegisteredUser u WHERE " +
			"UPPER(u.name) LIKE CONCAT('%' , :query, '%')" + 
			"Or UPPER(u.lastname) LIKE CONCAT('%' , :query, '%')")
	public List<RegisteredUser> searchUsers(String query);

	@Query("select u from RegisteredUser u where u.email = ?1 and u.password = ?2")
	public RegisteredUser loginUser(String email, String password);

}

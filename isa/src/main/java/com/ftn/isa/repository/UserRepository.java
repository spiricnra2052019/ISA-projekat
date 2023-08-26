package com.ftn.isa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ftn.isa.model.RegisteredUser;

@Repository
public interface UserRepository extends JpaRepository<RegisteredUser, Long> {

	public Optional<RegisteredUser> findOneByUsername(String username);

	public Optional<RegisteredUser> findOneById(Long id);

	public List<RegisteredUser> findAllByLastname(String lastname);

	public List<RegisteredUser> findByNameAndLastnameAllIgnoringCase(String firstname, String lastname);

	@Query("SELECT u FROM RegisteredUser u WHERE " +
			"UPPER(u.name) LIKE CONCAT('%' , :query, '%')" +
			"Or UPPER(u.lastname) LIKE CONCAT('%' , :query, '%')")
	public List<RegisteredUser> searchUsers(String query);

	public RegisteredUser findOneByActivationToken(String token);

}

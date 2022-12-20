package com.ftn.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.isa.model.Administrator;
import org.springframework.data.jpa.repository.Query;


public interface AdministratorRepository extends  JpaRepository<Administrator, Long>  {
	
	public Administrator findOneByUsername(String username);
	
	public List<Administrator> findAllByLastname(String lastname);
	
	public List<Administrator> findByNameAndLastnameAllIgnoringCase(String firstname, String lastname);

}

package com.ftn.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.isa.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	public Employee findOneByUsername(String username);
	
	public List<Employee> findAllByLastname(String lastname);
	
	public List<Employee> findByNameAndLastnameAllIgnoringCase(String firstname, String lastname);
}

package com.ftn.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.isa.model.Employee;
import com.ftn.isa.model.RegisteredUser;
import com.ftn.isa.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Employee findOnde(Long id) {
		return employeeRepository.findById(id).orElseGet(null);
	}
	
	public List<Employee> findAll(){
		return employeeRepository.findAll();
	}
	
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public void remove(Long id) {
		employeeRepository.deleteById(id);
	}
	
	public List<Employee> findByLastname(String lastname){
		return employeeRepository.findAllByLastname(lastname);
	}
	
	public List<Employee> findByNameAndLastname(String firstName, String lastName) {
		return employeeRepository.findByNameAndLastnameAllIgnoringCase(firstName, lastName);
	}
}

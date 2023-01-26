package com.ftn.isa.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@DiscriminatorValue("2")
public class Employee extends BaseUser implements Serializable {
	
	public Employee () {
		super();
	}

	public Employee(Long id, String name, String lastname, String username, String email, String password,
			LocalDate birthday, Address address) {
		super(id, name, lastname, username, email, password, birthday, address);
		// TODO Auto-generated constructor stub
	}
	
	
}

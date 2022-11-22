package com.ftn.isa.model;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("2")
public class TestUser extends BaseUser {

	private String par2;
	
	
	public TestUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TestUser(Long id, String name, String lastname, String username, String email, String password,
			LocalDate birthday, Address address) {
		super(id, name, lastname, username, email, password, birthday, address);
		// TODO Auto-generated constructor stub
	}

	public String getPar2() {
		return par2;
	}

	public void setPar2(String par2) {
		this.par2 = par2;
	}
	
	
}

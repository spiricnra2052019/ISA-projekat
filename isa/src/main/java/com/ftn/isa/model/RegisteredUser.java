package com.ftn.isa.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class RegisteredUser extends BaseUser implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 121981367185596048L;
	
	private String par;
	
	public RegisteredUser() {
		super();
	}



	public RegisteredUser(Long id, String name, String lastname, String username, String email, String password,
			LocalDate birthday, Address address) {
		super(id, name, lastname, username, email, password, birthday, address);
		// TODO Auto-generated constructor stub
	}



	public String getPar() {
		return par;
	}



	public void setPar(String par) {
		this.par = par;
	}

	
	
	
	
}

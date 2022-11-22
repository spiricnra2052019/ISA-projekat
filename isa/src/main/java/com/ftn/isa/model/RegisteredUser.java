package com.ftn.isa.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("1")
public class RegisteredUser extends BaseUser implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 121981367185596048L;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "address_id")
	private Address address;
	
	public RegisteredUser() {
		super();
	}



	public RegisteredUser(Long id, String name, String lastname, String username, String email, String password,
			LocalDate birthday, Address address) {
		super(id, name, lastname, username, email, password, birthday, address);
		// TODO Auto-generated constructor stub
		this.address = address;
	}



	public Address getAddress() {
		return address;
	}



	public void setAddress(Address address) {
		this.address = address;
	}



	
	
	
	
}

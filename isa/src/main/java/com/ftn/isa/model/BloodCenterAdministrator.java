package com.ftn.isa.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("4")
public class BloodCenterAdministrator extends BaseUser implements Serializable {
	
	private static final long serialVersionUID = 121981367185596048L;

	public BloodCenterAdministrator() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BloodCenterAdministrator(Long id, String name, String lastname, String username, String email,
			String password, LocalDate birthday, Address address) {
		super(id, name, lastname, username, email, password, birthday, address);
		// TODO Auto-generated constructor stub
	}
	
	
}

package com.ftn.isa.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@DiscriminatorValue("4")
@Data
public class BloodCenterAdministrator extends BaseUser implements Serializable, UserDetails {

	private static final long serialVersionUID = 121981367185596048L;

	@Column(name = "validated", nullable = true)
	private Boolean validated;

	public BloodCenterAdministrator() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BloodCenterAdministrator(Long id, String name, String lastname, String username,
			String password, LocalDate birthday, Address address, Boolean validated) {
		super(id, name, lastname, username, password, birthday, address);
		// TODO Auto-generated constructor stub
		this.validated = validated;

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("BloodCenterAdministrator")); // Use the role field directly"))
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.getEnabled();
	}

}

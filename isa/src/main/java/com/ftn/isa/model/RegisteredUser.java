package com.ftn.isa.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@DiscriminatorValue("1")
@Builder
@AllArgsConstructor
public class RegisteredUser extends BaseUser implements Serializable, UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 121981367185596048L;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "address_id")
	private Address address;

	@Column(name = "enabled", nullable = true)
	private boolean enabled;

	@Column(name = "activationToken", nullable = true)
	private String activationToken;

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

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setActivationToken(String activationToken) {
		this.activationToken = activationToken;
	}

	public String getActivationToken() {
		return activationToken;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("RegisteredUser")); // Use the role field directly
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
		return enabled;
	}

}

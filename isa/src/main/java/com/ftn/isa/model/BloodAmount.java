package com.ftn.isa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BloodAmount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column
	private float a;
	
	@Column
	private float b;
	
	@Column
	private float ab;
	
	@Column
	private float zero;
	
	public BloodAmount() {
		super();
	}

	public BloodAmount(Long id, float a, float b, float ab, float zero) {
		super();
		this.id = id;
		this.a = a;
		this.b = b;
		this.ab = ab;
		this.zero = zero;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getA() {
		return a;
	}

	public void setA(float a) {
		this.a = a;
	}

	public float getB() {
		return b;
	}

	public void setB(float b) {
		this.b = b;
	}

	public float getAb() {
		return ab;
	}

	public void setAb(float ab) {
		this.ab = ab;
	}

	public float getZero() {
		return zero;
	}

	public void setZero(float zero) {
		this.zero = zero;
	}
	
}

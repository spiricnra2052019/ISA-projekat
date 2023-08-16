package com.ftn.isa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonInclude;

// list of employees is missing

@Entity
public class BloodCenter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "averageRate", nullable = false)
	private float averageRate;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "address_id")
	private Address address;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bloodAmount_id")
	private BloodAmount bloodAmount;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "blood_center_administrator_id")
	private BloodCenterAdministrator bloodCenterAdministrator;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "working_time_id")
	private WorkingTime workingTime;

	public BloodCenter() {
		super();
	}

	public BloodCenter(Long id, String name, String description, float averageRate, Address address,
			BloodAmount bloodAmount, BloodCenterAdministrator bloodCenterAdministrator, WorkingTime workingTime) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.averageRate = averageRate;
		this.address = address;
		this.bloodAmount = bloodAmount;
		this.bloodCenterAdministrator = bloodCenterAdministrator;
		this.workingTime = workingTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getAverageRate() {
		return averageRate;
	}

	public void setAverageRate(float averageRate) {
		this.averageRate = averageRate;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public BloodCenterAdministrator getBloodCenterAdministrator() {
		return bloodCenterAdministrator;
	}

	public void setBloodCenterAdministrator(BloodCenterAdministrator bloodCenterAdministrator) {
		this.bloodCenterAdministrator = bloodCenterAdministrator;
	}

	public BloodAmount getBloodAmount() {
		return bloodAmount;
	}

	public void setBloodAmount(BloodAmount bloodAmount) {
		this.bloodAmount = bloodAmount;
	}

	public WorkingTime getWorkingTime() {
		return workingTime;
	}

	public void setWorkingTime(WorkingTime workingTime) {
		this.workingTime = workingTime;
	}

}

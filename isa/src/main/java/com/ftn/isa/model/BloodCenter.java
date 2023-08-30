package com.ftn.isa.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

// list of employees is missing

@Entity
@Table(name = "BloodCenter")
public class BloodCenter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "equipment", nullable = false)
	private int equipment;

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
			BloodAmount bloodAmount, BloodCenterAdministrator bloodCenterAdministrator, WorkingTime workingTime, int equipment) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.averageRate = averageRate;
		this.address = address;
		this.bloodAmount = bloodAmount;
		this.bloodCenterAdministrator = bloodCenterAdministrator;
		this.workingTime = workingTime;
		this.equipment = equipment;
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

	public Float getAverageRate() {
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

	public int getEquipment() {
		return equipment;
	}

	public void setEquipment(int equipment) {
		this.equipment = equipment;
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		BloodCenter that = (BloodCenter) o;

		return id != null && id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

}

package com.ftn.isa.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name="ScheduleCalendar")
public class ScheduleCalendar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique=true, nullable = false)
	private Long id;
	
	@Column(name = "scheduleDate", nullable = false)
	private String scheduleDate;
	
	@JsonFormat(pattern="HH:mm:ss")
	@Column(name = "startTime", nullable = false)
	private LocalTime startTime;
	
	@Column(name = "duration", nullable = false)
	private int duration;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "lastname", nullable = false)
	private String lastname;

	public ScheduleCalendar() {}

	public ScheduleCalendar(Long id, String scheduleDate, LocalTime startTime, int duration, String name, String lastname) {
		super();
		this.id = id;
		this.scheduleDate = scheduleDate;
		this.startTime = startTime;
		this.duration = duration;
		this.name = name;
		this.lastname = lastname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDate() {
		return scheduleDate;
	}

	public void setDate(String date) {
		this.scheduleDate = date;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
}

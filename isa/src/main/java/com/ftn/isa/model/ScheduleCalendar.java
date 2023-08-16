package com.ftn.isa.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.apache.tomcat.jni.Local;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ftn.isa.dto.ScheduleCalendarDTO;

@Entity(name = "ScheduleCalendar")
public class ScheduleCalendar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "scheduleDate", nullable = false)
	private LocalDate scheduleDate;

	@JsonFormat(pattern = "HH:mm")
	@Column(name = "startTime", nullable = false)
	private LocalTime startTime;

	@Column(name = "duration", nullable = false)
	private int duration;

	@Column(name = "name", nullable = true)
	private String name;

	@Column(name = "lastname", nullable = true)
	private String lastname;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "blood_center_id")
	private BloodCenter bloodCenter;

	public ScheduleCalendar() {
	}

	public ScheduleCalendar(ScheduleCalendarDTO scheduleCalendarDTO) {
		this.scheduleDate = scheduleCalendarDTO.getDate();
		this.startTime = scheduleCalendarDTO.getStartTime();
		this.duration = scheduleCalendarDTO.getDuration();
		this.name = scheduleCalendarDTO.getName();
		this.lastname = scheduleCalendarDTO.getLastname();
	}

	public ScheduleCalendar(Long id, LocalDate scheduleDate, LocalTime startTime, int duration, String name,
			String lastname, BloodCenter bloodCenter) {
		super();
		this.id = id;
		this.scheduleDate = scheduleDate;
		this.startTime = startTime;
		this.duration = duration;
		this.name = name;
		this.lastname = lastname;
		this.bloodCenter = bloodCenter;
	}

	public ScheduleCalendar(LocalDate scheduleDate, LocalTime startTime, int duration,
			BloodCenter bloodCenter) {
		super();
		this.scheduleDate = scheduleDate;
		this.startTime = startTime;
		this.duration = duration;
		this.bloodCenter = bloodCenter;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return scheduleDate;
	}

	public void setDate(LocalDate date) {
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

	public BloodCenter getBloodCenter() {
		return bloodCenter;
	}

	public void setBloodCenter(BloodCenter bloodCenter) {
		this.bloodCenter = bloodCenter;
	}
}

package com.ftn.isa.dto;

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

import com.fasterxml.jackson.annotation.JsonFormat;

public class ScheduleCalendarDTO {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate scheduleDate;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;

    private int duration;

    private String name;

    private String lastname;

    private Long bloodCenterId;

    public ScheduleCalendarDTO() {
    }

    public ScheduleCalendarDTO(LocalDate scheduleDate, LocalTime startTime, int duration, String name, String lastname,
            Long bloodCenterId) {
        this.scheduleDate = scheduleDate;
        this.startTime = startTime;
        this.duration = duration;
        this.name = name;
        this.lastname = lastname;
        this.bloodCenterId = bloodCenterId;
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

    public Long getBloodCenterId() {
        return bloodCenterId;
    }

    public void setBloodCenterId(Long bloodCenterId) {
        this.bloodCenterId = bloodCenterId;
    }
}

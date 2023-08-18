package com.ftn.isa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;

@Entity
public class WorkingTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @JsonFormat(pattern = "HH:mm")
    @Column(name = "opening_time")
    private LocalTime openingTime;

    @JsonFormat(pattern = "HH:mm")
    @Column(name = "closing_time")
    private LocalTime closingTime;

    public WorkingTime() {
        super();
    }

    public WorkingTime(LocalTime openingTime, LocalTime closingTime) {
        super();
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }
}

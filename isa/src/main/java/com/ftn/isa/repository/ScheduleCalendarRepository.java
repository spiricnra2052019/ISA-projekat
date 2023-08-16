package com.ftn.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.isa.model.ScheduleCalendar;

public interface ScheduleCalendarRepository extends JpaRepository<ScheduleCalendar, Long> {
    public List<ScheduleCalendar> findAllByBloodCenterId(Long id);
}

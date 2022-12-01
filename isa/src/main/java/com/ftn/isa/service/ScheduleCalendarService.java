package com.ftn.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.isa.model.BloodCenterAdministrator;
import com.ftn.isa.model.ScheduleCalendar;
import com.ftn.isa.repository.ScheduleCalendarRepository;

@Service
public class ScheduleCalendarService {
	@Autowired
	private ScheduleCalendarRepository scheduleCalendarRepository;
	
	public ScheduleCalendar findOne(Long id) {
		return scheduleCalendarRepository.findById(id).orElseGet(null);
	}
	
	public List<ScheduleCalendar> findAll(){
		return scheduleCalendarRepository.findAll();
	}
	
	public ScheduleCalendar save(ScheduleCalendar schedule) {
		return scheduleCalendarRepository.save(schedule);
	}
	
	public void remove(Long id) {
		scheduleCalendarRepository.deleteById(id);
	}
}

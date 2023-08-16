package com.ftn.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.isa.dto.ScheduleCalendarDTO;
import com.ftn.isa.model.BloodCenter;
import com.ftn.isa.model.BloodCenterAdministrator;
import com.ftn.isa.model.ScheduleCalendar;
import com.ftn.isa.repository.BloodCenterRepository;
import com.ftn.isa.repository.ScheduleCalendarRepository;

@Service
public class ScheduleCalendarService {
	@Autowired
	private ScheduleCalendarRepository scheduleCalendarRepository;

	@Autowired
	private BloodCenterRepository bloodCenterRepository;

	public ScheduleCalendar findOne(Long id) {
		return scheduleCalendarRepository.findById(id).orElseGet(null);
	}

	public List<ScheduleCalendar> findAll() {
		return scheduleCalendarRepository.findAll();
	}

	public ScheduleCalendar save(ScheduleCalendarDTO schedule) {
		ScheduleCalendar scheduleCalendar = new ScheduleCalendar(schedule);
		BloodCenter bloodCenter = bloodCenterRepository.findById(schedule.getBloodCenterId()).orElseGet(null);
		scheduleCalendar.setBloodCenter(bloodCenter);
		return scheduleCalendarRepository.save(scheduleCalendar);
	}

	public void remove(Long id) {
		scheduleCalendarRepository.deleteById(id);
	}

	public List<ScheduleCalendar> findAllByBloodCenterId(Long id) {
		return scheduleCalendarRepository.findAllByBloodCenterId(id);
	}
}

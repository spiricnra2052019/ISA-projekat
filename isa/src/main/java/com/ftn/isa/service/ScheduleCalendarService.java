package com.ftn.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.isa.dto.ScheduleCalendarDTO;
import com.ftn.isa.model.BaseUser;
import com.ftn.isa.model.BloodCenter;
import com.ftn.isa.model.BloodCenterAdministrator;
import com.ftn.isa.model.ScheduleCalendar;
import com.ftn.isa.repository.BloodCenterRepository;
import com.ftn.isa.repository.ScheduleCalendarRepository;
import com.ftn.isa.repository.UserRepository;

@Service
public class ScheduleCalendarService {
	@Autowired
	private ScheduleCalendarRepository scheduleCalendarRepository;

	@Autowired
	private BloodCenterRepository bloodCenterRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PatientAnswerService patientAnswerService;

	@Autowired
	private UserVisitHistoryService userVisitHistoryService;

	@Autowired
	private EmailQRService emailQRService;

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

	public ScheduleCalendar scheduleAppointmentForUser(Long id, Long userId) {

		ScheduleCalendar scheduleCalendar = scheduleCalendarRepository.findById(id).orElseGet(null);
		List<ScheduleCalendar> userAppointments = scheduleCalendarRepository.findAllByUserId(userId);
		BaseUser user = userRepository.findById(userId).orElseGet(null);

		if (checkIfAppointmentIsAlreadyScheduled(scheduleCalendar, userAppointments)) {
			throw new IllegalArgumentException("Patient already has scheduled appointment for this date and time.");
		}
		if (!patientAnswerService.checkIfPatientHasAlreadyAnswered(userId)) {
			throw new IllegalArgumentException("Patient has not answered questions yet.");
		}
		if (userVisitHistoryService.checkIfUserVisitedIn6Months(userId)) {
			throw new IllegalArgumentException("Patient has already visited blood center in last 6 months.");
		}
		scheduleCalendar.setUser(user);

		emailQRService.sendEmailWithQRCode(scheduleCalendar, "givemij506@bagonew.com");
		return scheduleCalendarRepository.save(scheduleCalendar);
	}

	public boolean checkIfAppointmentIsAlreadyScheduled(ScheduleCalendar scheduleCalendar,
			List<ScheduleCalendar> userAppointments) {
		for (ScheduleCalendar appointment : userAppointments) {
			if (appointment.getDate().equals(scheduleCalendar.getDate())
					&& appointment.getStartTime().equals(scheduleCalendar.getStartTime())
					&& appointment.getBloodCenter().getId() == scheduleCalendar.getBloodCenter().getId()) {
				return true;
			}
		}
		return false;
	}

	public List<ScheduleCalendar> findAllByUserId(Long id) {
		return scheduleCalendarRepository.findAllByUserId(id);
	}

	public ScheduleCalendar declineAppointment(Long id) {
		ScheduleCalendar scheduleCalendar = scheduleCalendarRepository.findById(id).orElseGet(null);
		scheduleCalendar.setUser(null);
		return scheduleCalendarRepository.save(scheduleCalendar);
	}
}

package com.ftn.isa.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.isa.dto.ScheduleCalendarDTO;
import com.ftn.isa.dto.UserScheduleAppointmentDTO;
import com.ftn.isa.model.BaseUser;
import com.ftn.isa.model.BloodCenter;
import com.ftn.isa.model.ScheduleCalendar;
import com.ftn.isa.repository.BloodCenterRepository;
import com.ftn.isa.repository.ScheduleCalendarRepository;
import com.ftn.isa.repository.UserRepository;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public ScheduleCalendar save(ScheduleCalendarDTO schedule) {
		BloodCenter bloodCenter = bloodCenterRepository.findByIdForSchedule(schedule.getBloodCenterId())
				.orElseGet(null);
		ScheduleCalendar scheduleCalendar = new ScheduleCalendar(schedule);
		scheduleCalendar.setBloodCenter(bloodCenter);

		if (isOverlapping(scheduleCalendar)) {
			throw new IllegalArgumentException("Overlapping time slots are not allowed.");
		}

		return scheduleCalendarRepository.save(scheduleCalendar);
	}

	private boolean isOverlapping(ScheduleCalendar schedule) {
		LocalDate scheduleDate = schedule.getDate();
		LocalTime startTimeCnv = schedule.getStartTime();
		LocalTime endTimeCnv = startTimeCnv.plusMinutes(schedule.getDuration());

		List<ScheduleCalendar> schedules = scheduleCalendarRepository.searchByScheduleDate(scheduleDate);

		for (ScheduleCalendar existingSchedule : schedules) {
			LocalTime scheduleStartTime = existingSchedule.getStartTime();
			LocalTime scheduleEndTime = scheduleStartTime.plusMinutes(existingSchedule.getDuration());

			if ((scheduleStartTime.isBefore(endTimeCnv) &&
					scheduleEndTime.isAfter(startTimeCnv)) ||
					(startTimeCnv.equals(scheduleStartTime) &&
							endTimeCnv.equals(scheduleEndTime))
					||
					(startTimeCnv.isBefore(scheduleEndTime) &&
							endTimeCnv.isAfter(scheduleStartTime))) {
				return true; // Overlapping schedules found
			}
		}

		return false; // No overlapping schedules found
	}

	public void remove(Long id) {
		scheduleCalendarRepository.deleteById(id);
	}

	public List<ScheduleCalendar> findAllByBloodCenterId(Long id) {
		return scheduleCalendarRepository.findAllByBloodCenterId(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public ScheduleCalendar scheduleAppointmentForUser(Long id, Long userId) {
		ScheduleCalendar scheduleCalendar = scheduleCalendarRepository.findByIdForUpdate(id).orElseGet(null);
		List<ScheduleCalendar> userAppointments = scheduleCalendarRepository.findAllByUserId(userId);
		BaseUser user = userRepository.findById(userId).orElseGet(null);

		if (!patientAnswerService.checkIfPatientHasAlreadyAnswered(userId)) {
			throw new IllegalArgumentException("Patient has not answered questions yet.");
		}
		if (checkIfAppointmentIsAlreadyScheduled(scheduleCalendar, userAppointments)) {
			throw new IllegalArgumentException("Patient already has scheduled appointment for this date and time.");
		}
		if (userVisitHistoryService.checkIfUserVisitedIn6Months(userId)) {
			throw new IllegalArgumentException("Patient has already visited blood center in last 6 months.");
		}
		scheduleCalendar.setUser(user);

		emailQRService.sendEmailWithQRCode(scheduleCalendar, scheduleCalendar.getUser().getUsername());
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

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public ScheduleCalendar declineAppointment(Long id) {
		ScheduleCalendar scheduleCalendar = scheduleCalendarRepository.findByIdForUpdate(id).orElseGet(null);
		scheduleCalendar.setUser(null);
		return scheduleCalendarRepository.save(scheduleCalendar);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public List<BloodCenter> freeBloodCenters(String startDate, String startTime, int duration) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate scheduleDate = LocalDate.parse(startDate, formatter);
		LocalTime startTimeCnv = LocalTime.parse(startTime);
		LocalTime endTimeCnv = startTimeCnv.plusMinutes(duration);

		List<ScheduleCalendar> schedules = scheduleCalendarRepository.searchByScheduleDate(scheduleDate);

		List<BloodCenter> bloodCenters = bloodCenterRepository.findAll();

		List<BloodCenter> overlappingCenters = new ArrayList<>();

		for (ScheduleCalendar schedule : schedules) {
			LocalTime scheduleStartTime = schedule.getStartTime();
			LocalTime scheduleEndTime = scheduleStartTime.plusMinutes(schedule.getDuration());

			if ((scheduleStartTime.isBefore(endTimeCnv) && scheduleEndTime.isAfter(startTimeCnv)) ||
					(startTimeCnv.equals(scheduleStartTime) && endTimeCnv.equals(scheduleEndTime)) ||
					(startTimeCnv.isBefore(scheduleEndTime) && endTimeCnv.isAfter(scheduleStartTime))) {
				overlappingCenters.add(schedule.getBloodCenter());
			}
		}

		// remove overlapping centers
		bloodCenters.removeAll(overlappingCenters);

		return bloodCenters;
	}

	public List<BloodCenter> freeBloodCentersAndSortBy(String startDate, String startTime, int duration) {
		List<BloodCenter> bloodCenters = freeBloodCenters(startDate, startTime, duration);
		// sort by rate
		bloodCenters.sort((b1, b2) -> b1.getAverageRate().compareTo(b2.getAverageRate()));

		return bloodCenters;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public ScheduleCalendar createAppointmentByUser(UserScheduleAppointmentDTO userScheduleAppointmentDTO) {
		BloodCenter bloodCenter = bloodCenterRepository
				.findByIdForSchedule(userScheduleAppointmentDTO.getBloodCenterId())
				.orElseGet(null);
		ScheduleCalendar scheduleCalendar = new ScheduleCalendar(userScheduleAppointmentDTO.getScheduleDate(),
				userScheduleAppointmentDTO.getStartTime(), userScheduleAppointmentDTO.getDuration());

		scheduleCalendar.setBloodCenter(bloodCenter);
		BaseUser user = userRepository.findById(userScheduleAppointmentDTO.getUserId()).orElseGet(null);
		scheduleCalendar.setUser(user);

		if (isOverlapping(scheduleCalendar)) {
			throw new IllegalArgumentException("Overlapping time slots are not allowed.");
		}

		emailQRService.sendEmailWithQRCode(scheduleCalendar, scheduleCalendar.getUser().getUsername());

		return scheduleCalendarRepository.save(scheduleCalendar);
	}
}

package com.ftn.isa.service;

import com.ftn.isa.dto.UserVisitHistoryDTO;
import com.ftn.isa.model.*;
import com.ftn.isa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class UserVisitHistoryService {
    @Autowired
    private UserVisitHistoryRepository userVisitHistoryRepository;
    @Autowired
    private ScheduleCalendarRepository scheduleCalendarRepository;
    @Autowired
    private BloodCenterRepository bloodCenterRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BloodCenterAdministratorRepository administratorRepository;

    public List<UserVisitHistory> searchReports(String query) {
        List<UserVisitHistory> reports = userVisitHistoryRepository.searchReports(query);
        return reports;
    }

    public UserVisitHistory findOne(Long id) {
        return userVisitHistoryRepository.findById(id).orElseGet(null);
    }

    public List<UserVisitHistory> findAll() {
        return userVisitHistoryRepository.findAll();
    }

    public UserVisitHistory save(UserVisitHistory history) {
        BloodAmount bloodAmount = new BloodAmount();
        if (history.getBloodType() == "A") {
            bloodAmount.setA(bloodAmount.getA() + history.getQuantity());
        } else if (history.getBloodType() == "B") {
            bloodAmount.setB(bloodAmount.getB() + history.getQuantity());
        } else if (history.getBloodType() == "AB") {
            bloodAmount.setAb(bloodAmount.getAb() + history.getQuantity());
        } else {
            bloodAmount.setZero(bloodAmount.getZero() + history.getQuantity());
        }
        return userVisitHistoryRepository.save(history);
    }

    public void remove(Long id) {
        userVisitHistoryRepository.deleteById(id);
    }

    public List<UserVisitHistory> findAllByUserId(Long id) {
        return userVisitHistoryRepository.findAllByUserId(id);
    }

    public List<UserVisitHistory> findAllByUserId(Long id, Sort by) {
        return userVisitHistoryRepository.findAllByUserId(id, by);
    }

    public boolean checkIfUserVisitedIn6Months(Long userId) {
        List<UserVisitHistory> userVisitHistories = userVisitHistoryRepository.findAllByUserId(userId);
        for (UserVisitHistory userVisitHistory : userVisitHistories) {
            LocalDate currentDate = LocalDate.now();
            Period period = Period.between(userVisitHistory.getAppointment().getDate(), currentDate);
            if (period.getMonths() < 6 && period.getYears() == 0) {
                return true;
            }
        }
        return false;
    }

    @Transactional(rollbackOn = RuntimeException.class)
    public UserVisitHistory addReport(UserVisitHistoryDTO reportDto){
        ScheduleCalendar scheduleCalendar = scheduleCalendarRepository.findById(reportDto.getScheduleCalendarId()).orElseThrow(()-> new RuntimeException("Schedule not found"));
        BloodCenter center = scheduleCalendar.getBloodCenter();
        BloodAmount amount = center.getBloodAmount();
        UserVisitHistory report = UserVisitHistory.builder()
                .description(reportDto.getDescription())
                .bloodType(reportDto.getBloodType())
                .quantity(reportDto.getQuantity())
                .numberOfEquipmentUsed(reportDto.getNumberOfEquipments())
                .user(userRepository.findById(reportDto.getPatientId()).orElseThrow(()-> new RuntimeException("Patient not found")))
                .appointment(scheduleCalendar)
                .administrator(administratorRepository.findById(center.getBloodCenterAdministrator().getId()).orElseThrow(() -> new RuntimeException("")))
                .build();
        if (reportDto.getBloodType().equals("A")) {
            amount.setA(amount.getA() + reportDto.getQuantity());
        } else if (reportDto.getBloodType().equals("B")) {
            amount.setB(amount.getB() + reportDto.getQuantity());
        } else if (reportDto.getBloodType().equals("AB")) {
            amount.setAb(amount.getAb() + reportDto.getQuantity());
        } else if (reportDto.getBloodType().equals("0")) {
            amount.setZero(amount.getZero() + reportDto.getQuantity());
        } else {
            throw new RuntimeException("Unrecognized blood type.");
        }
        if (center.getEquipment() - reportDto.getNumberOfEquipments() < 0) {
            throw new RuntimeException("Not enough equipment");
        } else {
            center.setEquipment(center.getEquipment() - reportDto.getNumberOfEquipments());
            center.setBloodAmount(amount);
            bloodCenterRepository.save(center);
        }
        return userVisitHistoryRepository.save(report);
    }
}

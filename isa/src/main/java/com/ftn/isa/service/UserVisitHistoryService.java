package com.ftn.isa.service;

import com.ftn.isa.model.BloodAmount;
import com.ftn.isa.model.RegisteredUser;
import com.ftn.isa.model.UserVisitHistory;
import com.ftn.isa.repository.UserVisitHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class UserVisitHistoryService {
    @Autowired
    private UserVisitHistoryRepository userVisitHistoryRepository;

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
            Period period = Period.between(userVisitHistory.getDate(), currentDate);
            if (period.getMonths() < 6) {
                return true;
            }
        }
        return false;
    }
}

package com.ftn.isa.service;

import com.ftn.isa.model.BloodAmount;
import com.ftn.isa.model.RegisteredUser;
import com.ftn.isa.model.UserVisitHistory;
import com.ftn.isa.repository.BloodAmountRepository;
import com.ftn.isa.repository.BloodCenterRepository;
import com.ftn.isa.repository.UserVisitHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserVisitHistoryService {
    @Autowired
    private UserVisitHistoryRepository userVisitHistoryRepository;
    @Autowired
    private BloodAmountRepository bloodAmountRepository;

    @Autowired
    private BloodCenterRepository bloodCenterRepository;

    public List<UserVisitHistory> searchReports(String query) {
        List<UserVisitHistory> reports = userVisitHistoryRepository.searchReports(query);
        return reports;
    }

    public UserVisitHistory findOne(Long id) {
        return userVisitHistoryRepository.findById(id).orElseGet(null);
    }

    public List<UserVisitHistory> findAll(){
        return userVisitHistoryRepository.findAll();
    }

    public UserVisitHistory save(UserVisitHistory history) {
        var bloodAmount = bloodAmountRepository.findById(1l).get();
        if (Objects.equals(history.getBloodType(), "A"))
        {
            bloodAmount.setA(bloodAmount.getA() + history.getQuantity());
        } else if (Objects.equals(history.getBloodType(), "B"))
        {
            bloodAmount.setB(bloodAmount.getB() + history.getQuantity());
        } else if (Objects.equals(history.getBloodType(), "AB"))
        {
            bloodAmount.setAb(bloodAmount.getAb() + history.getQuantity());
        } else if (Objects.equals(history.getBloodType(), "ZERO")){
            bloodAmount.setZero(bloodAmount.getZero() + history.getQuantity());
        }
        var newBloodAmount = bloodAmountRepository.save(bloodAmount);
        var bloodCenter = bloodCenterRepository.findById(1l).get();
        bloodCenter.setEquipmentNum(bloodCenter.getEquipmentNum() - history.getNumberOfEquipmentUsed());
        bloodCenterRepository.save(bloodCenter);
        return userVisitHistoryRepository.save(history);
    }

    public void remove(Long id) {
        userVisitHistoryRepository.deleteById(id);
    }

}

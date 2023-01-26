package com.ftn.isa.service;

import com.ftn.isa.model.UserVisitHistory;
import com.ftn.isa.repository.UserVisitHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserVisitHistoryService {
    @Autowired
    private UserVisitHistoryRepository userVisitHistoryRepository;

    public UserVisitHistory findOne(Long id) {
        return userVisitHistoryRepository.findById(id).orElseGet(null);
    }

    public List<UserVisitHistory> findAll(){
        return userVisitHistoryRepository.findAll();
    }

    public UserVisitHistory save(UserVisitHistory history) {
        return userVisitHistoryRepository.save(history);
    }

    public void remove(Long id) {
        userVisitHistoryRepository.deleteById(id);
    }

}

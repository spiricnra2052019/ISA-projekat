package com.ftn.isa.repository;

import com.ftn.isa.model.RegisteredUser;
import com.ftn.isa.model.UserVisitHistory;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserVisitHistoryRepository extends JpaRepository<UserVisitHistory, Long> {
    @Query("SELECT u FROM UserVisitHistory u WHERE " +
            "UPPER(u.bloodType) LIKE CONCAT('%' , :query, '%')" +
            "Or UPPER(u.description) LIKE CONCAT('%' , :query, '%')")
    public List<UserVisitHistory> searchReports(String query);

    public List<UserVisitHistory> findAllByUserId(Long id);

    public List<UserVisitHistory> findAllByUserId(Long id, Sort by);

    List<UserVisitHistory> findAllByAppointmentBloodCenterId(Long id);
}

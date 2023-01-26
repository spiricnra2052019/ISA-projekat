package com.ftn.isa.repository;

import com.ftn.isa.model.UserVisitHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserVisitHistoryRepository extends JpaRepository<UserVisitHistory, Long> {
}

package com.ftn.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.isa.model.WorkingTime;

public interface WorkingTimeRepository extends JpaRepository<WorkingTime, Long> {

}

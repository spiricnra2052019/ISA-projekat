package com.ftn.isa.repository;

import com.ftn.isa.model.PatientAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientAnswerRepository extends JpaRepository<PatientAnswer, Long> {
}

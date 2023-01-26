package com.ftn.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.isa.model.PatientQuestion;

public interface PatientQuestionRepository extends JpaRepository<PatientQuestion, Long>{

}

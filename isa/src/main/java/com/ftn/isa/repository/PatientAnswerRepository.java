package com.ftn.isa.repository;

import com.ftn.isa.model.PatientAnswer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientAnswerRepository extends JpaRepository<PatientAnswer, Long> {
    List<PatientAnswer> findAllByRegisteredUserId(Long id);

    List<PatientAnswer> removeByRegisteredUserId(Long id);

    PatientAnswer findByRegisteredUserIdAndPatientQuestionId(Long id, Long id1);
}

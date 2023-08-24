package com.ftn.isa.repository;

import com.ftn.isa.model.PatientAnswer;

import java.util.List;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

public interface PatientAnswerRepository extends JpaRepository<PatientAnswer, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({ @QueryHint(name = "javax.persistence.lock.timeout", value = "0") })
    List<PatientAnswer> findAllByRegisteredUserId(Long id);

    List<PatientAnswer> removeByRegisteredUserId(Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({ @QueryHint(name = "javax.persistence.lock.timeout", value = "0") })
    PatientAnswer findByRegisteredUserIdAndPatientQuestionId(Long id, Long id1);
}

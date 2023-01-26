package com.ftn.isa.service;

import com.ftn.isa.model.PatientAnswer;
import com.ftn.isa.repository.PatientAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientAnswerService {
    @Autowired
    private PatientAnswerRepository patientAnswerRepository;

    public PatientAnswer save(PatientAnswer patientAnswer){return patientAnswerRepository.save(patientAnswer);}
}

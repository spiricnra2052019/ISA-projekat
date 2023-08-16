package com.ftn.isa.service;

import com.ftn.isa.dto.QueryAnswerDTO;
import com.ftn.isa.model.PatientAnswer;
import com.ftn.isa.model.RegisteredUser;
import com.ftn.isa.repository.PatientAnswerRepository;
import com.ftn.isa.repository.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatientAnswerService {
    @Autowired
    private PatientAnswerRepository patientAnswerRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public PatientAnswer save(QueryAnswerDTO queryAnswerDTO) {
        PatientAnswer patientAnswer = patientAnswerRepository.findByRegisteredUserIdAndPatientQuestionId(
                queryAnswerDTO.getUserId(), queryAnswerDTO.getQuestion().getId());
        if (patientAnswer == null) {
            patientAnswer = new PatientAnswer();
        }
        patientAnswer.setPatientQuestion(queryAnswerDTO.getQuestion());
        RegisteredUser registeredUser = userRepository.findById(queryAnswerDTO.getUserId()).orElse(null);
        patientAnswer.setRegisteredUser(registeredUser);
        patientAnswer.setAnswer(queryAnswerDTO.getAnswer());
        return patientAnswerRepository.save(patientAnswer);
    }

    public boolean checkIfPatientHasAlreadyAnswered(Long userId) {
        List<PatientAnswer> patientAnswers = patientAnswerRepository.findAllByRegisteredUserId(userId);
        return patientAnswers.size() > 0;
    }
}

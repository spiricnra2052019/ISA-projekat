package com.ftn.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.isa.model.PatientQuestion;
import com.ftn.isa.repository.PatientQuestionRepository;

@Service
public class PatientQuestionService {
	@Autowired
	private PatientQuestionRepository patientQuestionRepository;
	
	public PatientQuestion findOne(Long id) {
		return patientQuestionRepository.findById(id).orElseGet(null);
	}
	
	public List<PatientQuestion> findAll(){
		return patientQuestionRepository.findAll();
	}
	
}

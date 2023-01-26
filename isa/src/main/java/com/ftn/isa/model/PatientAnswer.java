package com.ftn.isa.model;

import javax.persistence.*;

@Entity
public class PatientAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    private PatientQuestion patientQuestion;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id")
    private RegisteredUser registeredUser;

    @Column(name = "answer", nullable = false)
    private boolean answer;

    public PatientAnswer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PatientQuestion getPatientQuestion() {
        return patientQuestion;
    }

    public void setPatientQuestion(PatientQuestion patientQuestion) {
        this.patientQuestion = patientQuestion;
    }

    public RegisteredUser getRegisteredUser() {
        return registeredUser;
    }

    public void setRegisteredUser(RegisteredUser registeredUser) {
        this.registeredUser = registeredUser;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}

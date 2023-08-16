package com.ftn.isa.dto;

import com.ftn.isa.model.PatientQuestion;

public class QueryAnswerDTO {
    private Long userId;

    private PatientQuestion question;

    private boolean answer;

    public QueryAnswerDTO() {
    }

    public QueryAnswerDTO(Long userId, PatientQuestion question, boolean answer) {
        this.userId = userId;
        this.question = question;
        this.answer = answer;
    }

    public QueryAnswerDTO(Long userId, PatientQuestion question) {
        this.userId = userId;
        this.question = question;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public PatientQuestion getQuestion() {
        return question;
    }

    public void setQuestion(PatientQuestion question) {
        this.question = question;
    }

    public boolean getAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

}

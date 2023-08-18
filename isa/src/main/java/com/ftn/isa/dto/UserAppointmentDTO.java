package com.ftn.isa.dto;

import com.ftn.isa.model.RegisteredUser;

public class UserAppointmentDTO {
    private RegisteredUser user;
    private Long appointmentId;

    public UserAppointmentDTO() {
        super();
    }

    public UserAppointmentDTO(RegisteredUser user, Long appointmentId) {
        this.user = user;
        this.appointmentId = appointmentId;
    }

    public RegisteredUser getUser() {
        return user;
    }

    public void setUser(RegisteredUser user) {
        this.user = user;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointment) {
        this.appointmentId = appointment;
    }

}
